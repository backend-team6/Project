<%--
  Created by IntelliJ IDEA.
  User: yeonsu
  Date: 8/19/24
  Time: 10:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>로그인</title>
</head>
<body>
<%
    Cookie[] cookies = request.getCookies();
    String value = null;

    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().contains("cookie")) {
                value = cookie.getValue();
                break;
            }
        }
    }
%>
<form action="<%=request.getContextPath()%>/user?action=login" method="post">
    ID : <label>
    <%
        if (value == null) {
    %>
    <input type="text" name="loginId">
    <%
        } else {
    %>
    <input type="text" name="loginId" value="<%=value%>">
    <%
        }
    %>
</label><br>
    PW : <label>
    <input type="password" name="loginPw">
</label><br>
    <input type="submit" value="로그인">
    <br>
    <input type="checkbox" id="check" name="check" checked>[아이디 기억하기]
</form>
<br><a href="<%=request.getContextPath()%>/user?action=signup">[회원가입하기]</a><br>
<a href="<%=request.getContextPath()%>">[메인으로]</a>
</body>
</html>
