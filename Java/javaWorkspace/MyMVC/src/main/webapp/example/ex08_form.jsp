<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h1>페이지 이동방식과 response객체에 대해 살펴보자</h1>
<h2>테스트 방법: 아이디 입력박스에 killer, forward,
	redirect값을 입력하여 테스트 하세요.
</h2>

	<form action="ex08_response.jsp" method="POST">
		<div>
			<label for="uid">아이디</label>
			<!--label의 for 부분에 입력양식의 id값을 넣어줌  -->
			<input type="text" name="uid" id="uid2">
		</div>
		<div>
			<label for="pwd">비밀번호</label>
			<!--label의 for 부분에 입력양식의 id값을 넣어줌  -->
			<input type="password" name="pwd" id="pwd">
		</div>
		<div>
			<button>로그인</button>
		</div>
	</form>