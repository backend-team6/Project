<%--
  Created by IntelliJ IDEA.
  User: yeonsu
  Date: 8/19/24
  Time: 11:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>회원가입</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/user?action=signup" method="post">
    ID : <label>
    <input type="text" name="loginId">
</label><br>
    PW : <label>
    <input type="password" name="loginPw">
</label><br>
    <input type="submit" value="회원가입">
</form>
<br><a href="<%=request.getContextPath()%>">[메인으로]</a>
</body>
</html>
