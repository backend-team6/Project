<%--
  Created by IntelliJ IDEA.
  User: hgh14
  Date: 2024-08-19
  Time: 오전 10:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
</head>
<body>
<form method="post">
    <%
        Cookie[] cookies = request.getCookies();
		Cookie cookie = null;
        for (Cookie c : cookies) {
            if (c.getName().equals("loginId")) {
                cookie = c;
                break;
            }
        }
        if (cookie != null) {
%>
    ID : <input type="text" name="id" value="<%=cookie.getValue()%>"/><br>
    PW : <input type="password" name="pw"/><br>
    아이디 저장 <input type="checkbox" name="remember" checked="checked"><br>
<%
        } else {
%>
    ID : <input type="text" name="id"/><br>
    PW : <input type="password" name="pw"/><br>
    아이디 저장 <input type="checkbox" name="remember"><br>
    <%
        }
%>
  <input type="submit" value="로그인">
</form>
</body>
</html>
