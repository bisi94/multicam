<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>

<!--CDN ����  -->
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
		clear:both; /*float�Ӽ� ����*/
	}
	

</style>

</head>
<body>
	<div class="container padding py-4">

		<button class="btn btn-outline-primary" data-toggle="modal"
			data-target="#reviewModal">��ǰ�� ����</button>
		<a data-toggle="modal" href="#reviewModal">��ǰ ����</a>
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
						
						<!-- �۾��� �� ---------------------- -->
							<form name="rf" id="rf" method="post" enctype="multipart/form-data">
							<!-- hidden data -->
							<input type="hidden" name="userid" id="userid" value="min"> 
							<input type="hidden" name="pnum" id="pnum" value="1">
							<input type="hidden" name="num" id="num">
								<table class="table table-responsive">
									<tr>
										<th colspan="4" class="text-center">
											<h3>::��ǰ �ı�::</h3>
										</th>
									</tr>
									<tr>
										<th>������</th>
										<td><input type="radio" name="score" id="score1" value="1">1�� 
											<input type="radio" name="score" id="score2" value="2">2�� 
											<input type="radio" name="score" id="score3" value="3">3��
											<input type="radio" name="score" id="score4" value="4">4��
											<input type="radio" name="score" id="score5" value="5">5��
										</td>
										<th>�ۼ���</th>
										<td>�ιֹ�[min]</td>
									</tr>
									<tr>
										<th>����</th>
										<td colspan="3"><input type="text" name="title"
											id="title" placeholder="Title" class="form-control">
										</td>
									</tr>
									<tr>
										<th>��ǰ��</th>
										<td colspan="3"><textarea name="content" id="content"
												rows="2" class="form-control"></textarea></td>
									</tr>
									<tr>
										<th>�̹������ε�</th>
										<!-- input name�� mfilename���� ==> ��Ʈ�ѷ����� @RequestParam("mfilename") MultipartFile mf���� �޾ƾ� �� -->
										<td colspan="2"><input type="file" name="mfilename"
											id="filename" accept="image/*" class="form-control">
										</td>
										<td><a type="button" class="btn btn-success" id="btn1"
											onclick="send()">�۾���</a></td>
									</tr>
								</table>
								<div id="prodImg">
									<!-- ���ε��� ��ǰ �̹��� -->
								</div>

						</form>
						</div> <!-- col end -->
						<div class="col-md-6 col-sm-12 col-xs-12">
						<!-- ���� ��� ---------------------- -->
							<h3>���� ���</h3>
							<ul id="reviewList">
								<!-- <li>
									<img src="resources/images/person.PNG" class="img img-thumbnail">
								</li>
								<li>
									<span class="text-danger">�ڡڡڡڡ�</span><br>
									<span>[title]</span><br>
									��ǰ���� ���� ��ġ��
								</li>
								<li></li>
								li row end(�� �࿡ li 3���� �� ����) -->
								
								
								
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
			//��ü ���� ��� ��������
			getReviewAll();
		})
	
	
		const send=function(){
			let isCheck=$('input[name^="score"]').is(":checked");
			//alert(isCheck)
			if(!isCheck){
				alert('�������� üũ�ϼ���');
				$('#score5').focus();
				return;
			}
			
			if(!$('#title').val()){
				alert('������ �Է��ϼ���');
				$('#title').focus();
				return;
			}
			if(!$('#content').val()){
				alert('��ǰ���� �Է��ϼ���');
				$('#content').focus();
				return;	
			}
			//$('#rf').prop('action', 'reviews')
			//$('#rf').submit(); //==> ajax �� �� ��û�� �����ؾ� ��
			
			//[1]���� ���ε带 ���� ���� ���
			//let paramData=$('#rf').serialize(); //input data�� �Ķ���� ���ڿ��� ����ȭ�ؼ� ��ȯ��
			//alert(paramData);//userid=aaa&pnum=1&title=bbb...
			//writeReview(paramData);
			
			let btnTxt=$('#btn1').text();
			//alert(btnTxt);
			
			//[2]���� ���ε带 �ϴ� ��� => FormData�� �̿��Ѵ�
			let form=$('#rf')[0]; //form��ü
			//alert(form);
			let formData=new FormData(form);//���� ���ε��� ���� FormData�� �����Ѵ�.
			//formData.append("name","ȫ�浿")
			//input type="file"�� name�� ReviewVO�� filename���� �ٸ��� �־�� �Ѵ�. MultipartFileŸ������ �޾ƾ� �ϱ� ����
			if(btnTxt=='�۾���'){
				writeReviewAndFile(formData);
			}else if(btnTxt=='�ۼ���'){
				//updateReviewAndFile(formData);
				updateReview();
			}			
		}//send()-----------------
		//������ ���Ͼ��ε� �����ʴ� ���
		function updateReview(){
			//������ �����͸� json��ü�� �����
			let jsonData={
				userid:$('#userid').val(),//�α��� ó���� �鿣�忡�� ���ǿ��� ���� ���
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
				data:JSON.stringify(jsonData),//json�����͸� ���ڿ��� ����ȭ�ؼ� ������
				dataType:'json',
				contentType:'application/json; charset=UTF-8',
				cache:false
			})
			.done((res)=>{
				//alert(JSON.stringify(res));
				if(res.result=='success'){
					getReviewAll();//��ü��� ��������
					$('#btn1').text('�۾���');
					$('#title').val('');
					$('#content').val('');
				}else{
					alert('���� ����');
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
				data:formData,//FormData�� �����Ѵ�
				dataType:'json',
				processData:false, //�Ϲ������� ������ �����ϴ� �����ʹ� query String �����̴� false�� �ָ� ���Ӱ� ������� �ʴ´ٴ� �ǹ�
				contentType:false, //false�� �ָ� "multipart/form=data"
				success:function(res){
					if(res.result=='success'){
						getReviewAll();
					}else{
						alert('���� ��� ����')
					}
				},
				error:function(err){
					alert('error: '+err.status);
				}
			})
		}//---------------------------------
		
		//���� ���ε� �����ʴ� �Ϲ� ���ε�
		function writeReview(paramData){
			$.ajax({
				type:'post',
				url:'reviews',
				data:paramData, //�Ķ���� ������
				dataType:'json', //��������
				cache:false,
				success:function(res){
					//alert(JSON.stringify(res));
					if(res.result=='success'){
						getReviewAll();
					}else{
						alert('���� ��� ����')
					}
					
				},
				error:function(err){
					alert('error: '+err.status);
				}
			})
		}
		
		//������ ��������	GET	/reviews	
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
					str+=`��`
				}//for-----------
				str+=`</span>[\${dStr}]<br>
				<span>\${obj.title}</span><br>
				<span>\${obj.userid}</span><br>
				\${obj.content}<br>`
				//�۾��̿� �α����� ����� ��ġ�Ѵٸ� ����/���� ��ũ�ɱ�
				if(obj.userid=='min'){
					str+=`[<a href="javascript:edit('\${obj.num}')">����</a>]`;
					str+=`[<a href="javascript:del('\${obj.num}')">����</a>]`;
				}
				str+=`</li>`;
				str+=`<li></li>`;
			}//for------------------
			$('#reviewList').html(str);
		}//------------------------
		//DELETE	/reviews/num =>����ó��
		//Get		/reviews/num =>��ȸó��
		//����� ���� �Լ�
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
					getReviewAll();//��ü��� ��������
				}else{
					alert("��������");
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
				$('#num').val(res.num);//�۹�ȣ(pk)����
				if(res.filename!=null){
					$('#prodImg').html("<img src='resources/review_images/"+res.filename+"'>");
				}
				$('#btn1').text('�ۼ���')
			})
			.fail(function(err){
				alert("error: "+err.status);
			})
		}//edit--------------------------
		
	</script>
	
</body>
</html>