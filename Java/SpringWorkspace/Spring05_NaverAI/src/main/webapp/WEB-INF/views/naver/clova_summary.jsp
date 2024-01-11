<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Summary</title>

	<script>
		$(function(){
			$('#btnSummary').click(function(){
				
				let title=$('#title').val();
				let content=$('#news_content').val();
				if(!title||!content){
					alert('제목과 글 내용을 입력하세요');
					return;
				}
				let jsonData={
						title:title,
						content:content
				}
				
				$.ajax({
					type:'post',
					url:'summaryEnd',
					data:jsonData,
					dataType:'text',
					success:function(res){
						//alert(res);
						$('#result').html("<h4>"+res+"</h4>");
					},
					error:function(err){
						alert("error: "+err.status);
					}
				})
				
			})//-----------------
		})//$() end----------
	</script>

</head>
<body>

<div id="wrap" class="container py-5 text-center">
	<h1>Naver Clova Summary</h1>
	<br>
	제 목 : <input type="text" id="title" name="title" placeholder="News제목" style="width:650px">
	<br><br>
	글내용:<textarea rows="20" cols="100" name="content" id="news_content" placeholder="News내용"></textarea>
	<br><br>
	<button id="btnSummary">문장 요약하기</button>
	<hr color='red'>
	<div id="result">
		<!-- 문장 요약 여기에 들어옴 -->
	</div>
</div>

</body>
</html>