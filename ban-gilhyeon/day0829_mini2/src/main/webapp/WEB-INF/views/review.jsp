<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>review page</title>
</head>

<body>
<h2>login page</h2>
<form action="<%=request.getContextPath()%>/shop/review" method="post">
        식당이름 : <input type="text" name="name"><br>
        점수 : <input type="text" name="score"><br>
        내용 : <textarea name="review" cols="30" rows="10"></textarea>
        <input type="submit" value="작성">
    </form>
</body>
</html>