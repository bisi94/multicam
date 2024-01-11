<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>POST</title>
<style>
	input{
		width:80%;
		padding:4px;
	}
</style>
</head>
<body>
	<form name="pf" id="pf" action="../edit" method="post">
	
		<input type="hidden" name="id" value="${vo.id}">
	
		<table border="1" style="width:60%;margin:auto">
			<tr>
				<th colspan="2"><h1>::MongoDB Post Edit::</h1></th>
			</tr>
			
			<tr>
				<td width="20%"><b>글번호</b></td>
				<td width="80%">
					<input type="text" name="no" placeholder="No" readonly value="${vo.no}">
				</td>
			</tr>
			
			<tr>
				<td width="20%"><b>작성자</b></td>
				<td width="80%">
					<input type="text" name="name" placeholder="Name" required value="${vo.name}">
				</td>
			</tr>

			<tr>
				<td width="20%"><b>메모 내용</b></td>
				<td width="80%">
					<input type="text" name="msg" placeholder="Message" required value="${vo.msg}">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<button type="submit">글 수정하기</button>
					<button type="reset">다시 쓰기</button>
					<button type="button" onclick="location.href='../list'">글목록</button>
				</td>
			</tr>
		</table>
		
		
	</form>

</body>
</html>







