<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Catalog</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/general.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/roomCatalog.css" type="text/css">

</head>
<body>
<jsp:include page="../components/header.jsp"/>
<div class="catalog">
    <table>
        <tr>
            <th>Room number</th>
            <th>Occupy room</th>
        </tr>
            <tr>
                <td>
                        111
                </td>
                <td>
                    <form
                          method="post">
                        <input type="hidden" id="roomId" name="roomId" value="">
                        <input class="occupyBtn" type="submit" value="Occupy">
                    </form>
                </td>
            </tr>
    </table>
</div>
</body>
</html>
