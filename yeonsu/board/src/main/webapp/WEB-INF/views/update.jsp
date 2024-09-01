<%@ page import="com.yeonsu.model.board.BoardDTO" %><%--
  Created by IntelliJ IDEA.
  User: yeonsu
  Date: 8/19/24
  Time: 12:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>글 수정</title>
</head>
<body>
<%
    BoardDTO board = (BoardDTO) request.getAttribute("board");
    String writer = request.getAttribute("writer").toString();
%>
<form action="<%=request.getContextPath()%>/board?action=update&no=<%=board.getNo()%>" method="post">
    <table border="1">
        <tr>
            <td>글번호 : </td>
            <td><%=board.getNo()%></td>
        </tr>
        <tr>
            <td>제목 : </td>
            <td><label for="title"></label><input type="text" placeholder="제목을 입력하세요" value="<%=board.getTitle()%>" id="title" name="title"></td>
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
            <td><label for="content"></label><input type="text" placeholder="내용을 입력하세요" value="<%=board.getContent()%>" id="content" name="content"></td>
        </tr>
    </table>
    <input type="submit" value="수정하기">
</form>
<a href="<%=request.getContextPath()%>/board?action=list">[게시판 목록으로]</a>
<br>
<a href="<%=request.getContextPath()%>">[메인으로]</a>
</body>
</html>
