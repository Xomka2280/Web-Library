<%@ page import="ru.rsreu.library.model.roles.RoleType" %>
<%@ page import="ru.rsreu.library.utils.JspHelper" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Список пользователей</title>
    <style>
        <%@include file="../styles/style.css" %>
    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/books.js"></script>
</head>
<body>
<%@include file="header.jsp" %>
<div class="users">
    <div class="container" id="users">
        <div class="users-inner">
            <div class="users-table-title table-title title">Список пользователей</div>
            <div class="users-table table">
                <div class="users-row row">
                    <div>Логин</div>
                    <div>Роль</div>
                    <div>Статус блокировки</div>
                    <div>Статус авторизации</div>
                </div>
                <c:forEach var="user" items="${users}">
                    <div class="users-row row">
                        <div>${user.getLogin()}</div>
                        <div>${user.getRole().getTitle()}</div>
                        <div>${JspHelper.giveUserStatusBlock(user.isBlocked())}</div>
                        <div>${JspHelper.giveAuthorizationStatus(user.isAuthorized())}</div>
                        <c:if test="${sessionScope.user.getRole().equals(RoleType.MODERATOR.getRole())}">
                            <c:choose>
                                <c:when test="${user.isBlocked()}">
                                    <button class="input-button-a" onclick="changeBlock(${user.getId()})" type="button">Разблокировать</button>
                                </c:when>
                                <c:otherwise>
                                    <button class="input-button-a" onclick="changeBlock(${user.getId()})" type="button">Заблокировать</button>
                                </c:otherwise>
                            </c:choose>
                        </c:if>
                        <c:if test="${sessionScope.user.getRole().equals(RoleType.ADMIN.getRole())}">
                            <a href="editUser?id=${user.getId()}" class="input-button-a" href="#">Редактировать</a>
                            <button onclick="deleteUser(${user.getId()})" class="input-button-a" type="button">Удалить</button>
                        </c:if>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
</body>
</html>
