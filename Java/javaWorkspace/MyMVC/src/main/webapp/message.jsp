<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- taglib 지시어: 제어문등을 html 태그처럼 사용할 수 있다. => core-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- EL 표현식으로 변수값들을 출력해보자 -->
<c:if test="${msg==null}">
	<script>
		location.href="${loc}";
	</script>
</c:if>
<c:if test="${msg!=null}">
	<script>
		alert('${requestScope.msg}');
		location.href='${requestScope.loc}';
	</script>
</c:if>
