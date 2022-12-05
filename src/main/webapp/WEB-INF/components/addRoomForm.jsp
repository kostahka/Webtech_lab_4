<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="naming" var="naming"/>

<fmt:message bundle="${naming}" key="table.roomNumber" var="roomNumber"/>
<fmt:message bundle="${naming}" key="modal.add" var="add"/>
<fmt:message bundle="${naming}" key="modal.cancel" var="cancel"/>

<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/addModal.css" type="text/css">
</head>
<body>
<div id="addRoom" class="modal">
    <div class="modalWnd">
        <form method="post" action="edit">
            <label for="roomNumber"><b>${roomNumber}</b></label>
            <input class="numberInput" type="text" id="roomNumber" name="roomNumber"
                   pattern="^([0-9]{3})$" required>
            <input class="prcButton" type="submit" value="${add}"/>
        </form>
        <div>
            <button class="cancelButton" onclick="document.getElementById('addRoom').style.display='none'">${cancel}</button>
        </div>
    </div>
</div>

</body>
</html>
