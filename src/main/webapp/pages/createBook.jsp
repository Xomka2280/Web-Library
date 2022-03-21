<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Создать книгу</title>
    <style>
        <%@include file="../styles/style.css" %>
    </style>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/books.js"></script>
</head>
<body>
<%@include file="header.jsp" %>
<div class="createBook">
    <div class="container">
        <div class="createBook-inner">
            <div class="createBook-title title">Создание книги</div>
            <div class="createBook-name">
                <div class="createBook-name-title">Название:</div>
                <input type="text" id="title" class="createBook-name-input" maxlength="25">
            </div>
            <div class="createBook-author">
                <div class="createBook-author-title">Автор:</div>
                <input type="text" id="author" class="createBook-author-input" maxlength="25">
            </div>
            <button type="button" onclick="createBook()" class="createBook-button book-button">
                Создать книгу
            </button>

        </div>
    </div>
</div>
</body>
</html>
