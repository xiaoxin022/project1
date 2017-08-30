<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="C" %>
<html>
<head>
</head>
<body>
	<C:if test="${!empty error}">
		<font color="red"><C:out value="${error}" /></font>
	</C:if>
	<form action="<C:url value="/loginCheck.html" />" method="post">
		用户名:<input type="text" name="userName" />
		密码:<input type="password" name="password" />
		<br>
		<input type="submit" value="登录" />
		<input type="reset" value="重置" />
	</form>
</body>
</html>