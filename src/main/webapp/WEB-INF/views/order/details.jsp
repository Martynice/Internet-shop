<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Details</title>
</head>
<body>
<%@include file="../header.jsp"%>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Price</th>
    </tr>
    <c:forEach var = "product" items = "${orderProducts}">
        <tr>
            <td>
                <c:out value = "${product.id}"/>
            </td>
            <td>
                <c:out value = "${product.name}"/>
            </td>
            <td>
                <c:out value = "${product.price}"/>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
