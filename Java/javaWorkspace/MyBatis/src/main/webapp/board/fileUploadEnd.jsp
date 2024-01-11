<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="container">

	<h1>파일 업로드 결과</h1>
	<h2>전송되는 데이터 형태를 브라우저에 그대로 출력해보자</h2>
<%
	//컨텐트 타입, 파일크기
	String ctype=request.getContentType();
	long len=request.getContentLengthLong();//첨부파일 크기
%>
<hr color='blue'>
<h3>Content Type: <%=ctype %></h3>
<h3>Content Length: <%=len %></h3>


<hr color='blue'>
<%
	//request body에 포함되어 오는 데이터를 스트림 연결해서 읽은 뒤 브라우저에 출력하자
	ServletInputStream in=request.getInputStream();
	
	byte[] data=new byte[1024];
	int n=0;
	out.println("<xmp>");
	while((n=in.read(data))!=-1){
		String str=new String(data,0,n);
		out.println(str);
	}
	out.println("</xmp>");
	
	in.close();
%>

</div>