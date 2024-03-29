<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="container">
	<!-- 
		/memo GET방식 요청 => 메모 등록 폼을 보여주고
		/memo POST방식 요청 => 메모글을 등록하는 로직을 수행
	 -->
	 <script>
		function check(){
			if(!mf.name.value.trim()){
				alert('작성자를 입력하세요');
				mf.name.focus();
				return false;
			}
			
			return true;
		}//-------------------------------
	 
	 </script>
	<h1  class="text-center mt-4">::한줄 메모장::</h1>
	<form name="mf" id="mf" action="memo" method="post" onsubmit="return check()">
		<table class="table mt-3">
			<tr>
				<th width="20%">작성자</th>
					
				<td width="80%">
					<input type="text" name="name" id="name"  class="form-control"
					placeholder="Name">
				</td>
			</tr>
			
			<tr>
				<th width="20%">메모 내용</th>
					
				<td width="80%">
					<input type="text" name="msg" id="msg" maxlength="200" class="form-control" 
					placeholder="Message">
				</td>
			</tr>
			
			<tr>
				<td colspan="2" class="text-center">
					<button  class="btn btn-warning">글 남기기</button>
					<button type="reset" class="btn btn-danger">다시 쓰기</button>
				</td>
			</tr>
		</table>
	</form>

</div>