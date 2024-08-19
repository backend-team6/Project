<%@ page import="com.group.board.board.domain.BoardDTO" %><%--
  Created by IntelliJ IDEA.
  User: hgh14
  Date: 2024-08-19
  Time: 오후 3:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Board Update</title>
</head>
<body>
<%
    BoardDTO boardDTO = (BoardDTO) request.getAttribute("board");
%>
<form method="post">
    <input type="hidden" name="no" value="<%=boardDTO.getNo()%>">
    <input type="text" placeholder="제목을 입력하세요." value="<%=boardDTO.getTitle()%>" id="title" name="title"/><br/>
    <textarea placeholder="내용을 입력하세요."name="content">
        <%=boardDTO.getContent()%>
    </textarea><br/>
    <input type="submit" value="수정완료">
</form>
</body>
</html>
