<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<h1>Cookie 목록</h1>
<%
/*쿠키가 전송되면 웹브라우저는 한번 저장된 쿠키를 매번 요청이 필요할 때마다
  서버에 request를 통해 전송한다. 그러면 서버는 브라우저가 전송한 쿠키를
  꺼내서 필요한 작업을 수행한다.
*/

Cookie[] cks=request.getCookies();
if(cks!=null){
	out.println("<h2>클라이언트로부터 넘어온 쿠키들</h2>");
	for(Cookie ck:cks){
		//key값 가져오기 getName()
		String key=ck.getName();
		
		//val값 가져오기 getValue()
		String val=ck.getValue();
		
		out.println("<h3> "+key+": "+val+"</h3>");
	}
}
%>