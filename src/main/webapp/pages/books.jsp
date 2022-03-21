<%@ page import="ru.rsreu.library.model.users.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Список доступных книг</title>
    <style>
        <%@include file="../styles/style.css" %>
    </style>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/books.js"></script>
</head>
<body>
<%@include file="header.jsp" %>
<div class="books">
    <div class="container">
        <div class="books-inner">
            <%
                String link = (String) request.getAttribute("link");
            %>
            <div class="table-title title">Список доступных книг</div>
            <div class="books-table table">
                <div class="books-row row">
                    <div>Название</div>
                    <div>Автор</div>
                </div>
                <c:choose>
                    <c:when test="${link.equals(\"availableBooks\")}">
                        <c:forEach var="book" items="${availableBooks}">
                            <div class="books-row row">
                                <div>${book.getTitle()}</div>
                                <div>${book.getAuthor()}</div>
                                <button type="button" onclick="takeBook(${book.getId()})">Взять книгу</button>
                            </div>
                        </c:forEach>
                    </c:when>
                    <c:when test="${link.equals(\"myBooks\")}">
                        <c:forEach var="book" items="${books}">
                            <div class="books-row row">
                                <div>${book.getTitle()}</div>
                                <div>${book.getAuthor()}</div>
                                <button type="button" onclick="returnBook(${book.getId()})">Отдать книгу</button>
                            </div>
                        </c:forEach>
                    </c:when>
                    <c:when test="${link.equals(\"books\")}">
                        <c:forEach var="book" items="${books}">
                            <div class="books-row row">
                                <div>${book.getTitle()}</div>
                                <div>${book.getAuthor()}</div>
                                <button type="button" onclick="deleteBook(${book.getId()})">Списать</button>
                            </div>
                        </c:forEach>
                    </c:when>
                </c:choose>
            </div>
        </div>
    </div>
</div>
</body>
</html>
