<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- top.jsp -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
<%
	//컨텍스트명을 알아내자.
	String myctx=request.getContextPath(); //컨텍스트명이 들어옴
	System.out.println("myctx: "+myctx);

	//컨텍스트명을 기준으로 경로를 잡으면 => 절대경로
%>

	<!-- index.scc 외부 css파일 참조 ---------------------------------->
	<link rel="stylesheet" type="text/css" href="<%=myctx%>/css/index.css">
	<!-- --------------------------------------------------------- -->
	
</head>
<body>
	<div class="wrap">
	<!-- top menu------------------------ -->
		<ul class="topMenu">
			<li><a href="<%=myctx%>/main.me">HOME</a></li>
		
			<li><a href="<%=myctx%>/board/write.me">게시판 글쓰기</a></li>
			
			<li><a href="<%=myctx%>/board/list.me">게시판 목록</a></li>
		</ul>
	<!-- ---------------------------------- -->
	
	
	
	
	
	
	
	
	
	
	
	