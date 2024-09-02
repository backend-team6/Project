<%@ page import="programmers.baemin.store.domain.Store" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>가게 등록</title>
</head>
<body>
<%@ include file="../common/header.jsp"%>
<%
    Store store = (Store)request.getAttribute("store");
%>
<br>
<form action="<%=request.getContextPath()%>/store/register" method="post">
    가게 이름 : <input type="text" name="name" value="<%=store.getName()%>"><br>
    <input type="submit" value="수정">
</form>
</body>
</html>
