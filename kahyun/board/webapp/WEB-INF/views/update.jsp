<%@ page import="com.grepp.model.BoardDTO" %><%--
  Created by IntelliJ IDEA.
  User: kimkahyun
  Date: 8/19/24
  Time: 12:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>글 수정</title>
</head>
<body>
<%
    BoardDTO board=(BoardDTO) request.getAttribute("board");
%>
<form action="<%=request.getContextPath()%>/board.do?action=update&no=<%=board.getNo()%>" method="post">
    <input type="text" placeholder="제목을 수정하세요." value="<%=board.getTitle()%>" id="title" name="title"><br>
    <textarea placeholder="내용을 수정하세요."  name="content"><%=board.getContent()%></textarea><br>
    <input type="submit" value="수정 완료">
</form>
</body>
</html>
