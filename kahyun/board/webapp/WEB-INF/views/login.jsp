<%--
  Created by IntelliJ IDEA.
  User: kimkahyun
  Date: 8/19/24
  Time: 12:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>로그인 페이지</title>
</head>
<body>
<%
    String LoginId="";
    String checked="";
    Cookie[] cookies=request.getCookies();
    for(Cookie c:cookies){
        if(c.getName().equals("userId")){
            LoginId=c.getValue();
        }else if(c.getName().equals("checked")){
            checked=c.getValue();
        }
    }
%>
<form action="<%=request.getContextPath()%>/user.do?action=login" method="post">
    <%
        if(checked.equals("기억하기")){
    %>
    ID : <input type="text" name="id" value="<%=LoginId%>"/><br>
    PW : <input type="password" name="password"/><br><br>
    아아디 기억하기<input type="checkbox" name="idSave" checked><br>
    <%
        }else{
    %>
    ID : <input type="text" name="id"/><br>
    PW : <input type="password" name="password"/><br><br>
    아아디 기억하기<input type="checkbox" name="idSave"><br>
    <%
        }
    %>
    <input type="submit" value="로그인"/>
</form>
</body>
</html>
