package com.sanhao.tech.data.service.util;


import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.util.Assert;

import java.util.Map;


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
