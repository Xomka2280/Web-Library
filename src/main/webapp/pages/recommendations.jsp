<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="ru.rsreu.library.model.roles.RoleType" %>
<!DOCTYPE html>
<html>
<head>
    <title>Рекомендации</title>
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
            <div class="table-title title">Рекомендации</div>
            <div class="recommendations-table table">
                <div class="recommendations-row row">
                    <div>От кого</div>
                    <div>Кому</div>
                    <div>Книга</div>
                    <div>Текст рекомендации</div>
                </div>
                <c:forEach var="recommendation" items="${recommendations}">
                    <div class="recommendations-row row">
                        <div>${recommendation.getReader().getLogin()}</div>
                        <div>${recommendation.getSender().getLogin()}</div>
                        <div>${recommendation.getBook().getTitle()}</div>
                        <div>${recommendation.getText()}</div>
                        <c:if test="${sessionScope.user.getRole().equals(RoleType.MODERATOR.getRole())}">
                            <button type="button" onclick="deleteRecommendation(${recommendation.getId()})">Удалить
                            </button>
                        </c:if>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
</body>
</html>
