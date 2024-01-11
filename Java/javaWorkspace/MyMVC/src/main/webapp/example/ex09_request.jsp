<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h1>ex09_request.jsp</h1>

<%	
	//request객체: HttpServletRequest타입
	//[1] 단일값을 받을 경우: String getParameter("파라미터명")
	String uid=request.getParameter("uid");
	String gen=request.getParameter("gender");

	//[2] 다중값을 받을 경우: String[] getParameterValues("파라미터명")
 	String[] hobby = request.getParameterValues("hobby");
	String[] langs = request.getParameterValues("lang"); 
	
%>
<ul>
	<li>아이디 : <%=uid %></li>
	<li>성 별 : <%=gen %></li>
	<li>취 미 : 
	<%
		if(hobby!=null){
			for(String hb:hobby){
				out.println(hb+", ");
			}
		}
	
	%>
	</li>
	
	<li>언 어 : 
	<%
		for(int i=0; langs!=null&&i<langs.length; i++){
			String lang=langs[i];
			out.println(lang+", ");
		}
	
	%></li>

</ul>

<a href="ex09_form.jsp"><h2>돌아가기</h2></a>
