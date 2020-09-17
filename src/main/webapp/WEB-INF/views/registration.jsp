<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<%@include file="header.jsp"%>
<h1>Provide your user details</h1>
<h4 style="color:red">${message}</h4>
<form method="post" action="${pageContext.request.contextPath}/registration">
    <table>
        <tr>
            <td>
                <label>Name: </label>
            </td>
            <td>
                <input type="text" name="name" class="form-control" required>
            </td>
        </tr>
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
                <label>Repeat password:</label>
            </td>
            <td>
                <input type="password" name="pwd-repeat" class="form-control" required>
            </td>
        </tr>
        <tr>
            <td>
                <br><button class="btn btn-primary" type="submit">Register</button>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
