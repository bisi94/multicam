package my.com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
//apache-tomcat-9.0.80/lib/servlet-api.jar==>HttpServlet

/* [1]  HttpServlet을 상속받아 구현 : http프로토콜에 특화된 서블릿
 * [2]  doGet() 또는 doPost() 메서드를 오버라이드 한다
 * [3] 2번에서 재정의한 메서드 안에서 코드 구현
 * 
 * 서블릿은 구현하고 나면 서블릿을  web.xml 에 등록하던지, 아니면 어노테이션(@WebServlet)을 기술해야 한다.
 * (1) MyWeb/WEB-INF/web.xml
 * 
 * (2) @WebServlet어노테이션 사용하는 방법
 * */

/**
 * Servlet implementation class HelloServlet
 */
@WebServlet("/aaa")//url-pattern: unique한 값을 설정해야 한다. 안그러면 서버가 startup되지 못한다
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//req: 사용자 요청과 관련된 정보를 추출하거나 다루는 객체
		//res: 브라우저와 연결되어 브라우저에 동적으로 html을 생성해서 쓸 수 있는 객체
		
		res.setContentType("text/html; charset=UTF-8");
		//웹브라우저에 보여줄 문서 형식을 html문서를 출력하겠다고 설정
		
		
		PrintWriter out=res.getWriter();//브라우저에 html을 출력하기 위해 출력 스트림을 얻어온다
		out.println("<h1>Hello World</h1>");
		out.println("<h2>안녕 서블릿?</h2>");
		out.println("<hr color='red'>");
		Date today=new Date();
		out.println("<h3>Server Time: "+today.toString()+"</h3>");
		out.close();
		
		
	}

}
