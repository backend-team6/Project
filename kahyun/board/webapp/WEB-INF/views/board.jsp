<%@ page import="com.grepp.model.BoardDTO" %><%--
  Created by IntelliJ IDEA.
  User: kimkahyun
  Date: 8/19/24
  Time: 11:49 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>게시글</title>
</head>
<body>
<%
    BoardDTO board=(BoardDTO) request.getAttribute("board");
%>
<table border="1">
    <tr>
        <td>글 번호 : </td>
        <td><%=board.getNo()%></td>
    </tr>
    <tr>
        <td>제목 : </td>
        <td><%=board.getTitle()%></td>
    </tr>
    <tr>
        <td>직성자 : </td>
        <td><%=board.getWriter()%></td>
    </tr>
    <tr>
        <td>작성일자 : </td>
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
<a href="<%=request.getContextPath()%>/board.do?action=update&no=<%=board.getNo()%>">[수정하기]</a>
<a href="<%=request.getContextPath()%>/board.do?action=list">[글 목록 보기]</a>
</body>
</html>
