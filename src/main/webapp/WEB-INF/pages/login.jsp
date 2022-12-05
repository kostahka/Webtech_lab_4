<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/general.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/login.css" type="text/css">
    <title>Login</title>
</head>
<body>
<jsp:include page="../components/header.jsp"/>
<div class="infoLabel">
    <div class="loginForm">
        <form method="get">
            <div>
                <input class="signInForm" type="text" id="username" name="username" placeholder="login"
                       required>
            </div>

            <div>
                <input class="signInForm" type="password" id="password" name="password"
                       placeholder="password"
                       required>
            </div>
            <input id="signInButton" class="submitBtn" type="submit" value="Login">
        </form>
    </div>
</div>
</body>
</html>
