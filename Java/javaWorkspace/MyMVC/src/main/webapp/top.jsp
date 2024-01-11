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
			<li><a href="<%=myctx%>/main.do">HOME</a></li>
			
			<!-- 로그인한 경우 -->
			<c:if test="${sessionScope.loginResult==null || loginResult=='N'}">
				<li><a href="<%=myctx%>/login.do">로그인</a></li>
				<li><a href="<%=myctx%>/signup.do">회원가입</a></li>
			</c:if>
			
			<!-- 로그인 하지않은 경우 -->
			<c:if test="${loginResult!=null && loginResult=='Y'}">
				<li><a>[${loginId} 님 로그인 중...]</a></li>
				<li><a href="<%=myctx%>/logout.do">로그아웃</a></li>
			
				<li><a href="<%=myctx%>/admin/memberList.do">회원목록</a></li>
				<li><a href="<%=myctx%>/user/memberUpdate.do">회원정보 수정</a></li>
			</c:if>
			
			<li><a href="<%=myctx%>/board/list.do">게시판</a></li>
		</ul>
	<!-- ---------------------------------- -->
	
	
	
	
	
	
	
	
	
	
	
	