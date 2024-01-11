<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- 부트스트랩 CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/test.css">

<!-- 부트스트랩 JavaScript (Popper.js와 jQuery 포함) -->
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="https://oapi.map.naver.com/openapi/v3/maps.js?ncpClientId=dsbc03s9xu"></script>
<script type="text/javascript"
	src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=dsbc03s9xu&submodules=geocoder"></script>

<title>Document</title>
</head>
<body>
	<div class="container">
		<div class="sidebar">
		
			<div class="sidebar2">
				<button class="sidebtn1 btn-outline-info" id="cat1">
					STEP 1<br> 대분류 코드 받아오기
				</button>
			</div>
			
			<div class="sidebar2">
				<button class="sidebtn2 btn-outline-info" id="cat2">
					STEP 2<br> 중분류 코드 받아오기
				</button>
			</div>
			
			<div class="sidebar2">
				<button class="sidebtn3 btn-outline-info" id="cat3">
					STEP 3<br> 소분류 코드 받아오기
				</button>
			</div>
						
			<div class="sidebar2">
				<button class="sidebtn3 btn-outline-info" id="contents">
					STEP 4<br> 컨텐츠 받아오기
				</button>
			</div>
						
			<div class="sidebar2">
				<button class="sidebtn3 btn-outline-info" id="cat5">
					STEP 5<br> ㅁ받아오기
				</button>
			</div>
			
		</div>
	</div>
</body>
<script>

	$(document).ready(function() {
	    // 문서가 준비되면 실행될 코드

	    // #cat1 버튼 클릭 이벤트 처리
	    /*$("#cat1").click(function() {
	        // 클릭 시 tour() 메소드 호출
	        cat1();
	    });*/
	    
	    $("#contents").click(function() {
	        // 클릭 시 tour() 메소드 호출
	        tour();
	    });//------------------------------------------

	    // tour() 메소드 정의
	    function tour() {
	        // 여기에 tour() 메소드 내용을 추가
	        var inputValue = '';

	        // AJAX 요청 보내기
	        $.ajax({
	        	
	            type: 'get',
	            dataType: 'json',
	            url: 'tour?addr=' + inputValue,
	            cache: false,
	            processData: true,
	            success: function(res) {
	                // 성공 시 실행되는 코드
	                console.log(res);
	            },
	            error: function(err) {
	                // 에러 시 실행되는 코드
	                console.error('error: ' + err.status);
	            }
	        });
	    }
	});//-------------------------------------------------

</script>
</html>