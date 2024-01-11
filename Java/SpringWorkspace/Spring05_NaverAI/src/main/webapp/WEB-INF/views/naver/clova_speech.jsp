<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<script>
	$(function(){
		$('#frm').submit(function(event){
			event.preventDefault();//submit되지 못하게 막기
			
			let file=$('#mp3file')[0];
			if(file.files.length==0){
				alert('mp3파일을 첨부하세요');
				return;
			}
			
			let form=$('#frm')[0];
			let formData=new FormData(form);
			let fileName=formData.get("mp3file").name;
			//alert(fileName); //첨부파일명
			$.ajax({
				type:'post',
				data:formData,
				dataType:'json',
				url:'csrSpeech',
				cache:false,
				processData:false, //true:Get방식, false:Post방식
				contentType:false, //enctype="multipart/form-data"로 전송하려면 false로 지정한다
				success:function(res){
					//alert(JSON.stringify(res));
					//alert(typeof res);

					//string을 json유형으로 변환
					let jsonObj=JSON.parse(res.result);
					
					//alert(res.result.text);
					$("#result").html("<h3>"+jsonObj.text+"</h3>");
					
					//업로드한 오디오 파일 플레이시키기
					$('#sttAudio').prop("src","/upload/"+fileName);//파일명 설정
					$('#sttAudio').prop("autoplay","autoplay");//자동재생
					
				},
				
				error:function(err){
					alert('error: '+err.status);
				}
			})//$.ajax()-----------------
			
		})//#frm----------------------------
		
	})//$()--------------------------
	
</script>
	
<div class="container text-center py-5">
	<h1>Clova Speech Recognition</h1>
	<br>
	
	<form id="frm">
		<select name="language">
			<option value="Kor">한국어</option>
			<option value="Eng">영 어</option>
			<option value="Jpn">일본어</option>
			<option value="Chn">중국어</option>
		</select>
		<br><br>
		<label for="mp3file">MP3파일</label>
		<input type="file" name="mp3file" id="mp3file">
		<button class="btn btn-primary">업로드</button>
	</form>
	
	<hr>
	
	<div>
		<h2>STT(Speech to Text):음성을 텍스트로 변환한 결과</h2>
		<audio id="sttAudio" preload="auto" controls="controls"></audio>
	</div>
	
	<div id="result" class="alert alert-danger">
	
	</div>
</div>