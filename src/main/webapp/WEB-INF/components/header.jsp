<%@ page import="by.bsuir.lab4.entity.Role" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="naming" var="naming"/>

<fmt:message bundle="${naming}" key="nav.catalog" var="catalog"/>
<fmt:message bundle="${naming}" key="nav.editCatalog" var="editCatalog"/>
<fmt:message bundle="${naming}" key="nav.login" var="login"/>
<fmt:message bundle="${naming}" key="nav.signUp" var="signUp"/>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/navigationBar.css" type="text/css">
</head>
<body>
<div class="navigationBar">
    <div class="navigation">
        <%
            Role role = (Role)session.getAttribute("role");
            if(role != null && (role.equals(Role.USER) || role.equals(Role.ADMIN))){
        %>
        <a href="${pageContext.servletContext.contextPath}/catalog"><button>${catalog}</button></a>
        <%
            }
        %>
        <%
            if(role != null && role.equals(Role.ADMIN)){
        %>
        <a href="${pageContext.servletContext.contextPath}/catalog/edit"><button>${editCatalog}</button></a>
        <%
            }
        %>
    </div>
    <div class="auth">
        <a href="${pageContext.servletContext.contextPath}/login"><button>${login}</button></a>
        <a href="${pageContext.servletContext.contextPath}/signUp"><button>${signUp}</button></a>
        <div class="dropDown">
            <form action="" method="post">
                <div class="dropDownContent-language">
                    <button name="changeLanguage" value="RU" type="submit">
                        Русский
                    </button>
                    <button name="changeLanguage" value="EN" type="submit">
                        English
                    </button>
                </div>
            </form>

        </div>
    </div>
</div>

</body>
</html>
