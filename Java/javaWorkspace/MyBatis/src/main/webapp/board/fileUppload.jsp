<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="container">
	<h1>fileUpload.jsp</h1>
	<!--  
	post방식일 경우 인코딩방식(enctype) 2가지
	
	[1] application/x-www-form-urlencoded (디폴트)
		==> 이 경우는 첨부파일명만 서버에 전송된다
	
	[2] multipart/form-data
		==> 파일 이름과 함께 파일 데이터가 서버에 전송된다
	
	-->
	<br><br>
	<form action="fileUploadEnd.jsp" method="post" 
	enctype="multipart/form-data">
		올린이: <input type="text" name="author">
		<br><br>
		첨부파일: <input type="file" name="filename">
		
		<br><br>
		<button class="btn">업로드</button>
	
	</form>
</div>