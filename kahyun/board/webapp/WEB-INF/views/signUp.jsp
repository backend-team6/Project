<%--
  Created by IntelliJ IDEA.
  User: kimkahyun
  Date: 8/19/24
  Time: 12:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>회원가입</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/user.do?action=signUp" method="post">
    닉네임 : <input type="text" name="name"/><br>
    ID : <input type="text" name="id"/><br>
    PW : <input type="password" name="password"/><br>
    <input type="submit" value="로그인"/>
</form>
</body>
</html>
