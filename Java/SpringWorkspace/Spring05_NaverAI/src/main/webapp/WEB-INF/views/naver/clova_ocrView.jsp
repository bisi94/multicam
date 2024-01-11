<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<script>
	$(function(){
		$('#frm').on('submit',function(event){
			event.preventDefault();
			let fileName=$('#uploadFile').val().split("\\").pop();
			//alert(fileName)
			let form=$('#frm')[0];
			let formData=new FormData(form);
			$.ajax({
				type:'post',
				url:'ocrEnd',
				data:formData,
				dataType:'text',
				processData:false,
				contentType:false,
				cache:false
			})
			.done((res)=>{
				//alert(res);
				$('#result').html(res);
				$('#imgDiv').empty();
				$('#imgDiv').html("<img src='/ocr_images/"+fileName+"' width='40%'>")
			})
			.error((err)=>{
				alert('error: '+err.status);
			})
		})//on------------------------------
		
	})//end---------------------------------
</script>	
	
<div class="container py-5 text-center">
	<h1>OCR -이미지에서 텍스트 추출</h1>
	<br><br>
	<form id="frm" method="post" enctype="multipart/form-data">
		서버에 이미지 업로드
		<input type="file" id="uploadFile" name="uploadFile">
		<button class="btn btn-primary">결과보기</button>

	</form>
	<br><br>
	<hr>
	<h2>OCR - 이미지에서 텍스트 추출 결과</h2>
	<br>
	<div id="result" class="my-3" class='alert alert-info'>

	</div>
	<div id="imgDiv" class="my-3">

	</div>

</div>