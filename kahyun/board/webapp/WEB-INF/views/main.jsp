<%--
  Created by IntelliJ IDEA.
  User: kimkahyun
  Date: 8/19/24
  Time: 10:29 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="<%=request.getContextPath()%>/board.do?action=list">[게시글 보러가기]</a>
<%
String loginId=(String)session.getAttribute("loginId");
if(loginId==null){
%>
<a href="<%=request.getContextPath()%>/user.do?action=login">[로그인]</a>
<%
} else{
%>
<a href="<%=request.getContextPath()%>/user.do?action=logout">[로그아웃]</a>
<a href="<%=request.getContextPath()%>/board.do?action=writeForm">[게시글 작성하기]</a>
<%
    }
%>
<a href="<%=request.getContextPath()%>/user.do?action=signUp">[회원가입]</a>
</body>
</html>
