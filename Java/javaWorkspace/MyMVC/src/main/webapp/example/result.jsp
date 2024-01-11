<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>result.jsp</title>
<%@ page import ="java.lang.Integer"%>
<script>
        function goToInputPage() {
            // 특정 주소로 이동
            window.location.href = 'input.jsp';
        }
</script>
<style>
table td{
	text-align:center;
}
table td.container-score{
	width:200px;
}

</style>
    
</head>
<body>
<% String empNo=request.getParameter("empno");%>
<% String javaStr=request.getParameter("java");%>
<% String dbStr=request.getParameter("db");%>
<% String jspStr=request.getParameter("jsp");%>
	
	
<%

if(javaStr.trim().isEmpty()){
	javaStr="0";
}
if(dbStr.trim().isEmpty()){
	dbStr="0";
}
if(jspStr.trim().isEmpty()){
	jspStr="0";
}

int java=0;
int db=0;
int jsp=0;
double avg=0;



try{
	java=Integer.parseInt(javaStr);
 	db=Integer.parseInt(dbStr);
 	jsp=Integer.parseInt(jspStr);
 	avg=(java+db+jsp)/3;
 	String avgStr=String.valueOf(avg);
 	
}catch(NumberFormatException e){}


%>

 	

	<h1>성적 처리결과</h1>
		<table border='1'>
			<tr>
				<td colspan='2'>사번</td>
				<td class="container-score"><%=empNo%></td>
			</tr>
			
			<tr>
				<td rowspan='3'>과목</td>
				<td>Java</td>
				<td class="container-score"><%=javaStr%></td>
			</tr>
			
			<tr>
				<td>Database</td>
				<td class="container-score"><%=dbStr%></td>
			</tr>
			
			<tr>
				<td>JSP</td>
				<td class="container-score"><%=jspStr%></td>
			</tr>
			
			<tr>
				<td colspan='2'>평균</td>
				<td><%=avg%></td>
			</tr>
						
			<tr>
			<!-- 버튼을 클릭하면 goToSpecificPage() 함수 호출 -->
   				<td colspan='3'><button id='btn' onclick="goToInputPage()">입력화면</button></td>
			</tr>
		</table>

</body>
</html>