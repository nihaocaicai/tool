<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<html>
<head>
    <title>error</title>
</head>
<body>
<table>
    <tr>
        <td>error_id</td>
        <td>user_id</td>
        <td>error_content</td>
        <td>error_date</td>
    </tr>
    <tr>
        <td>${error.error_id}</td>
        <td>${error.user_id}</td>
        <td>${error.error_content}</td>
        <td>${error.error_date}</td>
    </tr>
</table>
</body>
</html>