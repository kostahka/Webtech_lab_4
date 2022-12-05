<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/addModal.css" type="text/css">
</head>
<body>
<div id="addRoom" class="modal">
    <div class="modalWnd">
        <form method="post" action="edit">
            <label for="roomNumber"><b>Room Number</b></label>
            <input class="numberInput" type="text" id="roomNumber" name="roomNumber"
                   pattern="^([0-9]{3})$" required>
            <input class="prcButton" type="submit" value="Add"/>
        </form>
        <div>
            <button class="cancelButton" onclick="document.getElementById('addRoom').style.display='none'">Cancel</button>
        </div>
    </div>
</div>

</body>
</html>
