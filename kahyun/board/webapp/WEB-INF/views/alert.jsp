<%--
  Created by IntelliJ IDEA.
  User: kimkahyun
  Date: 8/14/24
  Time: 4:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>alert</title>
</head>
<body>
<script>
    alert('<%=request.getAttribute("msg")%>');
    location.href="<%=request.getAttribute("path")%>";
</script>
</body>
</html>
