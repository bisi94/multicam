<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>
	.container{
		overflow-y:auto;
	}
</style>

<div class="container">
	<h1 class="text-center mt-4">Spring Board 목록</h1>
	<c:if test="${paging.findKeyword !=''}">
		<h3 class="text-center mt-4">검색어 : ${paging.findKeyword}</h3>
	</c:if>
	
	<br><br>
	<a href="write">글쓰기</a>
	<br><br>
	<!-- 검색 form----------------------------------- -->
	<div id="divFind" class="text-center">
		<form name="findF" id="findF" action="list"
			class="form-inline">
			<select name="findType" id="findType" class="form-control m-3">
				<option value="0">::검색 유형::</option>
				<option value="1">제 목</option>
				<option value="2">작성자</option>
				<option value="3">글내용</option>
			</select> 
			<input type="text" name="findKeyword" id="findKeyword"
				placeholder="검색어를 입력하세요" class="form-control m-3" required>
			<button class="btn btn-primary">검 색</button>
		</form>
	</div>
	<!-- ------------------------------------------- -->
	
	<br><br>
	<table class="table">
		<tr>
			<th width="10%">글번호</th>
			<th width="40%">제목</th>
			<th width="20%">작성자</th>
			<th width="20%">작성일</th>
			<th width="10%">조회수</th>
			
		</tr>
		<c:if test="${boardArr==null or empty boardArr}">
			<tr>
				<td colspan="5">
					<b>데이터가 없습니다</b>
				</td>
			</tr>
		</c:if>
		<c:if test="${boardArr!=null and not empty boardArr}">
		<!-- ----------------------------- -->
			<c:forEach var="board" items="${boardArr}">
				<tr>
					<td>${board.num}</td>
					<td align="left" style="padding-left:20px">
					
					<!-- 답변 레벨에 따라 들여쓰기 -->
					<c:forEach var="k" begin="1" end="${board.lev}">
						&nbsp;&nbsp;&nbsp;
					</c:forEach>
					<c:if test="${board.lev>0}">
						<img src="${myctx}/resources/images/rea.png">
					</c:if>
					
					<!-- 
					/board/view?num=12 ==>@RequestParam
					/board/view/12     ==>@PathVariable
					 -->
						<a href="view/${board.num}">${board.subject}</a>
						
						<c:if test="${board.filename ne null}">
							<img src="../resources/images/attach.png" style="width:1em">
						</c:if>
						
					</td>
					<td>${board.userid}</td>
					<td>
						<fmt:formatDate value="${board.wdate}" pattern="yyyy-MM-dd"/>
					</td>
					<td>${board.readnum}</td>
				</tr>
			</c:forEach>
		<!-- ----------------------------- -->
		</c:if>
		
		<tr>
			<td colspan="3">
				${pageNavi}
				
			</td>
			<td colspan="2">
				총 게시글 수 : <span style="color:red; font-weight:bold">
				${paging.totalCount} </span> 개
			</td>
		</tr>
		
	</table>
	
</div>




