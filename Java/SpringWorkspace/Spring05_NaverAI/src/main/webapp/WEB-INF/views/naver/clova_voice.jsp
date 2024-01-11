<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="container py-5 text-center">

	<h1>Clova Voice Premium</h1>

	<div>
		<textarea rows="7" cols="60" name="txt" id="txt"
			placeholder="내용을 입력하면 음성으로 서비스됩니다" class="form-control"></textarea>
		<button class="btn btn-danger" id="voiceBtn">음성확인</button>

	</div>
	<hr>
	<div class="alert alert-success">
		<audio id="ttsAudio" preload="auto" controls="controls"></audio>
	</div>

</div>

<script>
	$(function(){
		$('#voiceBtn').click(()=>{
			let str=$('#txt').val();
			alert(str);
			if(!str){
				alert('내용을 입력하세요');
				$('#txt').focus();
				return;
			}
			let url="voiceToText";
			$.ajax({
				type:'post',
				url:url,
				data:{content:str},
				dataType:'text', //네이버 응답유형은 바이너리 데이터==>blob==>java 컨트롤러에서 blob유형을 파일로 저장한 뒤 해당 파일명을 전송할 예정
				success:function(res){
					//alert(res);
					$('#ttsAudio').prop("src","upload/"+res);
					$('#ttsAudio').prop("autoplay", "autoplay")
				},
				error:function(err){
					alert('error: '+err.status);
				}
			})
			
		})
		
	})//$()------------------
</script>
