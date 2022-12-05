<%@ page import="by.bsuir.lab4.entity.Room" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/general.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/roomCatalogEditor.css" type="text/css">

    <title>Catalog Editor</title>
</head>
<body>
<jsp:include page="/WEB-INF/components/header.jsp"/>
<div class="catalog">
    <div>
        <table>
            <tr>
                <th>Room number</th>
                <th>Occupied</th>
                <th></th>
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
                        <%if(room.getOccupied()){%>
                        <label>Occupied</label>
                        <%} else {%>
                        <label>Free</label>
                        <%}%>
                    </td>
                    <td>
                        <%if(room.getOccupied()){%>
                        <form method="post" action="edit">
                            <input type="hidden" id="roomId" name="roomId"
                                   value="<%=room.getId()%>">
                            <input class="deoccupyBtn" type="submit" value="deoccupy">
                        </form>
                        <%} else {%>
                        <label>Free</label>
                        <%}%>

                    </td>
                </tr>
            <%}%>
        </table>
    </div>
    <div class="addPanel">
        <button class="addBtn"
                onclick="document.getElementById('addRoom').style.display='flex'">Add room</button>
    </div>
    <jsp:include page="/WEB-INF/components/addRoomForm.jsp"/>
</body>
</html>
