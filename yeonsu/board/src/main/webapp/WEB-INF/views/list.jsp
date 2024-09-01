<%@ page import="com.yeonsu.model.board.BoardDTO" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: yeonsu
  Date: 8/19/24
  Time: 11:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>게시판 목록</title>
</head>
<body>
<table border="1">
    <%
        List<BoardDTO> bList = (List<BoardDTO>) request.getAttribute("blist");
        for (BoardDTO b: bList) {
    %>
    <tr>
        <td><%=b.getNo()%></td>
        <td><a href="<%=request.getContextPath()%>/board?action=view&no=<%=b.getNo()%>"><%=b.getTitle()%></a></td>
        <td><%=b.getWriter()%></td>
        <td><%=b.getRegDate()%></td>
        <td><%=b.getReadCount()%></td>
    </tr>
    <%
        }
    %>
</table>
<br>
<br>
<a href="<%=request.getContextPath()%>/board?action=writeForm">[게시글 작성하러 가기]</a>
<br>
<a href="<%=request.getContextPath()%>">[메인으로]</a>
<%--<%--%>
<%--    String loginId = (String) session.getAttribute("loginId");--%>
<%--    if (loginId == null) { //로그인 안 된 사용자는 로그인 링크 그대로 보여주고--%>
<%--%>--%>
<%--<a href="<%=request.getContextPath()%>/user?action=login">[로그인]</a><br>--%>
<%--<a href="<%=request.getContextPath()%>/user?action=signup">[회원가입]</a><br>--%>
<%--<%--%>
<%--} else { //이미 로그인 내역이 있네?--%>
<%--%>--%>
<%--<a href="<%=request.getContextPath()%>/board?action=writeForm">[게시글 작성하러 가기]</a>--%>
<%--        <%--%>
<%--    }--%>
<%--%>--%>


</body>
</html>
