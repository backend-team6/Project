<%--
  Created by IntelliJ IDEA.
  User: 관리자
  Date: 2024-08-13
  Time: 오후 4:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>게시판 메인 화면</title>
</head>
<body>
<%
    String loginId = (String) session.getAttribute("member");
    if (loginId == null){ // 로그인 안된 사용자는 로그인 링크 그대로 보여주고.
%>
<a href="<%=request.getContextPath()%>/member/login">[로그인]</a>
<a href="<%=request.getContextPath()%>/member/join">[회원가입]</a>
<%
}else{ // 이미 로그인 내역이 있네?!
%>
로그인된 사용자이군요! <b> <%=loginId%> 님 반갑습니다!
    <a href="<%=request.getContextPath()%>/member/logout">[logout]</a>
    <a href="<%=request.getContextPath()%>/board/write">[작성하러가기]</a>
        <%
        }

%>
<a href="<%=request.getContextPath()%>/board/list">[목록보기]</a>
</body>
</html>
