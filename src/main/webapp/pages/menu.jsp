<%@ page import="ru.rsreu.library.model.users.User" %>
<%@ page import="ru.rsreu.library.model.roles.RoleType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Главное меню</title>
    <style>
        <%@include file="../styles/style.css" %>
    </style>
</head>
<body>
<%@include file="header.jsp" %>
<div class="menu">
    <div class="container">
        <div class="menu-inner">
            <div class="menu-title title">
                Главное меню
            </div>
            <div class="menu-list">
                <%
                    User user = (User) session.getAttribute("user");
                %>
                <c:choose>
                    <c:when test="${user.getRole().equals(RoleType.ADMIN.getRole())}">
                        <a href="users" class="menu-item">Пользователи</a>
                        <a href="createUser" class="menu-item">Создать пользователя</a>
                    </c:when>
                    <c:when test="${user.getRole().equals(RoleType.MODERATOR.getRole())}">
                        <a href="users" class="menu-item">Пользователи</a>
                        <a href="recommendations" class="menu-item">Рекомендации</a>
                        <a href="rates" class="menu-item">Оценки</a>
                    </c:when>
                    <c:when test="${user.getRole().equals(RoleType.READER.getRole())}">
                        <a href="myBooks" class="menu-item">Мои книги</a>
                        <a href="availableBooks" class="menu-item">Доступные книги</a>
                        <a href="giveBook" class="menu-item">Оставить заявку на книгу</a>
                        <a href="recommendBook" class="menu-item">Порекомендовать книгу</a>
                        <a href="rateBook" class="menu-item">Оценить книгу</a>
                        <a href="notices" class="menu-item">Уведомления</a>
                        <a href="recommendations" class="menu-item">Рекомендации</a>
                        <a href="ratesBooks" class="menu-item">Оценки</a>
                    </c:when>
                    <c:when test="${user.getRole().equals(RoleType.LIBRARIAN.getRole())}">
                        <a href="createBook" class="menu-item">Добавить книгу</a>
                        <a href="books" class="menu-item">Список книг</a>
                        <a href="recommendBook" class="menu-item">Порекомендовать книгу</a>
                    </c:when>
                </c:choose>
            </div>

        </div>
    </div>
</div>
</body>
</html>
