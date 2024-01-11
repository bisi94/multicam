<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>

<!--CDN 참조  -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<style>

	#reviewList{
		width:100%;
	}
	#reviewList li{
		float:left;
		list-style:none;
	}
	#reviewList li:nth-child(3n+1){
		width:30%;
	}
	#reviewList li:nth-child(3n+2){
		width:70%;
	}
	#reviewList li:nth-child(3n){
		clear:both; /*float속성 해제*/
	}
	

</style>

</head>
<body>
	<div class="container padding py-4">

		<button class="btn btn-outline-primary" data-toggle="modal"
			data-target="#reviewModal">상품평 쓰기</button>
		<a data-toggle="modal" href="#reviewModal">상품 리뷰</a>
		<!-- ======================================= -->
		<!-- The Modal -->
		<div class="modal" id="reviewModal">
			<div class="modal-dialog modal-xl">
				<div class="modal-content">

					<!-- Modal Header ------------------------------------------------->
					<div class="modal-header">
						<h4 class="modal-title">Modal Heading</h4>
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>

					<!-- Modal body --------------------------------------------------->
					<div class="modal-body">
						<div class="row">
						<!-- md,lg,lx(desktop), sm(tablet) xs(mobile) -->
						<div class="col-md-6 col-sm-12 col-xs-12">
						
						<!-- 글쓰기 폼 ---------------------- -->
							<form name="rf" id="rf" method="post" enctype="multipart/form-data">
							<!-- hidden data -->
							<input type="hidden" name="userid" id="userid" value="min"> 
							<input type="hidden" name="pnum" id="pnum" value="1">
							<input type="hidden" name="num" id="num">
								<table class="table table-responsive">
									<tr>
										<th colspan="4" class="text-center">
											<h3>::상품 후기::</h3>
										</th>
									</tr>
									<tr>
										<th>평가점수</th>
										<td><input type="radio" name="score" id="score1" value="1">1점 
											<input type="radio" name="score" id="score2" value="2">2점 
											<input type="radio" name="score" id="score3" value="3">3점
											<input type="radio" name="score" id="score4" value="4">4점
											<input type="radio" name="score" id="score5" value="5">5점
										</td>
										<th>작성자</th>
										<td>민밍밍[min]</td>
									</tr>
									<tr>
										<th>제목</th>
										<td colspan="3"><input type="text" name="title"
											id="title" placeholder="Title" class="form-control">
										</td>
									</tr>
									<tr>
										<th>상품평</th>
										<td colspan="3"><textarea name="content" id="content"
												rows="2" class="form-control"></textarea></td>
									</tr>
									<tr>
										<th>이미지업로드</th>
										<!-- input name을 mfilename으로 ==> 컨트롤러에서 @RequestParam("mfilename") MultipartFile mf으로 받아야 함 -->
										<td colspan="2"><input type="file" name="mfilename"
											id="filename" accept="image/*" class="form-control">
										</td>
										<td><a type="button" class="btn btn-success" id="btn1"
											onclick="send()">글쓰기</a></td>
									</tr>
								</table>
								<div id="prodImg">
									<!-- 업로드한 상품 이미지 -->
								</div>

						</form>
						</div> <!-- col end -->
						<div class="col-md-6 col-sm-12 col-xs-12">
						<!-- 리뷰 목록 ---------------------- -->
							<h3>리뷰 목록</h3>
							<ul id="reviewList">
								<!-- <li>
									<img src="resources/images/person.PNG" class="img img-thumbnail">
								</li>
								<li>
									<span class="text-danger">★★★★★</span><br>
									<span>[title]</span><br>
									상품평이 들어가는 위치임
								</li>
								<li></li>
								li row end(한 행에 li 3개씩 들어갈 예정) -->
								
								
								
							</ul>
							
						</div><!-- .col end -->
						</div><!-- .row end -->
					</div>

					<!-- Modal footer ------------------------------------------------->
					<div class="modal-footer">
						<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
					</div>

				</div>
			</div>
		</div>
		<!-- ======================================= -->
	</div>
	
	<script>
		$(function(){
			//전체 리뷰 목록 가져오기
			getReviewAll();
		})
	
	
		const send=function(){
			let isCheck=$('input[name^="score"]').is(":checked");
			//alert(isCheck)
			if(!isCheck){
				alert('평가점수를 체크하세요');
				$('#score5').focus();
				return;
			}
			
			if(!$('#title').val()){
				alert('제목을 입력하세요');
				$('#title').focus();
				return;
			}
			if(!$('#content').val()){
				alert('상품평을 입력하세요');
				$('#content').focus();
				return;	
			}
			//$('#rf').prop('action', 'reviews')
			//$('#rf').submit(); //==> ajax 할 때 요청을 전송해야 함
			
			//[1]파일 업로드를 하지 않을 경우
			//let paramData=$('#rf').serialize(); //input data를 파라미터 문자열로 직렬화해서 반환함
			//alert(paramData);//userid=aaa&pnum=1&title=bbb...
			//writeReview(paramData);
			
			let btnTxt=$('#btn1').text();
			//alert(btnTxt);
			
			//[2]파일 업로드를 하는 경우 => FormData를 이용한다
			let form=$('#rf')[0]; //form객체
			//alert(form);
			let formData=new FormData(form);//파일 업로드일 경우는 FormData를 전송한다.
			//formData.append("name","홍길동")
			//input type="file"의 name을 ReviewVO의 filename과는 다르게 주어야 한다. MultipartFile타입으로 받아야 하기 때문
			if(btnTxt=='글쓰기'){
				writeReviewAndFile(formData);
			}else if(btnTxt=='글수정'){
				//updateReviewAndFile(formData);
				updateReview();
			}			
		}//send()-----------------
		//수정시 파일업로드 하지않는 경우
		function updateReview(){
			//전송할 데이터를 json객체로 만든다
			let jsonData={
				userid:$('#userid').val(),//로그인 처리후 백엔드에서 세션에서 꺼내 사용
				pnum:$('#pnum').val(),
				title:$('#title').val(),
				content:$('#content').val(),
				score:$('input[name="score"]:checked').val(),
				num:$('#num').val()
			}
			//console.dir(jsonData);
			
			$.ajax({
				type:'put',
				url:'reviews',
				data:JSON.stringify(jsonData),//json데이터를 문자열로 직렬화해서 보낸다
				dataType:'json',
				contentType:'application/json; charset=UTF-8',
				cache:false
			})
			.done((res)=>{
				//alert(JSON.stringify(res));
				if(res.result=='success'){
					getReviewAll();//전체목록 가져오기
					$('#btn1').text('글쓰기');
					$('#title').val('');
					$('#content').val('');
				}else{
					alert('수정 실패');
				}
			})
			.fail((err)=>{
				alert('error: '+err.status);
			})
		}
		
		function updateReviewAndFile(formData){
			let file=$('#filename')[0].value;
			alert(file)
			$.ajax({
				type:'put',
				url:'reviews',
				data:formData,
				dataType:'json',
				processData:false,//true=>get, false=>post
				contentType:false,
				cache:false
			})
			.done((res)=>{
				alert(JSON.stringify(res));
			})
			.fail((err)=>{
				alert('error: '+err.status);
			})
		}//----------------------------
		
		function writeReviewAndFile(formData){
			$.ajax({
				type:'post',
				url:'reviews',
				data:formData,//FormData를 전달한다
				dataType:'json',
				processData:false, //일반적으로 서버에 전달하는 데이터는 query String 형태이다 false를 주면 ㅇㅣ걸 사용하지 않는다는 의미
				contentType:false, //false를 주면 "multipart/form=data"
				success:function(res){
					if(res.result=='success'){
						getReviewAll();
					}else{
						alert('리뷰 등록 실패')
					}
				},
				error:function(err){
					alert('error: '+err.status);
				}
			})
		}//---------------------------------
		
		//파일 업로드 하지않는 일반 업로드
		function writeReview(paramData){
			$.ajax({
				type:'post',
				url:'reviews',
				data:paramData, //파라미터 데이터
				dataType:'json', //응답유형
				cache:false,
				success:function(res){
					//alert(JSON.stringify(res));
					if(res.result=='success'){
						getReviewAll();
					}else{
						alert('리뷰 등록 실패')
					}
					
				},
				error:function(err){
					alert('error: '+err.status);
				}
			})
		}
		
		//리뷰목록 가져오기	GET	/reviews	
		const getReviewAll=function(){
			$.ajax({
				type:'get',
				url:'reviews?pnum=1',
				dataType:'json',
				cache:false,
				success:function(res){
					//alert(JSON.stringify(res));
					showList(res);
				},
				error:function(err){
					alert('error: '+err.status);
				}
			})
		}//getReviewAll------------------
		
		const showList=function(res){
			let str=``;
			for(let i=0; i<res.length; i++){
				let obj=res[i];
				let d=new Date(obj.wdate);
				let yy=d.getFullYear();
				let mm=d.getMonth()+1; //jan:0 feb:1 ... dec:11
				let dd=d.getDate();
				let dStr=yy+"-"+mm+"-"+dd;

				if(obj.filename!=null){
					str+=`<li><img src="resources/review_images/\${obj.filename}" class="img img-thumbnail"></li>`;
				}else{
					str+=`<li><img src="resources/images/person.PNG" class="img img-thumbnail"></li>`;
				}
				str+=`<li><span class="text-danger">`;
				for(let k=0; k<obj.score; k++){
					str+=`★`
				}//for-----------
				str+=`</span>[\${dStr}]<br>
				<span>\${obj.title}</span><br>
				<span>\${obj.userid}</span><br>
				\${obj.content}<br>`
				//글쓴이와 로그인한 사람이 일치한다면 수정/삭제 링크걸기
				if(obj.userid=='min'){
					str+=`[<a href="javascript:edit('\${obj.num}')">수정</a>]`;
					str+=`[<a href="javascript:del('\${obj.num}')">삭제</a>]`;
				}
				str+=`</li>`;
				str+=`<li></li>`;
			}//for------------------
			$('#reviewList').html(str);
		}//------------------------
		//DELETE	/reviews/num =>삭제처리
		//Get		/reviews/num =>조회처리
		//리뷰글 삭제 함수
		const del=function(num){
			//alert(num);
			$.ajax({
				type:'delete',
				url:'reviews/'+num,
				dataType:'Json',
				cache:false,
			})
			.done(function(res){
				//alert(JSON.stringify(res))
				if(res.result=="success"){
					getReviewAll();//전체목록 가져오기
				}else{
					alert("삭제실패");
				}
			})
		    .fail(function(err){
			  alert('error '+err.status)
		  	})
			  
		}//del-----------------------
		
		const edit= function(num){
			//alert(num);
			$.ajax({
				type:'get',
				url:'reviews/'+num,
				dataType:'json',
				cache:false
			})
			.done(function(res){
				//alert(JSON.stringify(res));
				$('#title').val(res.title)
				$('#score'+res.score).prop('checked', true);
				$('#content').val(res.content);
				$('#userid').val(res.userid);
				$('#pnum').val(res.pnum);
				$('#num').val(res.num);//글번호(pk)설정
				if(res.filename!=null){
					$('#prodImg').html("<img src='resources/review_images/"+res.filename+"'>");
				}
				$('#btn1').text('글수정')
			})
			.fail(function(err){
				alert("error: "+err.status);
			})
		}//edit--------------------------
		
	</script>
	
</body>
</html>