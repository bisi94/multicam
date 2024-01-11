<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>ajaxView</title>

<!--CDN 참조  -->
<!-- Latest compiled and minified CSS -->
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
  <style>
  .fakeimg {
    height: 200px;
    background: #aaa;
  }
  </style>
  
  <script>
  	$(function(){
  		$('#btn1').click(function(){
  			//alert('1')
  			//$.ajax({옵션들}) : jquery함수, ajax 통신을 수행하는 함수
  			$.ajax({
  				type:'get', //요청방식
  				url:'ajaxText?phone=1111-1234', //요청url
  				dataType:'text', //응답유형
  				cache:false, //캐시 사용 안함
  				success:function(res){ //성공적응답이 올 경우
  					//alert(res);
  					let tks=res.split('#');
  					let str=`
  					<h3>번호:\${tks[0]} </h3>
  					<h3>이름:\${tks[1]} </h3>
  					<h3>주소:\${tks[2]} </h3>
  					<h3>연락처:\${tks[3]} </h3>
  					`
  					$('#result').html(str).addClass('alert alert-success')
  				},
  				error:function(err){ //에러가 날 경우
  					alert('error: '+err.status+", "+err.responseText)
  				}
  			})
  		})//btn1---------------------
  		
  		//요청url: ajaxXml?phone=2222-3333
  		//응답유형:xml
		$('#btn2').click(function(){
			$.ajax({
				type:'get',
				url:'ajaxXml?phone=2222-1234',
				dataType:'xml',
				cache:false,
				success:function(res){
					//alert(res);
					let idx=$(res).find('idx').text();
					let	name=$(res).find('name').text();
					let	addr=$(res).find('addr').text();
					let	phone=$(res).find('phone').text();
					
					let str=`
					<h3>\${idx}</h3>
					<h3>\${name}</h3>
					<h3>\${addr}</h3>
					<h3>\${phone}</h3>
					`
					$('#result').html(str).addClass('alert alert-danger');
					
  				},
  				error:function(err){ //에러가 날 경우
  					alert('error: '+err.status)
  				}
			})
			
		})//btn2---------------------
		
		$('#btn3').click(function(){
			$.ajax({
				type:'get',
				url:'ajaxJson?phone=2222-1234',
				dataType:'json',
				cache:false,
				success:function(res){
					//JSON.parse()<=> JSON.stringify()
					//json => string
					//alert(JSON.stringify(res));
					let idx=res.idx;
					let	name=res.name;
					let	addr=res.addr;
					let	phone=res.phone;
					
					let str=`
					<h3>\${idx}</h3>
					<h3>\${name}</h3>
					<h3>\${addr}</h3>
					<h3>\${phone}</h3>
					`
					$('#result').html(str).addClass('alert alert-primary');
					
  				},
  				error:function(err){ //에러가 날 경우
  					alert('error: '+err.status)
  				}
			})
			
		})//btn3---------------------
  		
		$('#btn4').click(function(){
			$.ajax({ //ajax를 부르는 명령어
				type:'post',
				url:'ajaxJsonList',
				data:"dong=광장동",//post방식일때 전달할 데이터를 기술
				cache:false,
				success:function(res){
					//alert(res);//object Object
					//alert(JSON.stringify(res))
					//res==>배열 형태로 온다
					let str="<ul class='list-group'>";
					for(let i=0; i<res.length; i++){
						let obj=res[i];
						str+=`
						<li class="list-group-item">
						\${obj.idx}, \${obj.name}, \${obj.addr}, \${obj.phone}
						</li>
						`
					}
					str+="</ul>";
					$('#result').html(str);
				},
				error:function(err){
					alert('error: '+err.status);
				}
			})
		})//btn4---------------------
		
		
  	})//$() end------------
  </script>

</head>
<body>
	<div class="container py-5">
	<h1>JSON형태로 데이터를 받아봅시다</h1>
	<h2>JSON이란? - JavaScript Object Notation</h2>
	<h2>자바스크립트에서 이용하는 객체 형태로 데이터를 표현하는 방식</h2>
	<h3>JSON객체에는 문자열, 숫자, 배열, boolean, null 값만 들어갈 수 있다.</h3>
	
	<hr color='blue'>
	<button class="btn btn-info" id="btn1">Text데이터 받는 경우</button>
	<button class="btn btn-warning" id="btn2">Xml데이터 받는 경우</button>
	<button class="btn btn-success" id="btn3">JSON데이터 받는 경우(객체)</button>
	<br><br>
	<button class="btn btn-primary" id="btn4">JSON데이터 받는 경우(배열)</button>			
	<button class="btn btn-secondary" id="btn5">Naver OpenAPI에서 도서정보 받아오기</button>			
	
	<hr color='red'>
	<div id="result" class="mt-4">
	
	</div>
	</div>
</body>
</html>







