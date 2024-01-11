<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>

 <!-- 버튼 추가 -->
    <a href="${pageContext.request.contextPath}/sampleTest">Go to Sample Test</a>
 <!-- contextPath 출력 -->
    <h1>${pageContext.request.contextPath}</h1>
</body>
</html>
