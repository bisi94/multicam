<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <h3> for루프 이용해서 Hello JSP 5번 출력</h3>
    
    <% 
    	for(int i=0;i<5;i++){
   	%>
	<h3>Hello JSP<%=i%></h3>
    <%
    	}
    %>
    
    <hr>
    
    <h3> 구구단 8단을 출력해보세여</h3>
    
    <% 
   		 for(int i=1;i<10;i++){
   	%>
	<h4>8 x <%=i%> = <%=8*i%></h4>
   	<%
    }
    %>