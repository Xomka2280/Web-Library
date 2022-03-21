<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Оценка книги</title>
    <style>
        <%@include file="../styles/style.css" %>
    </style>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/books.js"></script>
</head>
<body>
<%@include file="header.jsp" %>
<div class="markBook">
    <div class="container">
        <div class="markBook-inner">
            <div class="markBook-title title">Оценивание книги</div>
            <div class="markBook-select">
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
            <div class="markBook-rate">
                <label for="opt1" class="radio">
                    <input type="radio" name="rdo" value="1" id="opt1" class="hidden"/>
                    <span class="label"></span>1
                </label>

                <label for="opt2" class="radio">
                    <input type="radio" name="rdo" value="2" id="opt2" class="hidden"/>
                    <span class="label"></span>2
                </label>

                <label for="opt3" class="radio">
                    <input type="radio" name="rdo" value="3" id="opt3" class="hidden"/>
                    <span class="label"></span>3
                </label>

                <label for="opt4" class="radio">
                    <input type="radio" name="rdo" value="4" id="opt4" class="hidden"/>
                    <span class="label"></span>4
                </label>

                <label for="opt5" class="radio">
                    <input type="radio" name="rdo" value="5" id="opt5" class="hidden"/>
                    <span class="label"></span>5
                </label>

            </div>
            <button type="button" onclick="rateBook()" class="markBook-button book-button">
                Оценить
            </button>

        </div>
    </div>
</div>
</body>
</html>
