<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!--  board-->
<style>
	.table td{
		text-align:left;
	}
</style>

<div class="container">

    <h1>Board 글쓰기</h1>
    <br><br>
   <!--  
    파일업로드시
    [1] method="post"
    [2] enctype="multipart/form-data"
    -->
    <form name="boardF" action="writeEnd.me" method="post" enctype="multipart/form-data">
    	<table class="table table-bordered">
			<tr>
				<th style="width:20%">제목</th>
				<td style="width:80%">
					<input type="text" name="subject"
					 id="subject" placeholder="Subject" class="form-control">
				</td>
			</tr>
			<tr>
				<th style="width:20%">글쓴이</th>
				<td style="width:80%">
					<input type="text" name="name"
					 id="name" placeholder="Name" class="form-control">
				</td>
			</tr>
			<tr>
				<th style="width:20%">글내용</th>
				<td style="width:80%">
					<textarea rows="10" cols="50" name="content"
					id="content" placeholder="Content" class="form-control"></textarea>
				</td>
			</tr>
			<tr>
				<th style="width:20%">비밀번호</th>
				<td style="width:80%">
					<input type="password"
					 name="pwd" id="pwd" placeholder="Password" class="form-control">
				</td>
			</tr>
			<tr>
				<th style="width:20%">첨부파일</th>
				<td style="width:80%">
					<input type="file"
					 name="filename" id="filename"
					  placeholder="Attach File" class="form-control">
				</td>
			</tr>
			<tr>
				<td colspan="2" class="text-center" style="text-align:center">
					<button type="button" onclick="check()" class="btn btn-success" id="btnWrite">글쓰기</button>
					<button type="reset" class="btn btn-warning" id="btnReset">다시쓰기</button>
				</td>
			</tr>
		</table>
    	
    </form>
    <script>
    	function check(){
    		if(!boardF.name.value){
    			alert('글 작성자를 입력하세요');
    			boardF.name.focus();
    			return;
    		}
			if(!boardF.pwd.value){
				alert('글 비밀번호를 입력하세요');
    			boardF.pwd.focus();
    			return;
    		}
			boardF.submit();
			
    	}//check()----------------------------
    </script>
    
</div>








