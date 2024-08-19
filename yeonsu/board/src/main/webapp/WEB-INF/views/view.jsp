<%@ page import="com.yeonsu.model.board.BoardDTO" %><%--
  Created by IntelliJ IDEA.
  User: yeonsu
  Date: 8/19/24
  Time: 12:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>글 상세화면</title>
</head>
<body>
<%
    BoardDTO board = (BoardDTO) request.getAttribute("board");
    String writer = request.getAttribute("writer").toString();
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
        <td><%=writer%></td>
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
<br>
<%
    String loginId = (String) session.getAttribute("loginId");
    if (loginId.equals(writer)) {
%>
<a href="<%=request.getContextPath()%>/board?action=update&no=<%=board.getNo()%>">[수정하기]</a>
<a href="<%=request.getContextPath()%>/board?action=delete&no=<%=board.getNo()%>">[삭제하기]</a>
<br>
<%
    }
%>

<a href="<%=request.getContextPath()%>/board?action=list">[게시판 목록으로]</a>
<br>
<a href="<%=request.getContextPath()%>">[메인으로]</a>
</body>
</html>
