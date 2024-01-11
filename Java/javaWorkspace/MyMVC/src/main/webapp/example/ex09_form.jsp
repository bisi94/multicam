<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <h1>ex09_form.jsp</h1>
<form name="f" action="ex09_request.jsp" method="get">
	아이디: <input type="text" name="uid"> <br>
	성별: <input type="radio" name="gender" value="F" checked>여자
		<input type="radio" name="gender" value="M" >남자
		<br>
	취미: <input type="checkbox" name="hobby" id="hobby1"
		 value="reading" checked>독서
		<input type="checkbox" name="hobby" id="hobby2"
		 value="movie">영화감상
		 <input type="checkbox" name="hobby" id="hobby3"
		 value="music">음악감상
		 <br>
	사용가능 언어: 
	<!--multiple을 주면 다중 선택 가능	
		size를 주면 펼친 형태의 리스트박스가 됨  -->
			<select name="lang" size="5" multiple
			style="width:200px">
			<optgroup label="::사용가능 언어::">
				<option value="HTML5">HTML5</option>
				<option value="JAVA">JAVA</option>
				<option value="JSP">JSP</option>
				<option value="SERVLET">SERVLET</option>
			</optgroup>
			</select>
			<br>
			<button>전송</button>
</form>