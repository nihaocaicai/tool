<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<html>
<head>
    <title>diary</title>
</head>
<body>
<table>
    <tr>
        <td>diary_id</td>
        <td>user_id</td>
        <td>diary_title</td>
        <td>diary_content</td>
        <td>diary_write_time</td>
        <td>diary_write_place</td>
    </tr>
    <tr>
        <td>${diary.diary_id}</td>
        <td>${diary.user_id}</td>
		<td>${diary.diary_title}</td>
        <td>${diary.diary_content}</td>
        <td>${diary.diary_write_time}</td>
        <td>${diary.diary_write_place}</td>
    </tr>
</table>
</body>
</html>