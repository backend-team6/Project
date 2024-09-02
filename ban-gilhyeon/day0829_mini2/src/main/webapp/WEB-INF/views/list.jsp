<!-- shopList.jsp -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Shop List</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
<h2>Shop List</h2>
<table>
    <thead>
    <tr>
        <th>No</th>
        <th>Name</th>
        <th>Score</th>
        <th>Review</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="shop" items="${shopDTOList}">
        <tr>
            <td>${shop.getNo}</td>
            <td>${shop.getName}</td>
            <td>${shop.getScore}</td>
            <td>${shop.getReview}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
