<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="board.model.*"  %>

    <h1>testMyBatis</h1>
    
<%
	BoardDAOMyBatis dao=new BoardDAOMyBatis();
	int cnt=dao.testMyBatis();

%>

  <h2 style='color:red'>multishop 계정의 테이블 수: <%=cnt %></h2>