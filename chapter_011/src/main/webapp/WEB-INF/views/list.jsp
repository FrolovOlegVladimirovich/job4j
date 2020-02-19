<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>All users</title>
</head>
<body>
<c:set var="message" value='${requestScope["message"]}' />
<c:if test="${message != null}">
    <c:out value="${message}" /><br/>
</c:if>
<p>Пользователи</p>
<table style="border: 1px solid black;" cellpadding="1" cellspacing="1" border="1">
    <tr>
        <th>Имя</th>
        <th>Логин</th>
        <th>Почта</th>
    </tr>
    <c:forEach items="${users}" var="user">
        <tr>
            <td><c:out value="${user.name}" /></td>
            <td><c:out value="${user.login}" /></td>
            <td><c:out value="${user.email}" /></td>
            <td>
                <form action="${pageContext.request.contextPath}/edit" method="post">
                    <input type="hidden" name="id" value="<c:out value="${user.id}" />">
                    <input type="hidden" name="name" value="<c:out value="${user.name}" />">
                    <input type="hidden" name="action" value="updateView">
                    <input type="submit" value="Редактировать">
                </form>
            </td>
            <td>
                <form action="${pageContext.request.contextPath}/edit" method="post">
                    <input type="hidden" name="id" value="<c:out value="${user.id}" />">
                    <input type="hidden" name="action" value="delete">
                    <input type="submit" value="Удалить">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>