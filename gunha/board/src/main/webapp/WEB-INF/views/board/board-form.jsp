<%--
  Created by IntelliJ IDEA.
  User: hgh14
  Date: 2024-08-19
  Time: 오후 12:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Board Form</title>
</head>
<body>
<form method="post">
    <input type="text" placeholder="제목을 입력하세요." value="제목" id="title" name="title"/><br/>
    <textarea placeholder="내용을 입력하세요." name="content"></textarea><br/>
    <input type="submit" value="작성완료">
</form>
</body>
</html>
