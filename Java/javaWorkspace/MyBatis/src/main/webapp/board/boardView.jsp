<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> <!-- 날짜포맷등에 쓰임 -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<style>
	td:nth-child(2n){
		text-align:left;
		padding-left:1.5em;
	}
</style>
<div class="container">
	<h1>Board 글내용 보기</h1>
	<br>
	<br> <a href="write.me">글쓰기</a>|<a href="list.me">글목록</a>| <br>
	<br>
	<br>
	<!-- eq: == 와 동일함 equals -->
<c:if test="${board eq null}">
	<h3>해당 게시글은 존재하지 않습니다</h3>
</c:if>
	<!-- ne: != 와 동일함 not equals -->
<c:if test="${board ne null}">
	<table class="table table-bordered">
		<tr>
			<td width="20%" class="m1"><b>글번호</b></td>
			<td width="30%">
			${board.no}
			</td>
			<td width="20%" class="m1"><b>작성일</b></td>
			<td width="30%">
			<fmt.formatDate value="${board.wdate}" pattern="yyyy-MM-dd hh:mm:ss" />
			</td>
		</tr>
		<tr>
			<td width="20%" class="m1"><b>글쓴이</b></td>
			<td width="30%">${board.name}</td>
			<td width="20%" class="m1"><b>조회수</b></td>
			<td width="30%">${board.readnum}</td>
		</tr>
		<tr>

			<td width="20%" class="m1"><b>첨부파일</b></td>
			<td colspan="3" class="text-left">&nbsp; 
			<!-- 첨부파일이 있다면 --> 
			<c:if test="${board.filename ne null}">
				<a href="${pageContext.request.contextPath}/upload/${board.filename}" download>
				 ${board.filename} </a>
				 
				 <!-- 첨부파일명을 모두 소문자로 변환해서 fname변수에 할당(확장자 체크 위해) -->
				 <c:set var="fname" value="${fn:toLowerCase(board.filename)}"/>
				 
				 <c:if test="${fn:endsWith(fname,'.png') or fn:endsWith(fname,'.jpg') or fn:endsWith(fname,'.gif')}">
				 <img src="${pageContext.request.contextPath}/upload/${board.filename}" style="width:3em">
				 </c:if>
				[ ${board.filesize} ]bytes
			</c:if>
			</td>
		</tr>
		<tr>
			<td width="20%" class="m1"><b>제목</b></td>
			<td colspan="3">${board.subject}</td>
		</tr>
		<tr>
			<td width="20%" class="m1"><b>글내용</b></td>
			<td colspan="3">
			
			<pre>${board.content}</pre> <!-- 엔터쳤을때 확인 -->
			
			</td>
		</tr>
		<tr>
			<td colspan="4" class="text-center"><a href="#"
				onclick="goEdit()">글수정</a>| <a href="#" onclick="goDel()">글삭제</a></td>
		</tr>
	</table>
</c:if>
	<br><br>
	<!-- 삭제 또는 수정 form 시작 --------------------------------->
	<form id="bf" name="bf" method="post">
		<!-- 수정 또는 삭제할 글번호 => hidden -->
		<input type="hidden" name="no" value="${board.no}">
		<div id="divPwd" style="display:none">
			<label for="pwd">비밀번호</label>
			<input type="password" name="pwd" id="pwd" placeholder="Password" required>
			<button class="btn" id="btn1"></button>
		</div>
	
	</form>
	<!--  --------------------------------------------------->


</div><!-- .container end -->

<script>
	function goDel(){
		let yn=confirm("게시글을 삭제하시겠습니까?");
		if(yn){
			let obj=document.getElementById('divPwd');
			let btn1=document.getElementById('btn1');
			obj.style.display='block';
			btn1.innerText='글 삭제';
			bf.action='delete.me';
		}
	}//goDel()-----------------------------
	
	function goEdit(){
			let obj=document.getElementById('divPwd');
			let btn1=document.getElementById('btn1');
			obj.style.display='block';
			btn1.innerText='글 수정';
			bf.action='edit.me';
	}//goEdit()-----------------------------
	
	
</script>











