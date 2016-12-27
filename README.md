# springboot-dubbo-provider
spring boot 与dubbo进行集成．
搞通之后，觉得很简单，在搞通之前，有不少坑．主要还是依赖问题和配置问题．

## maven配置
### 加入spring boot依赖
原来的配置，配置为允许自动配置
```
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>1.3.3.RELEASE</version>
	</parent>
		
	<dependencies>
	<!--  spring boot -->
			<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-tomcat</artifactId>
			<scope>provided</scope>
		</dependency>
				<!--添加适用于生产环境的功能，如性能指标和监测等功能。 -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-actuator</artifactId>
		</dependency>
</dependencies>
```

### 加入打包插件
```
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
				<version>1.3.3.RELEASE</version>
				<executions>
					<execution>
						<goals>
							<goal>repackage</goal>
						</goals>
					</execution>
				</executions>
			</plugin>
```

## 加入配置
下面的注解一句都不能少，少了就会报错．
```
@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@Import({OrderServiceConfig.class,MybatisConfiguration.class})
public class App 
{
    public static void main( String[] args )
    {
    	 SpringApplication.run(App.class, args);
    }
}
```
如果少了
```
@Import({OrderServiceConfig.class,MybatisConfiguration.class})
```
则无法暴露dubbo服务．
```
Caused by: org.springframework.boot.autoconfigure.jdbc.DataSourceProperties$DataSourceBeanCreationException: Cannot determine embedded database driver class for database type NONE. If you want an em
bedded database please put a supported one on the classpath. If you have database settings to be loaded from a particular profile you may need to active it (no profiles are currently active).
        at org.springframework.boot.autoconfigure.jdbc.DataSourceProperties.getDriverClassName(DataSourceProperties.java:180)
        at org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration$NonEmbeddedConfiguration.dataSource(DataSourceAutoConfiguration.java:121)
        at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        at sun.reflect.NativeMethodAccessorImpl.invoke(Unknown Source)
        at sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source)
        at java.lang.reflect.Method.invoke(Unknown Source)
        at org.springframework.beans.factory.support.SimpleInstantiationStrategy.instantiate(SimpleInstantiationStrategy.java:162)
        ... 38 more
```

```
// 后面经实测，这句可以去掉
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
```

## 加入监控配置
如果没有加入监控配置，则在监控中心，无法看到性能曲线图示信息。
```
	// 配置监控使能，　否则无法显示统计信息
	@Bean
	public MonitorConfig monitor(){
		MonitorConfig monitor = new MonitorConfig();
		monitor.setProtocol("registry");
		
		return monitor;
	}
```

暴露的服务，需要设置监控中心
```
     service.serMonitor(dubboBaseConfig.monitor()); // 设置监控。
```

## 序列化
  公共接口必须实现序列化接口.否则会报错误.
```
mush implements java.io.Serialble;
```

## 小技巧
开启自动驼峰命名规则（camel case）映射，即从经典数据库列名 A_COLUMN 到经典 Java 属性名 aColumn 的类似映射。
```
	@Bean
	public Properties settingss() {
		Properties settingss = new Properties();
		settingss.setProperty("mapUnderscoreToCamelCase","true"); // 设置为将数据库的下划线字段转换为类的驼峰表示

		return settingss;
	}

		@Bean
    	public DataSourceTransactionManager transactionManager() {
    		return new DataSourceTransactionManager(dataSource());
    	}

    	@Bean
    	public SqlSessionFactory sqlSessionFactory() throws Exception {
    		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
    		sessionFactory.setDataSource(dataSource());
    	//	sessionFactory.setDataSource(dataSourcePool());
    		sessionFactory.setTypeAliasesPackage(MAPPERS_PACKAGE_NAME);
    		sessionFactory.setConfigurationProperties(settingss()); // 设置支持下划线转驼峰
    		return sessionFactory.getObject();
    	}
```

## 有用的东西
通过_util_包下的_ServiceHelper_，可以省去很多配置复制代码．感谢_Alibaba Dubbo交流群2 472841355_群@凌封．
代码如下：
```
public class ServiceHelper {


    private static Map<String, Object> services = new HashedMap();

    public static <T> T getService(Class<T> clazz) {
        return getService(clazz, "1.0.0");
    }

    public static <T> T getService(Class<T> clazz, String version) {
        Assert.notNull(clazz);
        T t = (T) services.get(clazz.getName());
        if (t != null) {
            return t;
        }
        // 当前应用配置
        ApplicationConfig application = new ApplicationConfig();
        application.setName("my-apps-all");
        // 连接注册中心配置
        RegistryConfig registry = new RegistryConfig();
        registry.setProtocol("zookeeper");
        registry.setAddress("127.0.0.1:2181");
        //        registry.setUsername("aaa");
        //        registry.setPassword("bbb");
        // 注意：ReferenceConfig为重对象，内部封装了与注册中心的连接，以及与服务提供方的连接

        // 引用远程服务
        ReferenceConfig<T> reference = new ReferenceConfig<T>(); // 此实例很重，封装了与注册中心的连接以及与提供者的连接，请自行缓存，否则可能造成内存和连接泄漏
        reference.setApplication(application);
        reference.setRegistry(registry); // 多个注册中心可以用setRegistries()
        reference.setInterface(clazz);
        reference.setVersion(version);
        // 和本地bean一样使用xxxService
        t = reference.get(); // 注意：此代理对象内部封装了所有通讯细节，对象较重，请缓存复用
        return t;
    }
}
```
这段代码我没有投入生产者使用权哈．

## 编译
```
mvn clean install
```

## 运行
```
java -jar target\dataservice-provider-0.0.1-SNAPSHOT.jar
```
