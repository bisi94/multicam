<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Melon Chart</title>
<style>
	#wrap{
		width:90%;
		margin:2em;
		padding:2em;
		/*border:1px solid red;*/
	}
	#menu{
		width:100%;
		text-align:center;
		margin-bottom:1em;
	}
	#menu button{
		padding:0.8em;
	}
	#melonList{
		width:49%;
		/* border:1px solid blue; */
		float:left;
	}
	#melonSingerCnt{
		width:49%;
		/* border:1px solid orange; */
		float:right;
	}
	#melonList ul.melon_rank>li{
		list-style:none;
		float:left;
		height:120px;
		margin-bottom:3px;
	}
	#melonList ul.melon_rank>li:nth-child(3n+1){
		width:10%;
		font-weight:bold;
		font-size:1.5em;
		color:gray;
	}
	#melonList ul.melon_rank>li:nth-child(3n+2){
		width:25%;
		
	}
	#melonList ul.melon_rank>li:nth-child(3n){
		width:65%;
		
	}
	
	#singerList>ul>li{
		font-size:1.0em;
		list-style:none;
		height:30px;
		line-height:30px;
		margin-bottom:3px;
	}
	
	span.title{
		font-size:1.0em;
		font-weight:bold;
	}
	span.singer{
		font-size:0.9em;
		font-weight:bold;
		color:gray;
	}
	
</style>

<!-- jquery cdn------------------------------------------------ -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

<!-- google chart cdn------------------------------------------ -->
<!--Load the AJAX API-->
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

<script>
	$(function(){
		//구글 차트 로드
		google.charts.load('current', {'packages':['corechart','bar']});
		getMelonList();
		//getMelomChart();
		
		//ajax로 크롤링 요청을 보내기
		$('#btn1').on('click', function(){
			$.ajax({
				type:'get',
				url:'crawling',
				dataType:'json',
				cache:false
			})
			.done(function(res){
				alert(JSON.stringify(res));
			})
			.fail(function(err){
				alert('error: '+err.status);
			})
			
		})//#btn1------------------------------------
		
		$('#btn2').on('click', function(){
			getMelonList();
		})//#btn2------------------------------------
		
		$('#btn3').on('click', function(){
			getMelonChart();
		})//#btn3------------------------------------
		
	})//$() end------------------------------------------
	
	function getMelonChart(){
		$.ajax({
			type:'get',
			url:'singerSongCnt',
			dataType:'json',
			cache:false
		})
		.done(function(res){
			alert(JSON.stringify(res));
			showCntBySinger(res);
		})
		.fail(function(err){
			alert('error: '+err.status);
		})
			
	}//MelonChart()--------------------
	
	function getMelonList(){
		$.ajax({
			type:'get',
			url:'melonList',
			dataType:'json',
			cache:false
		})
		.done(function(res){
			//alert(JSON.stringify(res));
			showList(res);
		})//--------------------
		.fail(function(err){
			alert('error: '+err.status);
		})
	}//getMelonList()--------------------------------
	
	function showCntBySinger(jsonArr){
	 	// Load the Visualization API and the corechart package.
		var data = new google.visualization.DataTable();
		data.addColumn("string", "Singer");//컬럼 정보 (자료형, 컬럼명)
		data.addColumn("number", "Song Count");
		let mydata=[]; //
		
		let str='<ul>'
		$.each(jsonArr, function(i, obj){
			let rowData=[obj.singer, obj.cntBySinger];//행 데이터
			mydata.push(rowData);
			console.log('rowData: '+rowData);
			str+=`<li>
					\${obj.singer} : \${obj.cntBySinger}
				  </li>
			`
		});
		console.log("mydata: ", mydata);
		data.addRows(mydata); //그래프로 표현할 실제 데이터를 추가
		renderChart(data); //그래프로 그려주는 함수 표출
		
		str+='</ul>';
		$('#singerList').html(str);
		
	}//---------------------
	
	function renderChart(data){
		 // Set chart options
        var options = {'title':'멜론 차트에 오른 가수별 노래 수',
                       'width':'100%',
                       'height':300,
                       'fontSize':11,
                       'fontName':'Verdana'
		 };

        // Instantiate and draw our chart, passing in some options.
        var chart = new google.visualization.PieChart(document.getElementById('myPieChart'));
        chart.draw(data, options);
        
        var chart2 = new google.visualization.BarChart(document.getElementById('myBarChart'));
        chart2.draw(data, options);
	}//------------------------
	
	//$.each(배열, 함수) : 배열 크기만큼 반복돌면서 배열요소와 인덱스를 핸들러 함수에 전달한다
	function showList(jsonArr){
		let len=jsonArr.length;
		if(len==0){
			$('#melonList').html("<h3>멜론차트를 보려면 크롤링을 먼저 하세요</h3>")
		}else{
			let str="<ul class='melon_rank'>";
			//for(let i=0; i<len; i++){
			//	let obj=jsonArr[i]; str+=obj.songTitle;
			//}	
			$.each(jsonArr, function(i, obj){ //i:인덱스번호, obj:배열에 저장된 객체
				str+=`<li>\${obj.ranking}</li>
				<li><img src="\${obj.albumImage}"></li>
				<li>
					<span class="title">\${obj.songTitle}</span>
					<span class="singer">\${obj.singer}</span>
				</li>
				`;
			})
				str+="</ul>";
			$('#melonList').html(str);
		}
		//목록을 보여준 후 가수별 노래 수 가져와 차트 그리기
		getMelonChart();
	}//showList()--------------------------------------

</script>

</head>
<body>
<div id="wrap" class="container">
	<div id="menu">
		<h1> 오늘의 Melon Chart - ${today}</h1>
		<button id="btn1">멜론 차트 크롤링하기</button>
		<button id="btn2">멜론 차트 목록 보기</button>
		<button id="btn3">멜론 차트 가수별 노래</button>
	</div>
	<div id="melonList">
	<!-- 멜론차트들어옴 -->
	</div>
	<div id="melonSingerCnt">
	<!-- 가수별노래들어옴 -->
	<div id="singerList">
	
	</div>
	<div id="myBarChart">
	</div>
	
	<div id="myPieChart">
	</div>
	
	</div>
</div>
</body>
</html>