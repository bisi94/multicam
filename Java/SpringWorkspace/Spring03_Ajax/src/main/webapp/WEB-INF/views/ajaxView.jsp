<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>ajaxView</title>

<!--CDN ����  -->
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
  			//$.ajax({�ɼǵ�}) : jquery�Լ�, ajax ����� �����ϴ� �Լ�
  			$.ajax({
  				type:'get', //��û���
  				url:'ajaxText?phone=1111-1234', //��ûurl
  				dataType:'text', //��������
  				cache:false, //ĳ�� ��� ����
  				success:function(res){ //������������ �� ���
  					//alert(res);
  					let tks=res.split('#');
  					let str=`
  					<h3>��ȣ:\${tks[0]} </h3>
  					<h3>�̸�:\${tks[1]} </h3>
  					<h3>�ּ�:\${tks[2]} </h3>
  					<h3>����ó:\${tks[3]} </h3>
  					`
  					$('#result').html(str).addClass('alert alert-success')
  				},
  				error:function(err){ //������ �� ���
  					alert('error: '+err.status+", "+err.responseText)
  				}
  			})
  		})//btn1---------------------
  		
  		//��ûurl: ajaxXml?phone=2222-3333
  		//��������:xml
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
  				error:function(err){ //������ �� ���
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
  				error:function(err){ //������ �� ���
  					alert('error: '+err.status)
  				}
			})
			
		})//btn3---------------------
  		
		$('#btn4').click(function(){
			$.ajax({ //ajax�� �θ��� ��ɾ�
				type:'post',
				url:'ajaxJsonList',
				data:"dong=���嵿",//post����϶� ������ �����͸� ���
				cache:false,
				success:function(res){
					//alert(res);//object Object
					//alert(JSON.stringify(res))
					//res==>�迭 ���·� �´�
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
	<h1>JSON���·� �����͸� �޾ƺ��ô�</h1>
	<h2>JSON�̶�? - JavaScript Object Notation</h2>
	<h2>�ڹٽ�ũ��Ʈ���� �̿��ϴ� ��ü ���·� �����͸� ǥ���ϴ� ���</h2>
	<h3>JSON��ü���� ���ڿ�, ����, �迭, boolean, null ���� �� �� �ִ�.</h3>
	
	<hr color='blue'>
	<button class="btn btn-info" id="btn1">Text������ �޴� ���</button>
	<button class="btn btn-warning" id="btn2">Xml������ �޴� ���</button>
	<button class="btn btn-success" id="btn3">JSON������ �޴� ���(��ü)</button>
	<br><br>
	<button class="btn btn-primary" id="btn4">JSON������ �޴� ���(�迭)</button>			
	<button class="btn btn-secondary" id="btn5">Naver OpenAPI���� �������� �޾ƿ���</button>			
	
	<hr color='red'>
	<div id="result" class="mt-4">
	
	</div>
	</div>
</body>
</html>







