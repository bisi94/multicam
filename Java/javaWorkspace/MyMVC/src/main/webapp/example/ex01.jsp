<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>

<!-- page directive -->

<!-- 1. html주석 -->

<%
//2. 자바 주석
/*   자바 주석*/

%>

<%-- 3.JSP 주석 --%>

<h1>처음 해보는 JSP</h1>
<%
// 스크립트렛(scriptlet) 태그: 이 안에서는 자바코드를 기술한다
	
	Date date=new Date();

	out.println("<h2>"+date.toString()+"</h2>");
	//out: JspWriter 타입의 내장객체

%>


<h2 style='color:purple'>오늘은 <%=date.toString()%>일 입니다</h2>

<%--
	<%=변수%> : 출력식(expression) ==> out.println(변수) 와 동일함 



 --%>