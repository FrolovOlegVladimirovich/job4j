<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>Create user</title>
</head>
<body>
<c:set var="message" value='${requestScope["message"]}' />
<c:if test="${message != null}">
    <c:out value="${message}" /><br/>
</c:if>
<p>Введите данные нового пользователя</p>
<form action="${pageContext.request.contextPath}/create" method="post" enctype="multipart/form-data">
    <p>Имя:</p><input type='text' name='name'/>
    <p>Логин:</p><input type='text' name='login'/>
    <p>Почта:</p><input type='text' name='email'/>
    <p>Фото:</p><input type="file" name="file"/>
    <p><input type='submit'/></p>
</form>
</body>
</html>