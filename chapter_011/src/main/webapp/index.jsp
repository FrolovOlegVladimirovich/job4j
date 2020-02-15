<%@ page import="ru.job4j.crudservlet.User" %>
<%@ page import="ru.job4j.crudservlet.MemoryStore" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Echo</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/echo" method="post">
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
    <% for (User user : MemoryStore.INSTANCE.findAll()) {%>
    <tr>
        <td><%=user.getLogin()%></td>
        <td><%=user.getEmail()%></td>
    </tr>
    <% } %>
</table>
</body>
</html>