<%--
  Created by IntelliJ IDEA.
  User: banny_x2
  Date: 2024-08-19
  Time: 오전 11:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>회원가입 화면</title>
</head>
<body>

<form action="<%=request.getContextPath()%>//signup.do" method="post">
    <input type ="text" placeholder="id 입력" id="user_id" name="user_id"/><br>
    <input type ="text" placeholder="pw 입력" id="user_pw" name="user_pw"/><br>
    <input type="submit" value="회원가입">
</form>

</body>
</html>
