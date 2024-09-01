<%--
  Created by IntelliJ IDEA.
  User: yeonsu
  Date: 8/19/24
  Time: 11:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>글 작성</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/board?action=insert" method="post">
    <label>
        <input type="text" placeholder="제목을 입력하세요" value="제목" id="title" name="title">
    </label><br>
<%--    <label>--%>
<%--        <input type="text" placeholder="작성자를 입력하세요" id="writer" name="wwww">--%>
<%--    </label><br>--%>
    <label>
        <textarea placeholder="내용을 입력하세요" id="content" name="content"></textarea>
    </label><br>
    <input type="submit" value="작성완료">
</form>
</body>
</html>
