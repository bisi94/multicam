<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>

<style>
	.container{
		width:90%
		margin:auto;
		text-align:center;
		padding:1em;
	}
	#postList{
		width:100%;
	}
	#postList li{
		list-style:none;
		float:left;
		height:40px;
		line-height:40px;
		border-bottom:solid 1px #ddd;
	}
	#postList li:nth-child(4n+1){
		width:10%;
	}
	#postList li:nth-child(4n+2){
		width:50%;
	}
	#postList li:nth-child(4n+3){
		width:20%;
	}
	#postList li:nth-child(4n){
		width:20%;
	}
</style>

</head>
<body>
	<div class="container">
		<h1>Post List</h1>
		
		<br>
		[<a href="post">글쓰기</a>]
		<br><br>
		
		<%-- ${postArr} --%>
		<ul id="postList">
			<li>글번호</li>
			<li>메시지</li>
			<li>작성자</li>
			<li>작성일</li>
			
			<!-- ------------------------- -->
			<c:forEach var="vo" items="${postArr}">
				<li>${vo.no}</li>
				<li>${vo.msg} 
				<span class="m1">
				<a href="edit/${vo.id}">수정</a>
				<a href="delete/${vo.id}">삭제</a>
				</span> </li>
				<li>${vo.name}</li>
				<li>${vo.wdate}</li>
			</c:forEach>
			<!-- ------------------------- -->
		</ul>
	</div>
</body>
</html>