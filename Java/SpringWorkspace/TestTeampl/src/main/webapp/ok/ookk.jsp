<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1"> <!--모바일 반응형 지원 -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>tourApiTest</title>
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
		alert(phone);
		//let url='pizzaResult.jsp?phone='+phone;//text로 응받받을 시
		//let url='pizzaResultXml.jsp?phone='+phone;//xml로 응받받을 시
		let url='pizzaResultJson.jsp?phone='+phone;//json으로 응답받을 시
		
		
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
	
	//3. json으로 받을 경우
	const updatePage=function(){
		if(req.readyState==4 && req.status==200){
			//json데이터 => responsText로 받는다(string)=> json객체로 문자열을 변환시켜야 한다
			//[1]JSON.parse(문자열)==> json객체로 변환한다
			//[2]JSON.stringify(json객체)==문자열로 변환
			let res=req.responseText;
			//alert(res+" typeof: "+typeof(res)); ==> string
			let obj=JSON.parse(res);
			//alert(obj+", typeof: "+typeof(obj));
			let idx=obj.idx;
			let name=obj.name;
			let tel=obj.phone;
			let addr=obj.addr;
			
			if(parseInt(idx)==0){//비회원인 경우
				$('#nonUser').show(1000); //비회원입력폼을 보여주기
				$('#userInfo').hide(1000); //1초안에 유저정보란을 가려주기
				$('#addr').val("").focus();
			}else{//회원인 경우
				let str=`
					<div class='alert alert-danger my-3'>
						<h3>주문자: \${name}</h3>
						<h3>연락처: \${tel}</h3>
						<h3>주 소: \${addr}</h3>
					</div>
				`;
				$('#userInfo').html(str);
				
				$('#nonUser').hide();
				$('#userInfo').show(); 
				$('#addr').val("");
			}//else---------------------------
			
		}
	}//-----------------------
	
	window.onload=init
</script>

</head>
<body>
<div class="section">
<div class="container py-5">

	<h1 class="m-4 text-center">I'm OK에요</h1>
	
	<form role="form" class="form-horizontal" 
	name="orderF" id="orderF"
	 action="order.jsp" method="POST">
		<div class="form-group">
			<p class="text-info">
			<b>관광지 검색을 원하는 지역명을 입력하세요:</b>
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
