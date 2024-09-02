<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>메인화면</title>
</head>
<body>
<%
    String login = (String) request.getSession().getAttribute("login");
    if (login == null) {
%>
<a href="<%=request.getContextPath()%>/user/login">[로그인]</a>
<%
    } else {
%>
안녕하세요, <%=login%>님.<br>
<a href="<%=request.getContextPath()%>/user/logout">[로그아웃]</a>
<%
    }
%>
<a href="<%=request.getContextPath()%>/store/list">[가게 목록보기]</a>
<a href="<%=request.getContextPath()%>/store/register">[가게등록]</a>
</body>
</html>
