<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Current user's order</title>
</head>
<body>
<%@include file="../header.jsp"%>
<table border="1">
    <tr>
        <th>ID</th>
        <th>User ID</th>
    </tr>
    <c:forEach var = "order" items = "${orders}">
        <tr>
            <td>
                <c:out value = "${order.id}"/>
            </td>
            <td>
                <c:out value = "${order.userId}"/>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/order/details?id=${order.id}">Details</a>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/order/delete?id=${order.id}"><button>Delete</button></a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
