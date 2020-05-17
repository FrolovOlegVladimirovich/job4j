<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Список пользователей</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <script>
        function validate() {
            let result = true;
            let message = "";
            const id = $('#id');
            const name = $('#name');
            const login = $('#login');
            const email = $('#email');
            const password = $('#password');
            var role = $('#role');
            var country = $('#country');
            var city = $('#city');

            if (name.val() === '') {
                message += name.attr('name') + ' ';
            }
            if (country.val() === 'default') {
                message += country.attr('name') + ' ';
            }
            if (city.val() === 'default') {
                message += city.attr('name') + ' ';
            }
            if (id.val() === '' && '${model.role}' === 'admin') {
                if (login.val() === '') {
                    message += login.attr('name') + ' ';
                }
                if (password.val() === '') {
                    message += password.attr('name') + ' ';
                }
                if (email.val() === '') {
                    message += email.attr('name') + ' ';
                }
                if ($('#photo').get(0).files.length === 0) {
                    message += 'photo ';
                }
            }
            if (message !== '') {
                alert('Fill in required fields: ' + message);
                result = false;
            }
            if (result) {
                if (id.val() === '') {
                    addRow();
                    clearForm();
                } else {
                    if (role.val() == null) {
                        role = 'user';
                    } else {
                        role = role.val();
                    }
                    updateRow(id.val(), name.val(), role, country.val(), city.val());
                    clearForm();
                }
            }
        }

        function clearForm() {
            $('#form')[0].reset();
            $('#id').val('');
            $('#login').attr('readonly', false);
            $('#password').attr('readonly', false);
            $('#email').attr('readonly', false);
            $('#photo').attr('disabled', false);
            $("#city").html('<option value="default">Select city</option>');
        }

        function addRow() {
            var form = $('#form')[0];
            var formData = new FormData(form);
            formData.append('model', "${sessionScope["model"]}");
            $.ajax({
                type: 'POST',
                url: 'http://localhost:8080/users/create',
                data: formData,
                enctype: 'multipart/form-data',
                processData: false,
                contentType: false,
                success: function ($data) {
                    if ($data['result']) {
                        addRowOnTable(formData, $data['id'], $data['pic']);
                    }
                }
            });
        }

        function addRowOnTable(formData, id, pic) {
            const urlPhoto = "<%=request.getContextPath()%>" + "/download?name=" + pic;
            const name = formData.get('name');
            const login = formData.get('login');
            const email = formData.get('email');
            const role = formData.get('role');
            const country = formData.get('country');
            const city = formData.get('city');

            var tbody = document.getElementById('userList').getElementsByTagName("TBODY")[0];
            var row = document.createElement("TR");
            row.setAttribute('id', id + "_row");

            var tdPic = document.createElement("TD");

            var inputPhotoId = document.createElement("INPUT");
            inputPhotoId.setAttribute('type', "hidden");
            inputPhotoId.setAttribute('name', "photoId");
            inputPhotoId.setAttribute('id', id + "_photoId");
            inputPhotoId.setAttribute('value', pic);

            var a = document.createElement("A");
            a.setAttribute('href', urlPhoto);
            a.setAttribute('download', "");
            a.setAttribute('title', "Click to download");
            var img = document.createElement('IMG');
            img.setAttribute('src', urlPhoto);
            img.setAttribute('width', "100px");
            img.setAttribute('height', "100px");
            a.appendChild(img);
            tdPic.appendChild(inputPhotoId);
            tdPic.appendChild(a);
            row.appendChild(tdPic);

            var tdName = document.createElement("TD");
            tdName.setAttribute('id', id + "_name");
            tdName.appendChild(document.createTextNode(name));
            row.appendChild(tdName);

            var tdLogin = document.createElement("TD");
            tdLogin.setAttribute('id', id + "_login");
            tdLogin.appendChild(document.createTextNode(login));
            row.appendChild(tdLogin);

            var tdEmail = document.createElement("TD");
            tdEmail.setAttribute('id', id + "_email");
            tdEmail.appendChild(document.createTextNode(email));
            row.appendChild(tdEmail);

            var tdCountry = document.createElement("TD");
            tdCountry.setAttribute('id', id + "_country");
            tdCountry.appendChild(document.createTextNode(country));
            row.appendChild(tdCountry);

            var tdCity = document.createElement("TD");
            tdCity.setAttribute('id', id + "_city");
            tdCity.appendChild(document.createTextNode(city));
            row.appendChild(tdCity);

            if ('admin' === "${model.role}") {
                var tdRole = document.createElement("TD");
                tdRole.setAttribute('id', id + "_role");
                tdRole.appendChild(document.createTextNode(role));
                row.appendChild(tdRole);

                var tdButtons = document.createElement("TD");
                tdButtons.setAttribute('id', id + "_buttons");

                var buttonEdit = document.createElement("BUTTON");
                buttonEdit.setAttribute('id', id + "_buttons_edit");
                buttonEdit.setAttribute('type', "button");
                buttonEdit.setAttribute('class', "btn btn-default");
                buttonEdit.setAttribute('onclick', "clearForm(); fillForm(" + id + ")");
                buttonEdit.textContent = "Редактировать";

                var buttonDelete = document.createElement("BUTTON");
                buttonDelete.setAttribute('id', id + "_buttons_delete");
                buttonDelete.setAttribute('type', "button");
                buttonDelete.setAttribute('class', "btn btn-default");
                buttonDelete.setAttribute('onclick', "clearForm(); deleteUser(" + id + ")");
                buttonDelete.textContent = "Удалить";

                tdButtons.appendChild(buttonEdit);
                tdButtons.appendChild(buttonDelete);
                row.appendChild(tdButtons);
            }
            tbody.appendChild(row);
        }

        function updateRow(id, name, role, country, city) {
            var formData = new FormData(form);
            formData.append('action', "update");
            formData.append('id', id);
            formData.append('name', name);
            formData.append('role', role);
            formData.append('country', country);
            formData.append('city', city);
            $.ajax({
                type: 'POST',
                url: 'http://localhost:8080/users/edit',
                data: formData,
                processData: false,
                contentType: false,
                success: function ($data) {
                    if ($data['result']) {
                        $(document).ready(function () {
                            $('#' + id + "_name").text(name);
                            $('#' + id + "_role").text(role);
                            $('#' + id + "_country").text(country);
                            $('#' + id + "_city").text(city);
                            if ('${model.id}' === id) {
                                $('#' + "model_name").text(name);
                            }
                        });
                    }
                }
            });
        }

        function fillForm(id) {
            var name = $('#' + id + "_name").text();
            var role = $('#' + id + "_role").text();
            var country = $('#' + id + "_country").text();
            var city = $('#' + id + "_city").text();
            if ('${model.role}' === 'admin') {
                document.getElementById('login').toggleAttribute('readonly', true);
                document.getElementById('password').toggleAttribute('readonly', true);
                document.getElementById('email').toggleAttribute('readonly', true);
                document.getElementById('role').value = role;
                document.getElementById('photo').toggleAttribute('disabled', true);
            }
            document.getElementById('id').value = id;
            document.getElementById('name').value = name;
            document.getElementById('country').value = country;
            citiesList(country);
            document.getElementById('city').value = city;
        }

        function deleteUser(id) {
            var formData = new FormData();
            formData.append('action', "delete");
            formData.append('id', id);
            formData.append('photoId', $('#' + id + '_photoId').val());
            $.ajax({
                type: 'POST',
                url: 'http://localhost:8080/users/edit',
                data: formData,
                processData: false,
                contentType: false,
                success: function ($data) {
                    if ($data['result']) {
                        $(document).ready(function () {
                            $('#' + id + "_row").remove();
                        });
                    }
                }
            });
        }

        function showHideForm() {
            var x = document.getElementById("form");
            if (x.style.display === "none") {
                x.style.display = "block";
            } else {
                x.style.display = "none";
            }
        }

        $(document).ready(function () {
            $("#country").change(function () {
                var val = $(this).val();
                citiesList(val);
            });
        });

        function citiesList(country) {
            var list = [];
            if (country !== 'default') {
                var json = ${requestScope["locations"]};
                var countries = json["locations"];
                var cities = countries[country];
                cities.forEach(function (c) {
                    list.push('<option value="' + c + '">' + c + '</option>');
                });
            } else {
                list.push('<option value="default">Select city</option>')
            }
            $("#city").html(list);
        }
    </script>
