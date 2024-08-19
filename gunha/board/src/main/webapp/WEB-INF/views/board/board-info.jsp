<%@ page import="com.group.board.board.domain.BoardDTO" %><%--
  Created by IntelliJ IDEA.
  User: hgh14
  Date: 2024-08-19
  Time: 오후 12:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Board Info</title>
</head>
<body>
<%
    BoardDTO board = (BoardDTO) request.getAttribute("board");
%>
<table border="1">
    <tr>
        <td>글번호 : </td>
        <td><%=board.getNo()%></td>
    </tr>
    <tr>
        <td>제목 : </td>
        <td><%=board.getTitle()%></td>
    </tr>
    <tr>
        <td>작성자 : </td>
        <td><%=board.getWriter()%></td>
    </tr>
    <tr>
        <td>작성일시 : </td>
        <td><%=board.getRegDate()%></td>
    </tr>
    <tr>
        <td>조회수 : </td>
        <td><%=board.getReadCount()%></td>
    </tr>
    <tr>
        <td>내용 : </td>
        <td><%=board.getContent()%></td>
    </tr>
</table>
<%
    String loginId = session.getAttribute("member").toString();
	if (loginId.equals(board.getWriter())) {
%>
        <a href="<%=request.getContextPath()%>/board/update?no=<%=board.getNo()%>">[수정하기]</a>
<%
    }
%>
<a href="<%=request.getContextPath()%>/board/list">[게시판 목록으로]</a>
</body>
</html>
