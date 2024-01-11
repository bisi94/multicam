<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, user.model.*" %>    
       
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>::정보 수정::</title>
<!-- 외부 css파일 참조===================================================-->
<link rel="stylesheet" type="text/css" href="../css/member.css">
<!--================================================================-->

<!--외부 js파일 참조=====================================================-->
<script src="../js/member.js"> </script>
<!--================================================================-->

<script type="text/javascript">
	function check(){
		if(!mf.name.value){
			alert('이름을 입력하세요');
			mf.name.focus();
			return;
		}
		//아이디 체크
		if(!mf.userid.value){
			alert('아이디를 입력하세요');
			mf.userid.focus();
			return;
		}
		//비밀번호 체크
		if(!mf.userpw.value){
			alert('비밀번호를 입력하세요');
			mf.userpw.focus();
			return;
		}
		//비밀번호와 확인값 비교 후 체크	
		if(mf.userpw.value!=mf.userpw2.value){
			alert('비밀번호와 일치하지 않습니다');
			mf.userpw2.focus();
			return;
		}
		
		mf.submit();//서버에 전송
	}
	
</script>

</head>
<body>

<%  //scriptlet tag 안에서 자바코드를 사용할 수 있음
	//회원번호 받기
	//JSP의 내장객체: request (HttpServletRequest타입), response (HttpServletResponse타입)
	//				out (JspWriter타입)
	String noStr=request.getParameter("no");

//유효성 체크
	if(noStr==null||noStr.trim().equals("")){
%>
	<script>
		alert('잘못 들어온 경로입니다');
		history.back();
	</script>
<%
		return;
	}
	int no=Integer.parseInt(noStr.trim());
	
	MemberDAO dao=new MemberDAO();
	MemberVO user=dao.selectOne(no);
	if(user==null){
		%>
		<script>
			alert('회원정보가 존재하지 않습니다');
			self.close();
		</script>
		<%
		return;
	}
%>

	<div id="wrap">
		<form name="mf" action="../MemberUpdate" method="post">
			<table border="1">
				<tr>
					<th colspan="2" id="title" style="text-align: center;">
						<h2>Member Info Edit</h2>
					</th>
				</tr>
				<tr>
					<th>회원번호</th>
					<td>
						<input type="text" name="no" readonly value="<%=user.getNo()%>">
					</td>
				</tr>
				
				<tr>
					<th>이름</th>
					<td>
						<input type="text" name="name" value="<%=user.getName()%>">
					</td>
				</tr>
				<tr>
					<th>아이디</th>
					<td>
						<input type="text" name="userid" value="<%=user.getUserid()%>">
					</td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td>
						<input type="password" name="userpw">
					</td>
				</tr>
				<tr>
					<th>비밀번호 확인</th>
					<td>
						<input type="password" name="userpw2">
					</td>
				</tr>
				<tr>
					<th>연락처</th>
					<td>
						<input type="text" name="tel" value="<%=user.getTel()%>">
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<button type="button" onclick="check()">수정하기</button>
						<button type="reset">다시쓰기</button>
					</td>
				</tr>
				
			</table>
			
		</form>
		
		<div style='text-align:center'><a href='javascript:winClose()'>닫 기</a></div>
		
	</div>

</body>
</html>