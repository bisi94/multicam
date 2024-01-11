<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>input.jsp</title>
<style>
table td{
	text-align:center;
}
#btn-container button{
	width:20%;
}
</style>
</head>
<body>



	<form id="myForm" action="result.jsp" method="get">
		<h1>성적 입력</h1>
		<table border='1'>
			<tr>
				<td colspan='2'>사번</td>
				<td><input type="text" name="empno"></td>
			</tr>
			
			<tr>
				<td rowspan='3'>과목</td>
				<td>Java</td>
				<td><input type="text" name="java"></td>
			</tr>
			
			<tr>
				<td>Database</td>
				<td><input type="text" name="db"></td>
			</tr>
			
			<tr>
				<td>JSP</td>
				<td><input type="text" name="jsp"></td>
			</tr>
			
			  <tr>
                <td colspan='3' id="btn-container"><button type="button" onclick="validateAndSubmit()">저장</button></td>
            </tr>
		</table>
		
	</form>
	
	 <script>
        function validateAndSubmit() {
            // empno 값 가져오기
            var empNoValue = document.getElementsByName('empno')[0].value;

            // 유효성 검사
            if (empNoValue.trim() == null || empNoValue.trim() == "") {
            	// 알림 출력
            	alert('사번을 입력하세요');
            } else {
            	// 폼 제출
            	document.getElementById('myForm').submit();
            }
        }
    </script>

</body>
</html>