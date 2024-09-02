<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>가게 등록</title>
</head>
<body>
<%@ include file="../common/header.jsp"%>
<br>
<form action="<%=request.getContextPath()%>/store/register" method="post">
    가게 이름 : <input type="text" name="name"><br>
    <input type="submit" value="등록">
</form>
</body>
</html>
