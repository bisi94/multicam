<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h1>ex05_includeAction 페이지 입니다</h1>
<p>
	includeAction 태그를 통해 다른 페이지를 여기에 포함시킬 수 있어요
	<br>
	includeAction은 실행결과를 포함시키는 방식이다. jsp의 흐름을 ex04_gugudan.jsp로 이동시켜 그 실행 결과문을 현재 위치에 포함시킨다
</p>


<hr color='red'>

<jsp:include page="ex04_gugudan.jsp" />
<hr color='red'>

<!-- ex02.jsp파일을 include action으로 포함시키기 -->
<jsp:include page="ex02.jsp" />