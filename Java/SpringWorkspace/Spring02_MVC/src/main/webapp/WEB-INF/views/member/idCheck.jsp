<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>::ID 중복체크::</title>

<link rel="stylesheet" type="text/css" href="css/index.css">
<style>
	#userid{
		padding: 7px;
		border: 1px solid silver;
	}
	div{
		padding: 2em;
		width:90%;
		margin: 2em auto;
	}
	.jumbotron, .navbar{
		display:none;
	} 
</style>

<script>
	const checkId=function(){
		let obj=document.getElementById('userid'); //input객체
		if(!obj.value){
			alert('아이디를 입력하세요');
			obj.focus();
			return;
		}
		idF.submit(); //전송
	}//-----------------------------
</script>

</head>
<body>
	<div align="center">
	
		<form name="idF" action="idCheck" method="post">
		
			<label for="userid">아이디</label>
			<input type="text" name="userid" id="userid" placeholder="ID" autofocus="autofocus">
			<button type="button" onclick="checkId()" class="btn btn-info">확  인</button>	
		</form>
		
	</div>
</body>
</html>