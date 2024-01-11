<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Welcome to my page</h1>
<hr color='red'>

<h2 style='color:purple'>
메시지: <%= request.getAttribute("msg")%> %>
</h2>

</body>
</html>