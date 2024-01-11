<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h1>ex08_response.jsp 페이지 입니다</h1>
<%
	//1.아이디와 비밀번호 받아서 브라우저에 출력
	String uid=request.getParameter("uid");
	String pwd=request.getParameter("pwd");
	
	out.println(uid+"/"+pwd+"<br>");
	
	//2. null값이면 ex08_form.jsp로 redirect 이동
	if(uid==null||pwd==null){
		response.sendRedirect("ex08_form.jsp");
		//Redirect는 url을 ""안의 주소로 바꿈
		
		return;
	}
	
	System.out.println("여기에 들어올까여???");
	
	if("".equals(uid.trim())){
		//빈 문자열일 경우
		//response.sendError(400, "잘못된 요청입니다");
		response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		
		return;
	}
	
	//3. killer가 들어오면 접근 금지 에러를 출력하세요 -403
	if(uid.contains("killer")){
		response.sendError(403, "접근금지");
	}
	
	//4. uid가 redirect라면 "welcome.jsp"로 redirect방식으로 이동시키세요
	if(uid.equals("redirect")){
		response.sendRedirect("welcome.jsp");
	}
	
	//5. uid가 forward라면 "welcome.jsp"로 forward방식으로 이동시키세요
	if(uid.equals("forward")){
		%><jsp:forward page="welcome.jsp" /><%
	}
	
%>
<h2 style='color:green'><%=uid %>님 환영합니다</h2>
