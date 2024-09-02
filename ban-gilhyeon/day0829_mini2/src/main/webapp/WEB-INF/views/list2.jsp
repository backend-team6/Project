<%@ page import="org.example.model.dto.ShopDTO"%>
<%@ page import="org.example.model.dto.ShopDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>

<%@ page contentType="text/html; charset=UTF-8" language="java"%>

<html>

<head>
    <title>리뷰 목록</title>
</head>

<%--<%@ include file="common/header.jsp"%>--%>
<a href="<%=request.getContextPath()%>/shop/review">[리뷰 작성]</a><br>
<table border="1">
        <%
           List<ShopDTO> shopDTOList = (List<ShopDTO>) request.getAttribute("shopDTOList");
        for(ShopDTO shop : shopDTOList){
    %>
    <tr>
        <td><%=shop.getNo()%></td>
        <td><a href="<%=request.getContextPath()%>/shop/list?no=<%=shop.getNo()%>"><%=shop.getName()%></a></td>
        <td><%=shop.getScore()%></td>
        <td><%=shop.getReview()%></td>
    </tr>
        <%
        }
    %>
</table>
<a href="<%=request.getContextPath()%>/main">[메인으로]</a>
</html>