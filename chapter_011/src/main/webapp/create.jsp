<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create user</title>
</head>
<body>
<%String message = request.getParameter("message");%>
<% if (message != null) {%>
<%=message%><br/><%;
}%>
<p>Введите данные нового пользователя</p>
<form action="<%=request.getContextPath()%>/create" method="post">
    <p>Имя:</p><input type='text' name='name'/>
    <p>Логин:</p><input type='text' name='login'/>
    <p>Почта:</p><input type='text' name='email'/>
    <p><input type='submit'/></p>
</form>
</body>
</html>