<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="common.db.DBUtil, java.sql.*" %>
<%
    	Connection con=DBUtil.getCon();
    	out.println("<h1>DB연결됨: "+con+"</h1>");
    	
    	con.close();
%>
