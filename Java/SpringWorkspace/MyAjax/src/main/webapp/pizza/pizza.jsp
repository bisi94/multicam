<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1"> <!--모바일 반응형 지원 -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>pizza.jsp</title>
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


<style type="text/css">
	/* body,h2{
		font-family:돋움,돋움체,verdana;
		color:navy;
	} */
</style>

<script>
	let req=false;//전역변수
	const init=function(){
		try {
			req=new XMLHttpRequest();		
			console.log('req: '+req);
		} catch (e) {
			alert('req생성 실패');
		}
		
	}//init()--------------------
	
	const getUserInfo=function(){
		//alert('a')
		//사용자가 입력한 연락처를 알아내자
		let phone=$('#phone').val(); //id가 phone인 객체의 밸류값을 let phone에 저장함
		//alert(phone);
		//let url='pizzaResult.jsp?phone='+phone;//text로 응받받을 시
		let url='pizzaResultXml.jsp?phone='+phone;//xml로 응받받을 시
		//Ajax요청을 보내기 위해 필요한 객체 => XMLHttpRequest객체
		//[1] 요청을 준비하는 단계 => open(요청방식, url, [비동기여부])
		req.open('GET', url, true);// true=>비동기방식으로 요청을 보낸다(디폴트)
		
		//[2] 요청을 보내기 전에 onreadystatechange 속성값에 콜백함수 지정
		req.onreadystatechange = updatePage;
		
		//[3] 요청을 전송한다 => send(파라미터데이터)
		req.send(null);
		//get방식: null을 파라미터로 전달
		//post방식: send(phone=1111-1111&userid=hong)의 형식으로 전달 
	}//--------------------------
	
	//1. xml로 받을 경우
	const updatePage=function(){
		if(req.readyState==4 && req.status==200){
			//응답유형이 text 형식이면 responseText로 받고
			//			xml 형식이면 responseXML로 받는다
			//			json 형식이면? responseText로 받은 뒤에 JSON.parse() 함수를 이용해서 문자열을 json객체로 변환해야 한다
			let res=req.responseXML;
			//alert(res);		//XMLDocument 객체로 반환된다 ==> 이 문서를 파싱하여 필요한 데이터를 추출한다
			let idx=$(res).find('idx').text(); //회원번호
			
			let name=$(res).find('name').text();
			let addr=$(res).find("addr").text();
			//tel태그의 type속성을 찾아보자. attr('속성명')
			//props('속성명')
			//attr('속성명')
			
			let telType=$(res).find("tel").attr("type");
			let tel=$(res).find('tel').text();
			
			if(parseInt(idx)==0){//비회원인 경우
				$('#nonUser').show(1000); //비회원입력폼을 보여주기
				$('#userInfo').html("").hide(1000); //1초안에 유저정보란을 가려주기
				$('#addr').val("").focus();
			}else{//회원인 경우
				let str=`
					<h3>주문자: \${name}</h3>
					<h3 class='text-success'>\${addr}</h3>
					<h3>연락처:[\${telType}] \${tel}</h3>
					`;
					
				$('#userInfo').html(str).show(1000);
				$('#nonUser').hide(1000);
				$('#addr').val("");
			}//else---------------------------
		}
	}//-----------------------
	
	//2. text로 받을 경우
	const updatePage1=function(){
		//alert(req.readyState+" / "+req.status);
		if(req.readyState==4 && req.status==200){
			//성공적인 응답이 왔다면 ==> 응답데이터를 보냈다는 의미
			//alert('success')
			//응답유형이 text 형식이면 responseText로 받고
			//			xml 형식이면 responseXML로 받는다
			//			json 형식이면?
			let res=req.responseText;
			//alert(res);
			//'#' 구분자를 기준으로 쪼개어 배열에 할당
			let tokens=res.split('#');
			//tokens[0]:회원번호, tokens[1]:이름...
			let str=`<h3>회원 이름: \${tokens[1]}</h3>
			<h3>회원 주소: \${tokens[2]}</h3>
			`;
			$('#userInfo').html(str);
		}
			
			
	}//-------------------------
	
	
	window.onload=init
</script>

</head>
<body>
<div class="section">
<div class="container py-5">

	<h1 class="m-4 text-center">Pizza Order Page</h1>
	
	<form role="form" class="form-horizontal" 
	name="orderF" id="orderF"
	 action="order.jsp" method="POST">
		<div class="form-group">
			<p class="text-info">
			<b>귀하의 전화번호를 입력하세요:</b>
			<input type="text" size="20" name="phone" id="phone" onchange="getUserInfo()"  class="form-control"/>
			</p>
			<p class="text-danger">
			<b>
				귀하가 주문하신 피자는 아래 주소로 배달됩니다.
			</b>	
			</p>
			<div id="userInfo"></div>
			<div id="address"></div>
			<!-- 비회원 입력 폼 : 비회원일 경우 주소입력 폼을 보여주자.-->
			<div id="nonUser" style="display:none;">
				주소: <input type="text" name="addr" id="addr"
						size="60" style="border:2px solid maroon;" class="form-control"/>
			</div>
			<!-- ------------------------------------------- -->
			<p class="text-info">
			<b>주문 내역을 아래에 기입하세요</b></p>
			<p>
				<textarea name="orderList"
				 id="orderList" rows="6" cols="50" class="form-control"></textarea>
			</p>
			<p>
				<input type="submit" value="Order Pizza" class="btn btn-primary"/>
			</p>
		</div>
	</form>
</div>
</div>
</body>
</html>
