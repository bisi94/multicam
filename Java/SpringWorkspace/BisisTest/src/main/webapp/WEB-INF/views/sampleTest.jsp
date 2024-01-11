<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>SampleModel Test JSP</title>
</head>
<body>

<%@ page import="com.example.model.SampleModel" %>
<%@ page import="java.util.ArrayList" %>

<%
    // SampleModel 객체 생성
    SampleModel sampleModel = new SampleModel();
    sampleModel.setId(1);
    sampleModel.setName("Sample Name");

    // SampleModel 객체를 ArrayList에 추가 (테스트용)
    ArrayList<SampleModel> sampleList = new ArrayList<>();
    sampleList.add(sampleModel);
%>

<h2>SampleModel Test</h2>

<c:forEach var="sample" items="${sampleList}">
    <p>ID: ${sample.id}, Name: ${sample.name}</p>
</c:forEach>

</body>
</html>
