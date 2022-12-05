<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="naming" var="naming"/>

<fmt:message bundle="${naming}" key="login.cannotLogin" var="cannotLogin"/>
<fmt:message bundle="${naming}" key="login.login" var="login"/>
<fmt:message bundle="${naming}" key="auth.password" var="plpassword"/>
<fmt:message bundle="${naming}" key="auth.username" var="plusername"/>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/general.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/login.css" type="text/css">
    <title>Login</title>
</head>
<body>
<jsp:include page="/WEB-INF/components/header.jsp"/>
<div class="infoLabel">
    <div class="loginForm">
        <form method="post" action="login">
            <label>
                <% if(request.getAttribute("loginError") != null) {%>
                ${cannotLogin}
                <% }%>
            </label>
            <div>
                <input class="signInForm" type="text" id="username" name="username"
                       placeholder="${plusername}" required>
            </div>

            <div>
                <input class="signInForm" type="password" id="password" name="password"
                       placeholder="${plpassword}" required>
            </div>
            <input id="signInButton" class="submitBtn" type="submit" value="${login}">
        </form>
    </div>
</div>
</body>
</html>
