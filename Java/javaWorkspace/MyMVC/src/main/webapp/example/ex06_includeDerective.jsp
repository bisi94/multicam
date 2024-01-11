<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <h1>ex06_includeDerective 페이지 입니다</h1>
    
    <p>
    	includeDerective를 이용해 ex04_gugudan.jsp를 아래에 포함시킵니다.
    	<br>
    	include 지시어는 다른파일(ex04_gugudan.jsp)의 소스를 현재 위치에 삽입한 후,
    	jsp파일을 서블릿으로 변환하고 컴파일하는 방식이다
    </p>
    <hr color='blue'>
    
    <%@ include file="ex04_gugudan.jsp" %>
    <hr color='blue'>
    
    <!-- ex02.jsp파일을 include action으로 포함시키기 -->
    <%@ include file="ex02.jsp"%>
    
    <%-- int local2=5000; //ex02.jsp에 local지역변수가 있으므로 사용불가
    out.println("local2: ",+local2+"<br>");
    --%>