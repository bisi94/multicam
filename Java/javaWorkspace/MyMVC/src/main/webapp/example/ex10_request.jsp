<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*"
    pageEncoding="UTF-8"%>

    <h1>ex10_request.jsp</h1>
    <h1>request내장객체: HttpServletRequest 타입</h1>
    
    http://localhost:9090/MyMVC/example/ex10_request.jsp
    ?uid=1&gender=F&hobby=reading&lang=HTML5
   
    <hr color='red'>
   <%
   		String uid=request.getParameter("uid");
   		if(uid==null){
   			response.sendRedirect("ex10_form.jsp");
   			return;
   		}
   
   		String server=request.getServerName(); //도메인명
   		int port=request.getServerPort(); //포트번호
   		
   		String url=request.getRequestURL().toString(); //요청URL
   		String uri=request.getRequestURI(); //요청URI
   		
   		String queryStr=request.getQueryString();//쿼리스트링
   		
   		String myctx=request.getContextPath();//컨텍스트명을 반환한다
   		
   		String protocol=request.getProtocol(); //프로토콜
   		String cip=request.getRemoteAddr(); //클라이언트의 ip주소
   		
   		// [문제1] 요청 URI 중에서
   		// /multi/example/ex10_request.jsp===>"/example/ex10_request.jsp" 문자열 정보만 추출해서
   		// 브라우저에 출력하세요
   		String str=uri.substring(myctx.length());
    		
   		//[문제2] "/example/ex10_request" 문자열 정보를 추출하세요
   		//String클래스의 String substring(int start, int end): 문자열 인덱스 start에서 end-1 까지의 문자열을 추출한다
   		//			   String substring(int start)
   		//				int indexOf("검색문자열"): 검색문자열을 찾아 해당 문자열의 인덱스 번호를 반환한다.
   		//									찾는 문자열이 없다면 -1을 반환한다
   		//				int lastIndexOf("검색문자열") : 뒤에서부터 찾는다
   		int idx=str.lastIndexOf(".jsp");
   		System.out.println("idx: "+idx);
   		String str2=str.substring(0,idx);
   		
   		String path=request.getServletPath();//컨텍스트명 이후의 경로를 반환
   		     		
   %>
   
   <h3>서버 도메인명: <%=server %></h3>
   <h3>서버 도메인명: <%=port %></h3>
   <h3>요청 URL: <%=url %></h3>
   <h3>요청 URI: <%=uri %></h3>
   <h3>요청 쿼리 스트링: <%=queryStr %></h3>
   
   <h3>컨텍스트명: <%=myctx %></h3>
   <h3>프로토콜: <%=protocol %></h3>
   <h3>클라이언트 ip주소: <%=cip %></h3>
   
   <h3>str: <%=str %></h3>
   <h3>str2: <%=str2 %></h3>
   <h3>Servlet Path: <%=path %></h3>
   
   <hr color='blue'>
   <h2>request의 헤더 정보</h2>
   <%
   		//헤더의 key값들만 반환
   		Enumeration<String> en=request.getHeaderNames();
   		
   		while(en.hasMoreElements()){
   			String key=en.nextElement(); //헤더의 키값을 반환
   			String val=request.getHeader(key); //키에 해당하는 value값을 반환한다
   			out.println("<h3>"+key+": "+val+" </h3>");
   		}
   
   %>
    
   <h3><a href="ex10_form.jsp">돌아가기</a></h3>
   
   
   
   
   
   
   
   
   