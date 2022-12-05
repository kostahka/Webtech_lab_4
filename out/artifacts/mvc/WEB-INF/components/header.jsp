<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/navigationBar.css" type="text/css">
</head>
<body>
<div class="navigationBar">
    <div class="navigation">
        <a href="${pageContext.servletContext.contextPath}/catalog"><button>Catalog</button></a>
        <a href="${pageContext.servletContext.contextPath}/catalog/edit"><button>Change Catalog</button></a>
    </div>
    <div class="auth">
        <a href="${pageContext.servletContext.contextPath}/login"><button>Login</button></a>
        <a href="${pageContext.servletContext.contextPath}/signUp"><button>Sign Up</button></a>
        <a><button>Sign Out</button></a>
    </div>
</div>

</body>
</html>
