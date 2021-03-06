<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Shopping Cart</title>
</head>
<body>
<%@include file="../header.jsp"%>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Price</th>
    </tr>
    <c:forEach var = "product" items = "${shoppingCart.products}">
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
            <td>
                <a href="${pageContext.request.contextPath}/shopping-cart/product/delete?id=${product.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
<br><a href="${pageContext.request.contextPath}/order/complete?id=${shoppingCart.userId}"><button class="btn btn-primary">Order</button></a>
</body>
</html>
