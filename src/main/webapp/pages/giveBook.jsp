<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Получение книги</title>
    <style>
        <%@include file="../styles/style.css" %>
    </style>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/books.js"></script>
</head>
<body>
<%@include file="header.jsp" %>
<div class="giveBook">
    <div class="container">
        <div class="giveBook-inner">
            <div class="giveBook-title title">Получение книги</div>
            <div class="giveBook-select">
                <label class="select" for="selectBook">
                    <select id="selectBook" required="required">
                        <c:choose>
                            <c:when test="${takenBooks.size() == 0}">
                                <option value="" disabled selected>Нет книг для заявки</option>
                            </c:when>
                            <c:otherwise>
                                <option value="" disabled selected>Выберите книгу</option>
                                <c:forEach var="book" items="${takenBooks}">
                                    <option value="${book.getId()}">${book.getTitle()}</option>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
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
            <button type="button" class="giveBook-button" onclick="createRequest()">
                Создать заявку
            </button>

        </div>
    </div>
</div>
</body>
</html>
