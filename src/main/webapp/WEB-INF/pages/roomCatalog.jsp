<%@ page import="by.bsuir.lab4.entity.Room" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="naming" var="naming"/>

<fmt:message bundle="${naming}" key="table.roomNumber" var="roomNumber"/>
<fmt:message bundle="${naming}" key="table.occupyRoom" var="occupyRoom"/>
<fmt:message bundle="${naming}" key="table.deoccupy" var="deoccupy"/>
<fmt:message bundle="${naming}" key="table.free" var="free"/>
<fmt:message bundle="${naming}" key="table.occupied" var="occupied"/>
<fmt:message bundle="${naming}" key="table.occupy" var="occupy"/>

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
            <th>${roomNumber}</th>
            <th>${occupyRoom}</th>
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
                    <label>${occupied}</label>
                    <%} else {%>
                    <input class="occupyBtn" type="submit" value="${occupy}">
                    <%}%>
                </form>
            </td>
        </tr>
        <%}%>
    </table>
</div>
</body>
</html>
