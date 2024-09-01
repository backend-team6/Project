<%--
  Created by IntelliJ IDEA.
  User: yeonsu
  Date: 8/19/24
  Time: 10:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>게시판 메인 화면</title>
</head>
<body>
<a href="<%=request.getContextPath()%>/board?action=list">[목록보기]</a><br>

<%
    String loginId = (String) session.getAttribute("loginId");
    if (loginId == null) { //로그인 안 된 사용자는 로그인 링크 그대로 보여주고
%>
<a href="<%=request.getContextPath()%>/user?action=login">[로그인]</a><br>
<a href="<%=request.getContextPath()%>/user?action=signup">[회원가입]</a><br>
<%
} else { //이미 로그인 내역이 있네?
%>
<b><%=loginId%> 님 반갑습니다!<br>
    <a href="<%=request.getContextPath()%>/user?action=logout">[로그아웃]</a><br>
    <a href="<%=request.getContextPath()%>/board?action=writeForm">[글 작성]</a>
        <%
    }
%>
</body>
</html>
