<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Оценки</title>
    <style>
        <%@include file="../styles/style.css" %>
    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/books.js"></script>
</head>
<body>
<%@include file="header.jsp" %>
<div class="recommendations">
    <div class="container">
        <div class="recommendations-inner">
            <div class="table-title title">Оценки</div>
            <div class="recommendations-table table">
                <div class="recommendations-row rates-row row">
                    <div>Книга</div>
                    <div>Оценка</div>
                </div>
                <c:forEach var="rate" items="${rates}">
                    <div class="recommendations-row rates-row row">
                        <div>${rate.key}</div>
                        <div>${rate.value}</div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
</body>
</html>