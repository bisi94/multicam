<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:include page="/top.jsp" />
<script>
	function openWin(){
		let url="../idCheck.do";
		win=open(url,"idCheck", "width=600, height=600, left=200, top=200");
	}

	function check(){
		//이름
		if(!mf.name.value){
			alert("이름을 입력하세요");
			mf.name.focus();
			return;
		}
		
		//아이디
		if(!mf.userid.value){
			alert("아이디 중복체크를 하세요");
			mf.userid.focus();
			return;
		}
		//비밀번호
		if(!mf.userpwd.value){
			alert("비밀번호를 입력하세요");
			mf.userpwd.focus();
			return;
		}
		
		//비밀번호확인
		if(mf.userpwd.value!=mf.userpwd2.value){
			alert("비밀번호가 일치하지 않습니다");
			mf.userpwd2.select();
			return;
		}
		//연락처123
		if(!mf.hp1.value||!mf.hp2.value||!mf.hp3.value){
			alert("연락처를 입력하세요");
			mf.hp2.focus();
			return;
		}
		//isNaN(obj) : 숫자가 아니면 true를 반환하는 함수
		if(isNaN(mf.hp2.value)){
			alert("연락처는 숫자를 입력하세요");
			mf.hp2.select();
			return;
		}
		if(isNaN(mf.hp3.value)){
			alert("연락처는 숫자를 입력하세요");
			mf.hp3.select();
			return;
		}
			
		mf.submit();
		
	}//check()--------------------

</script>
	<div class="container">
		<h1>[${loginId}]님 회원정보 수정</h1>
		<br><br>
		<!-- signupEnd.do는 서블릿의 url-mapping시 url pattern으로 등록할 예정 -->
		<form name="mf" action="memberUpdateEnd.do" method="post">
		
		<table border="1" id=userTable>
				<tr>
					<th>이름</th>
					<td>
						<input type="text" name="name" value="${user.name}">
									<%-- ${user.name} ==> <%=user.getname()%>와 동일 --%>
					</td>
				</tr>
				<tr>
					<th>아이디</th>
					<td>
						<input type="text" name="userid" readonly value="${user.userid}">
						<button type="button" class="btn" onclick="openWin()">중복체크</button>
					</td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td>
						<input type="password" name="userpwd">
					</td>
				</tr>
				<tr>
					<th>비밀번호 확인</th>
					<td>
						<input type="password" name="userpwd2">
					</td>
				</tr>
				<tr>
					<th>연락처</th>
					<td>
						<select name="hp1" class="hp">
							<option value="010">010</option>
							<option value="011">011</option>
							<option value="070">070</option>
						</select>
					
						<input type="text" name="hp2" value="${user.hp2}" class="hp" maxlength="4">
						<input type="text" name="hp3" value="${user.hp3}" class="hp" maxlength="4">
					</td>
				</tr>
				<tr>
					<th>우편번호</th>
					<td>
						<input type="text" name="post" value="${user.post}" class="post">
						<button type="button" class="btn">우편번호 찾기</button>
					</td>
				</tr>
				<tr>
					<th>주소</th>
					<td>
						<input type="text" name="addr1" value="${user.addr1}" class="addr"><br>
						<input type="text" name="addr2" value="${user.addr2}" class="addr">
					</td>
				</tr>
				<tr>
					<th>마일리지</th>
					<td>${user.mileage} 점</td>
				</tr>		
				<tr>
					<th>등록일</th>
					<td>${user.indate}</td>
				</tr>		
				<tr>
					<td colspan="2" align="center" style="text-align:center; padding:8px;">
						<button type="button" class="btn" onclick="check()">수정하기</button>
						<button type="reset" class="btn">다시쓰기</button>
					</td>
				</tr>
				<table>
					<br><br>
					<a>활동중지</a> | <a>회원탈퇴</a>
				</table>
						
			</table>
		
		</form>
	</div>
	
<jsp:include page="/foot.jsp" />







