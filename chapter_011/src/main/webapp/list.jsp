<%@ page import="ru.job4j.crudservlet.User" %>
<%@ page import="ru.job4j.crudservlet.MemoryStore" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All users</title>
</head>
<body>
<%String message = request.getParameter("message");%>
<% if (message != null) {%>
<%=message%><br/><%;
}%>
<p>Пользователи</p>
<table style="border: 1px solid black;" cellpadding="1" cellspacing="1" border="1">
    <tr>
        <th>Имя</th>
        <th>Логин</th>
        <th>Почта</th>
    </tr>
    <% for (User user : MemoryStore.INSTANCE.findAll()) {%>
    <tr>
        <td><%=user.getName()%></td>
        <td><%=user.getLogin()%></td>
        <td><%=user.getEmail()%></td>
        <td>
            <form action="<%=request.getContextPath()%>/edit.jsp" method="get">
                <input type="hidden" name="id" value="<%=user.getId()%>">
                <input type="hidden" name="name" value="<%=user.getName()%>">
                <input type="hidden" name="action" value="update">
                <input type="submit" value="Редактировать">
            </form>
        </td>
        <td>
            <form action="<%=request.getContextPath()%>/edit" method="get">
                <input type="hidden" name="id" value="<%=user.getId()%>">
                <input type="hidden" name="action" value="delete">
                <input type="submit" value="Удалить">
            </form>
        </td>
    </tr>
    <% } %>
</table>
</body>
</html>