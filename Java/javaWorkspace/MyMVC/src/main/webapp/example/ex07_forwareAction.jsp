<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h1>ex07_forwardAction 페이지 입니다</h1>
<p>
	forwardAction 태그를 이용해 ex01.jsp페이지로 이동할 예정입니다
	<br>
	서버 내부에서 페이지 이동을 한다. 따라서 브라우저는 이동한 페이지의 url이 아니라
	이전 페이지의 url을 유지한다
</p>
<hr color='yellow'>
<jsp:forward page="ex01.jsp" />