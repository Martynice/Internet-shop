<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Internet-shop</title>
  </head>
  <body>
  <%@include file="header.jsp"%>
    <a href="${pageContext.request.contextPath}/registration">Sign up</a><br>
    <a href="${pageContext.request.contextPath}/users/all">All users</a><br>
    <a href="${pageContext.request.contextPath}/product/all">All products</a><br>
    <a href="${pageContext.request.contextPath}/product/add">Add products to DB</a><br>
  </body>
</html>
