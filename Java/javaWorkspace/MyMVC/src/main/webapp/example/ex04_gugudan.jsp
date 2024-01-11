<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ex04_gugudan.jsp</title>
	<style>
		.container{
		width:80%;
		margin:auto;
		text-align:center;
		}	
		.container table{
			width:90%;
			margin:1em auto;
		}
	</style>
</head>
<body>
	<div class="container">
		<h1>구구단</h1>
		
		<table border="1">
			<tr>
				<%for(int i=2;i<10;i++){ %>
				<td><%=i %>단</td>
				<%} %>
			</tr>
			<%for(int i=1;i<10;i++){ %>
				<tr>
					<%for(int j=2;j<10;j++){ %>
					<td><%=j%> x <%=i%> = <%=i*j%></td>
					<%}%>
				</tr>
			<%}%>
			
		</table>
	</div>

</body>
</html>