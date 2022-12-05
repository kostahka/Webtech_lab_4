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
                <tr>
                    <td>
                            111
                    </td>
                    <td>
                            Occupied
                    </td>
                    <td>
                        <form method="post">
                            <input type="hidden" id="roomId" name="roomId" value="1">
                            <input class="deoccupyBtn" type="submit" value="deoccupy">
                        </form>
                    </td>
                </tr>
        </table>
    </div>
    <div class="addPanel">
        <button class="addBtn"
                onclick="document.getElementById('addRoom').style.display='flex'">Add room</button>
    </div>
    <jsp:include page="/WEB-INF/components/addRoomForm.jsp"/>
</body>
</html>
