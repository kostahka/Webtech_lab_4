<%@ page import="by.bsuir.lab4.entity.Room" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Catalog</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/general.css" type="text/css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/roomCatalog.css" type="text/css">
</head>
<body>
<jsp:include page="/WEB-INF/components/header.jsp"/>
<div class="catalog">
    <table>
        <tr>
            <th>Room number</th>
            <th>Occupy room</th>
        </tr>
        <jsp:useBean id="roomList" scope="request" type="java.util.List"/>
        <%
            for (Room room : (List<Room>)roomList) {
        %>
        <tr>
            <td>
                <%=room.getRoomNumber()%>
            </td>
            <td>
                <form method="post" action="catalog">
                    <input type="hidden" id="roomId" name="roomId"
                           value="<%=room.getId()%>">
                    <%if(room.getOccupied()){%>
                    <label>Occupied</label>
                    <%} else {%>
                    <input class="occupyBtn" type="submit" value="Occupy">
                    <%}%>
                </form>
            </td>
        </tr>
        <%}%>
    </table>
</div>
</body>
</html>
