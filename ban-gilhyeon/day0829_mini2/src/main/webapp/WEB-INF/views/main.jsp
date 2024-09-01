<%--
  Created by IntelliJ IDEA.
  User: 관리자
  Date: 2024-08-20
  Time: 오전 9:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>메인화면</title>
</head>
<body>
<%@ include file="common/header.jsp"%>
<h2>배달의 세계에 오신걸 환영합니다</h2>
<a href="<%=request.getContextPath()%>shop/review">[리뷰 하러가기]</a>
<a href="<%=request.getContextPath()%>/shop/list">[배달 목록으로]</a>
</body>
</html>
