<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="java.util.*, java.text.*" %>
<h1>Cookie Tset</h1>
<h2>쿠키에 간단한 정보를 저장해보자</h2>
<%
/*사용자의 간단한 정보들은 쿠키에 저장할 수 있다.
이 때 쿠키는 클라이언트 쪽 메모리나 파일로 저장된다.
1. 쿠키 저장 단계
	[1] 쿠키 생성 단계 (key, value)
	[2] 쿠키 속성 설정 단계 (유효시간, 도메인, 경로 등...)
	[3] 쿠키 전송 단계 => response에 쿠키를 포함시켜서 클라이언트 쪽으로 전송한다.
2. 쿠키 꺼내는 단계
	=> request에 포함되어 있는 쿠키를 꺼내 활용한다.
*/

	//[1] 쿠키 생성 단계 (key, value)
	Cookie ck1=new Cookie("visitId", "scott"); //key, value 형태로 매핑해서 저장
	
	Date d=new Date();
	SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd_hh:mm:ss");
	String time=sdf.format(d);
	
	Cookie ck2=new Cookie("visitTime", time);
			
	//쿠키에 저장할 값들은 영문만 가능, 공백()이나 세미콜론(;), 콤마(,), 하이픈(-) 등 특수문자는 포함할 수 없다
	
	//[2] 쿠키 속성 설정 단계 (유효시간, 도메인, 경로 등...)
	ck1.setMaxAge(60*60*24*3); //3일간 유효
	ck2.setMaxAge(30); //30초 유효
	ck1.setPath("/"); //setDomain()...
	ck2.setPath("/");
	
	//setMaxAge(0) : 쿠키 삭제
	
	//[3] 쿠키 전송 단계
	response.addCookie(ck1);
	response.addCookie(ck2);
%>
	<h2>쿠키 저장 완료!!</h2>
	<h2><a href="ex12_cookirList.jsp">쿠키 목록 보러 가기</a></h2>











