<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>Echo</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/echo" method="post">
    Login: <input type="text" name="login"><br/>
    Email: <input type="text" name="email"><br/>
    <input type="submit">
</form>
<br/>
<table style="border: 1px solid black;" cellpadding="1" cellspacing="1" border="1">
    <tr>
        <th>Логин</th>
        <th>Почта</th>
    </tr>
    <c:forEach items="${users}" var="user">
        <tr>
            <td><c:out value="${user.login}" /></td>
            <td><c:out value="${user.email}" /></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>