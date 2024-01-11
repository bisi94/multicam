<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="container text-center py-5">
	<h1>Welcome to Spring05_NaverAI</h1>
	
	<p>
		<h2 class="text-danger">
			Multishop 계정의 테이블 수: ${tableCnt}
		</h2>
		<hr>
		<a href="summaryForm"><h3>Clova Summary</h3></a>
		<a href="csrform"><h3>Clova Speech Recognition</h3></a>
		<a href="voiceForm"><h3>Clova Voice Premium</h3></a>
		<a href="ocrForm"><h3>Clova OCR</h3></a>
		
	</p>
	
</div>

</body>
</html>