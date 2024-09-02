<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>회원가입 화면</title>
</head>
<body>
<h2>회원가입 화면입니다.</h2>
<form action="<%=request.getContextPath()%>/member/login" method="post">
    <table>
        <tr>
            <td>ID :</td>
            <td><input type="text" name="loginId"></td>
        </tr>
        <tr>
            <td>PW :</td>
            <td><input type="password" name="password"></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="회원가입">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
