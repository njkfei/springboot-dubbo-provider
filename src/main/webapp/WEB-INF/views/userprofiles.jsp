<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html class="no-js" lang="en">
<head>
	<title>Users</title>
</head>
<body>
	<h1>User List:</h1>
	<ul>
		<c:forEach items="${userprofiles}" var="userprofile">
			<li>${userprofile.id}  - ${userprofile.type}</li>
		</c:forEach>
	</ul>
	<a href="<%= request.getContextPath() %>">home</a>
</body>
</html>