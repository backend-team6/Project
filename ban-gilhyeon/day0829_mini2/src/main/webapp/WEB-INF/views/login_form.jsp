<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login page</title>
</head>

<body>
<h2>login page</h2>
<form action="<%=request.getContextPath()%>/member/login" method="post">
    <table>
        <tr>
            <td> ID : </td>
            <td><input type="text" name="user_id" value="test1"/> </td>
        </tr>

        <tr>
            <td> PW : </td>
            <td> <input type="password" name="user_pw" value="1234"/> </td>
        </tr>

        <tr>
            <td colspan="2">
                <input type="submit" value="LOGIN">
            </td>
        </tr>
    </table>
</form>
</body>
</html>