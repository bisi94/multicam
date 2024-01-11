<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <h1>session에 저장된 정보</h1>
    
    <h2 style="color:red">JSESSIONID: <%=session.getId()%></h2>
    
    <%
    	//세션에 저장된 key값들을 반환
    	java.util.Enumeration<String> en=session.getAttributeNames();
    
    	while(en.hasMoreElements()){
    		String key=en.nextElement();
    		Object val=session.getAttribute(key);
    		%>
    		<h2><%=key %> : <%=val %></h2>
    		
    		<%
    	}
    %>