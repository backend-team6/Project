<%@ page import="com.group.board.board.domain.BoardDTO" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: hgh14
  Date: 2024-08-19
  Time: 오후 12:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Board List</title>
</head>
<body>
<table border="1">
    <tr>
        <th>게시글 번호</th>
        <th>게시글 제목</th>
        <th>작성자</th>
        <th>작성일자</th>
        <th>조회수</th>
    </tr>
    <%
        List<BoardDTO> list = (List<BoardDTO>) request.getAttribute("list");
        for(BoardDTO b: list) {
    %>
    <tr>
        <td><%=b.getNo()%></td>
        <td><a href="<%=request.getContextPath()%>/board/info?no=<%=b.getNo()%>&writer=<%=b.getWriter()%>"><%=b.getTitle()%></a></td>
        <td><%=b.getWriter()%></td>
        <td><%=b.getRegDate()%></td>
        <td><%=b.getReadCount()%></td>
    </tr>
    <%
        }
    %>
</table>
<a href="/application">[홈]</a><br>
<%
    String loginId = (String) session.getAttribute("member");
    if (loginId != null) {
%>
    <a href="<%=request.getContextPath()%>/board/write">[게시글 작성하러 가기]</a>
<%
    }
%>
</body>
</html>
