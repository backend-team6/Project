<%@ page import="programmers.baemin.store.domain.Store" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        Store store = (Store)request.getAttribute("store");
    %>
    <title><%=store.getName()%> 정보</title>
</head>
<body>
<%@ include file="../common/header.jsp"%>
<table border="1">
    <tr>
        <td>가게 등록 번호</td>
        <td><%=store.getId()%></td>
    </tr>
    <tr>
        <td>가게 이름</td>
        <td><%=store.getName()%></td>
    </tr>
</table>
<a href="<%=request.getContextPath()%>/store/update">[수정하기]</a>
<a href="<%=request.getContextPath()%>/store/list">[가게 목록 보기]</a>
</body>
</html>
