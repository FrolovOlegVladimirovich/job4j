<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit user</title>
</head>
<body>
<p>Редактировать данные пользователя</p>
<form action="<%=request.getContextPath()%>/edit" method="post">
    <p>Имя:</p><input type="text" name="name" value="<%=request.getParameter("name")%>">
    <input type="hidden" name="id" value="<%=request.getParameter("id")%>">
    <input type="hidden" name="action" value="<%=request.getParameter("action")%>">
    <p><input type="submit"/></p>
</form>
</body>
</html>