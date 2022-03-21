<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Рекомендовать книгу</title>
    <style>
        <%@include file="../styles/style.css" %>
    </style>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/books.js"></script>
</head>
<body>
<%@include file="header.jsp" %>
<div class="recommendBook">
    <div class="container">
        <div class="recommendBook-inner">
            <div class="recommendBook-title title">Рекомендация</div>
            <div class="recommendBook-select">
                <label class="select" for="selectReader">
                    <select id="selectReader" required="required">
                        <option value="" disabled="disabled" selected="selected">Выберите читателя</option>
                        <c:forEach var="reader" items="${readers}">
                            <option value="${reader.getId()}">${reader.getLogin()}</option>
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
            <div class="recommendBook-select">
                <label class="select" for="selectBook">
                    <select id="selectBook" required="required">
                        <option value="" disabled="disabled" selected="selected">Выберите книгу</option>
                        <c:forEach var="book" items="${myBooks}">
                            <option value="${book.getId()}">${book.getTitle()}</option>
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
            <textarea id="textRecommendation" class="recommendBook-text"></textarea>
            <button onclick="recommendBook()" type="button" class="markBook-button book-button">
                Рекомендовать
            </button>

        </div>
    </div>
</div>
</body>
</html>
