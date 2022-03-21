<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Редактирование пользователя</title>
    <style>
        <%@include file="../styles/style.css" %>
    </style>
</head>
<body>
<%@include file="header.jsp" %>
<div class="createUser">
    <div class="container">
        <form class="createUser-inner" action="editUser?id=${editUser.getId()}" method="post">
            <div class="createUser-block">
                <div class="createUser-title title">Редактирование пользователя</div>
                <div class="createUser-item">
                    <div class="createUser-item-title"> Логин:</div>
                    <div><input class="createUser-input" type="text" name="login" value="${editUser.getLogin()}"></div>
                </div>
                <div class="createUser-item">
                    <div class="createUser-item-title"> Пароль:</div>
                    <div><input class="createUser-input" type="text" name="password" value="${editUser.getPassword()}"></div>
                </div>
                <div class="createUser-role">
                    <label class="select" for="slct">
                        <select name="format" id="slct" required="required">
                            <c:forEach var="item" items="${requestScope.roles}">
                                <c:choose>
                                    <c:when test="${editUser.getRole().equals(item)}">
                                        <option value="${item.getId()}" selected>${item.getTitle()}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${item.getId()}">${item.getTitle()}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                        <svg>
                            <use xlink:href="#select-arrow-down"></use>
                        </svg>
                    </label>
                    <svg class="sprites">
                        <symbol id="select-arrow-down" viewbox="0 0 10 6">
                            <polyline points="1 1 5 5 9 1"></polyline>
                        </symbol>
                    </svg>
                </div>
                <div class="createUser-item createUser-item-submit">
                    <input class="input-button-a createUser-submit" type="submit" value="Сохранить"/>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>

