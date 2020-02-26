<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>Edit user</title>
</head>
<body>
<p>Редактировать данные пользователя</p>
<form action="${pageContext.request.contextPath}/edit" method="post">
    <p>Имя:</p><input type="text" name="name" value="<c:out value="${param.name}" />">
    <input type="hidden" name="id" value="<c:out value="${param.id}" />">
    <input type="hidden" name="role" value="<c:out value="${param.role}" />">
    <input type="hidden" name="action" value="update">
    <p><input type="submit"/></p>
</form>
</body>
</html>