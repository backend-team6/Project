<%--
  Created by IntelliJ IDEA.
  User: yeonsu
  Date: 8/19/24
  Time: 10:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>message</title>
</head>
<body>
<script>
    alert("<%=request.getAttribute("msg")%>");
    location.href = "<%=request.getAttribute("path")%>"
</script>
</body>
</html>