</head>
<body>
<div class="container">
    <h1 id="model_name"><c:out value="${model.name}"/></h1>
    <c:set var="model" value='${sessionScope["model"]}'/>

    <c:if test="${'user'.equals(model.role)}">
        <button id="${model.id}_buttons_edit" type="button" class="btn btn-primary"
                onclick="showHideForm(); fillForm('${model.id}')">Редактировать
        </button>
        <br><br>
    </c:if>

    <form action="${pageContext.servletContext.contextPath}/logout" method="post">
        <input type="submit" class="btn btn-primary" value="Выйти">
    </form>

    <form action="index.jsp" method="post" id="form" enctype="multipart/form-data">
        <input type="hidden" name="id" id="id" value=""/>
        <div class="form-group">
            <label for="name">Name:</label>
            <input type="text" class="form-control" id="name" placeholder="Enter your name" name="name">
        </div>
        <c:if test="${'admin'.equals(model.role)}">
            <div class="form-group">
                <label for="login">Login:</label>
                <input type="text" class="form-control" id="login" placeholder="Enter your surname" name="login">
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="text" class="form-control" id="password" placeholder="Enter password" name="password">
            </div>
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="text" class="form-control" id="email" placeholder="Enter email" name="email">
            </div>
            <div class="form-group">
                <label for="role">Role:</label>
                <select name="role" id="role">
                    <option value="user">user</option>
                    <option value="admin">admin</option>
                </select>
            </div>
            <div class="form-group">
                <label for="photo">Photo:</label>
                <input type="file" name="file" id="photo"/>
            </div>
        </c:if>
        <div class="form-group">
            <label for="country">Country:</label>
            <select name="country" id="country">
                <option value="default">Select country</option>
                <c:forEach items="${countrySet}" var="country">
                    <option value="${country}">${country}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="city">City:</label>
            <select name="city" id="city">
                <option value="default">Select city</option>
            </select>
        </div>
        <button id="button" type="button" class="btn btn-default"
                onclick="validate(); if ('${model.role}' === 'user') {showHideForm()}">Submit
        </button>
        <button id="button_clear" type="button" class="btn btn-default"
                onclick="clearForm()">Clear
        </button>
    </form>
    <c:if test="${'user'.equals(model.role)}">
        <script type="text/javascript">showHideForm()</script>
    </c:if>
    <h2>Список пользователей</h2>
    <table class="table table-striped" id="userList">
        <thead>
        <tr>
            <th>Фото</th>
            <th>Имя</th>
            <th>Логин</th>
            <th>Почта</th>
            <th>Страна</th>
            <th>Город</th>
            <c:if test="${'admin'.equals(model.role)}">
                <th>Роль</th>
            </c:if>
        </tr>
        </thead>
        <c:forEach items="${users}" var="user">
            <tr id="${user.id}_row">
                <td>
                    <input type="hidden" name="photoId" id="${user.id}_photoId" value="${user.photoId}"/>
                    <a download href="${pageContext.servletContext.contextPath}/download?name=${user.photoId}"
                       title="Click to download">
                        <img src="${pageContext.servletContext.contextPath}/download?name=${user.photoId}"
                             width="100px"
                             height="100px"/>
                    </a>
                </td>
                <td id="${user.id}_name"><c:out value="${user.name}"/></td>
                <td id="${user.id}_login"><c:out value="${user.login}"/></td>
                <td id="${user.id}_email"><c:out value="${user.email}"/></td>
                <td id="${user.id}_country"><c:out value="${user.country}"/></td>
                <td id="${user.id}_city"><c:out value="${user.city}"/></td>
                <c:if test="${'admin'.equals(model.role)}">
                    <td id="${user.id}_role"><c:out value="${user.role}"/></td>
                    <td id="${user.id}_buttons">
                        <button id="${user.id}_buttons_edit" type="button" class="btn btn-default"
                                onclick="clearForm(); fillForm('${user.id}')">Редактировать
                        </button>
                        <button id="${user.id}_buttons_delete" type="button" class="btn btn-default"
                                onclick="clearForm(); deleteUser('${user.id}')">Удалить
                        </button>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>