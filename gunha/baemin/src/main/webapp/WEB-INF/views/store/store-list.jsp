<%@ page import="programmers.baemin.store.domain.Store" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>가게 목록</title>
</head>
<body>
<%@ include file="../common/header.jsp"%>
<a href="<%=request.getContextPath()%>/store/register">[가게 등록]</a>
<table border="1">
    <tr>
        <th>가게 번호</th>
        <th>가게 이름</th>
        <th>가게 리뷰</th>
        <th>가게 상세 페이지</th>
    </tr>
    <%
        List<Store> storeList = (List<Store>)request.getAttribute("storeList");
        for (Store store : storeList) {
    %>
    <tr>
        <td><%=store.getId()%></td>
        <td><%=store.getName()%></td>
        <td>가게 리뷰 보기</td>
        <td><a href="<%=request.getContextPath()%>/store/<%=store.getId()%>">가게 상세 페이지</a></td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>
