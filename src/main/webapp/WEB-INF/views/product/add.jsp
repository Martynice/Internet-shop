<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="../header.jsp"%>
<form method="post" action="${pageContext.request.contextPath}/product/add">
    <table>
        <tr>
            <td>
                <label>Name: </label>
            </td>
            <td>
                <input type="text" name="name" required>
            </td>
        </tr>
        <tr>
            <td>
                <label>Price: </label>
            </td>
            <td>
                <input type="number" name="price" required>
            </td>
        </tr>
        <tr>
            <td>
                <br><button type="submit">Add</button>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
