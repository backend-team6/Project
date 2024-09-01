<%@ page import="com.grepp.model.BoardService" %>
<%@ page import="com.grepp.model.BoardDTO" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: kimkahyun
  Date: 8/19/24
  Time: 10:35 AM
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
        List<BoardDTO> list=(List<BoardDTO>)request.getAttribute("bList");
        for(BoardDTO board:list){
    %>
    <tr>
        <td><%=board.getNo()%></td>
        <td><a href="<%=request.getContextPath()%>/board.do?action=view&no=<%=board.getNo()%>"><%=board.getTitle()%></a></td>
        <td><%=board.getWriter()%></td>
        <td><%=board.getRegDate()%></td>
        <td><%=board.getReadCount()%></td>
    </tr>
    <%
        }
    %>
</table>
<a href="<%=request.getContextPath()%>/board.do?action=writeForm">[게시글 작성하러 가기]</a>
<a href="<%=request.getContextPath()%>/main.do">[메인으로]</a>
</body>
</html>
