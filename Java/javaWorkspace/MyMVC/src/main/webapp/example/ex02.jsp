<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h1> scripting element</h1>

[1] scriptlet : &lt;% %&gt; <br>
[2] declaration(선언문) : &lt;%! %&gt; 여기에 메소드를 구성하거나 멤버변수를 선언함 <br> 
[3] expression(출력식) : &lt;%= %&gt; <br>

<%! 
	String global="인스턴스 변수(멤버변수)";

	public String sum(int x, int y){
		
		return x+"+"+y+"="+(x+y);
	}
%>

<%
	String local="지역변수";

	//스크립트 태그 안에 있는 내용들이 서블릿의 __jspService()메소드 안에 들어간다
%>
<h1>global: <%=global %> </h1>
<h1>local: <%=local %> </h1>
sum()호출해서 반환해주는 값 출력하기<br>
<h2> <%=sum(77,22) %></h2>
이클립스에서 서블릿 생성되는 곳:
C:\Users\qltl0\OneDrive\Desktop\multicamp\JavaWorkspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\work\Catalina\localhost\MyMVC\org\apache\jsp\example