<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<h1>Provide your user details</h1>
<h4 style="color:red">${message}</h4>
<form method="post" action="${pageContext.request.contextPath}/registration">
    <table>
        <tr>
            <td>
                <label>Name: </label>
            </td>
            <td>
                <input type="text" name="name">
            </td>
        </tr>
        <tr>
            <td>
                <label>Login: </label>
            </td>
            <td>
                <input type="text" name="login">
            </td>
        </tr>
        <tr>
            <td>
                <label>Password: </label>
            </td>
            <td>
                <input type="password" name="pwd">
            </td>
        </tr>
        <tr>
            <td>
                <label>Repeat password:</label>
            </td>
            <td>
                <input type="password" name="pwd-repeat">
            </td>
        </tr>
        <tr>
            <td>
                <br><button type="submit">Register</button>
            </td>
        </tr>
    </table>
</form>
<a href="${pageContext.request.contextPath}/">Main page</a><br>
<a href="${pageContext.request.contextPath}/user/all">All users</a><br>
<a href="${pageContext.request.contextPath}/product/all">All products</a><br>
</body>
</html>
