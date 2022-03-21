<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ru.rsreu.library.utils.JspHelper" %>
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
<div class="notice">
    <div class="container">
        <div class="notice-inner">
            <div class="table-title title">Уведомления</div>
            <div class="notice-table table">
                <div class="notice-row row">
                    <div>Книга</div>
                    <div>Статус</div>
                </div>
                <c:forEach var="notice" items="${notices}">
                    <div class="notice-row row">
                        <div>${notice.getBook().getTitle()}</div>
                        <div>${JspHelper.giveAvailableStatus(notice.isAvailable())}</div>
                        <button type="button" onclick="deleteNotice(${notice.getId()})">Удалить уведомление</button>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
</body>
</html>
