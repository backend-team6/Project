<%--
  Created by IntelliJ IDEA.
  User: kimkahyun
  Date: 8/19/24
  Time: 11:26 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>글 작성</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/board.do?action=write" method="post">
  <input type="text" placeholder="제목을 입력하세요." name="title"><br>
<%--  <input type="text" placeholder="작성자를 입력하세요."name="writer"><br><br>--%>
  <textarea placeholder="내용을 입력하세요." name="content"></textarea><br>
  <input type="submit" value="작성 완료">
</form>
</body>
</html>
