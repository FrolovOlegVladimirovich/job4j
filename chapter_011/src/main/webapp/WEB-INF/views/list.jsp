<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>All users</title>
</head>
<body>
<c:set var="model" value='${sessionScope["model"]}' />
<p>Авторизован пользователь:
    <c:out value="${model.login}"/>
</p>
<p>Роль:
    <c:out value="${model.role}"/>
</p>
<c:if test="${'user'.equals(model.role)}">
    <form action="${pageContext.request.contextPath}/edit" method="post">
        <input type="hidden" name="id" value="<c:out value="${model.id}" />">
        <input type="hidden" name="name" value="<c:out value="${model.name}" />">
        <input type="hidden" name="role" value="<c:out value="${model.role}" />">
        <input type="hidden" name="action" value="updateView">
        <input type="submit" value="Редактировать">
    </form>
    <form action="${pageContext.servletContext.contextPath}/logout" method="post">
        <input type="submit" value="Выйти">
    </form>
</c:if>
<p>Пользователи</p>
<table style="border: 1px solid black;" cellpadding="1" cellspacing="1" border="1">
    <tr>
        <th>Имя</th>
        <th>Логин</th>
        <th>Почта</th>
        <th>Фото</th>
        <c:if test="${'admin'.equals(model.role)}">
            <th>Роль</th>
            <th>
                <form action="${pageContext.servletContext.contextPath}/create" method="get">
                    <input type="submit" value="Создать нового пользователя">
                </form>
            </th>
            <th>
                <form action="${pageContext.servletContext.contextPath}/logout" method="post">
                    <input type="submit" value="Выйти">
                </form>
            </th>
        </c:if>
    </tr>
    <c:forEach items="${users}" var="user">
        <tr>
            <td><c:out value="${user.name}" /></td>
            <td><c:out value="${user.login}" /></td>
            <td><c:out value="${user.email}" /></td>
            <td>
                <img src="${pageContext.servletContext.contextPath}/download?name=${user.photoId}" width="100px" height="100px"/>
                <br/>
                <form action="${pageContext.servletContext.contextPath}/download?name=${user.photoId}" method="post">
                    <input type="submit" value="Скачать фото">
                </form>
            </td>
            <c:if test="${('admin'.equals(model.role))}">
                <td>
                    <form action="${pageContext.request.contextPath}/edit" method="post">
                        <select name="role">
                            <c:if test="${'admin'.equals(user.role)}">
                                <option value="admin" hidden="">admin</option>
                                <option value="user">user</option>
                            </c:if>
                            <c:if test="${'user'.equals(user.role)}">
                                <option value="user" hidden="">user</option>
                                <option value="admin">admin</option>
                            </c:if>
                        </select>
                        <input type="hidden" name="id" value="${user.id}">
                        <input type="hidden" name="name" value="${user.name}">
                        <input type="hidden" name="action" value="update">
                        <br/>
                        <input type="submit" value="Изменить">
                    </form>
                </td>
                <td>
                    <form action="${pageContext.request.contextPath}/edit" method="post">
                        <input type="hidden" name="id" value="<c:out value="${user.id}" />">
                        <input type="hidden" name="name" value="<c:out value="${user.name}" />">
                        <input type="hidden" name="role" value="<c:out value="${user.role}" />">
                        <input type="hidden" name="action" value="updateView">
                        <input type="submit" value="Редактировать">
                    </form>
                </td>
                <td>
                    <form action="${pageContext.request.contextPath}/edit" method="post">
                        <input type="hidden" name="id" value="<c:out value="${user.id}" />">
                        <input type="hidden" name="photoId" value="<c:out value="${user.photoId}" />">
                        <input type="hidden" name="action" value="delete">
                        <input type="submit" value="Удалить">
                    </form>
                </td>
            </c:if>
        </tr>
    </c:forEach>
</table>
</body>
</html>