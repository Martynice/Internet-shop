<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All users:</title>
</head>
<body>
<%@include file="../header.jsp"%>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Login</th>
        <th>Password</th>
    </tr>
    <c:forEach var = "user" items = "${users}">
        <tr>
            <td>
                <c:out value = "${user.id}"/>
            </td>
            <td>
                <c:out value = "${user.name}"/>
            </td>
            <td>
                <c:out value = "${user.login}"/>
            </td>
            <td>
                <c:out value = "${user.password}"/>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/users/delete?id=${user.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
<a href="${pageContext.request.contextPath}/registration">Sign up</a><br>
<a href="${pageContext.request.contextPath}/">Main page</a><br>
<a href="${pageContext.request.contextPath}/product/all">All products</a><br>
</body>
</html>
