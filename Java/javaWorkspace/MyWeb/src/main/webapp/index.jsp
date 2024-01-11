<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Hello Index JSP</h1>
	<h2>안녕 쟈스피</h2>
	<%
		//내장객체: out
		java.util.Date today=new java.util.Date();
		out.println(today);
	%>
	
</body>
</html>