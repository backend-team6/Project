<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>로그인 화면</title>
</head>
<body>
<script>
    <%
    String msg = (String) request.getAttribute("error");
    if (msg != null) {
		%>
    alert(msg);
    <%
    }
    %>
</script>
<h2>로그인 화면입니다.</h2>
<%
    String redirectUrl = request.getParameter("redirectURL");
%>
<form action="<%=request.getContextPath()%>/user/login" method="post">
    <input  type="hidden" name="redirectUrl" value="<%=redirectUrl%>">
    <table>
        <tr>
            <td>ID :</td>
            <td><input type="text" name="loginId"></td>
        </tr>
        <tr>
            <td>PW :</td>
            <td><input type="password" name="password"></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="LOGIN">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
