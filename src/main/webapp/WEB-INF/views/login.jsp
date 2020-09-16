<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<%@include file="header.jsp"%>
<h1>Login page</h1>
<h4 style="color:red">${message}</h4>
<form action="${pageContext.request.contextPath}/login" method="post">
    <table>
        <tr>
            <td>
                <label>Login: </label>
            </td>
            <td>
                <input type="text" name="login" class="form-control" required>
            </td>
        </tr>
        <tr>
            <td>
                <label>Password: </label>
            </td>
            <td>
                <input type="password" name="pwd" class="form-control" required>
            </td>
        </tr>
        <tr>
            <td>
                <br><button class="btn btn-primary" type="submit">Login</button>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
