<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>join page</title>
</head>

<body>
<h2>join page</h2>
<form action="<%=request.getContextPath()%>/member/join" method="post">
    <table>
        <tr>
            <td>ID :</td>
            <td><input type="text" name="userid"/></td>
        </tr>
        <tr>
            <td>PW :</td>
            <td><input type="password" name="userpw"/></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="JOIN">
            </td>
        </tr>
    </table>
</form>
</body>
</html>