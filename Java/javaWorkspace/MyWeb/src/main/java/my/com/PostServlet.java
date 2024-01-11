package my.com;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/PostServlet")
public class PostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out=res.getWriter();
		//사용자가 입력한 값 받기
		//HttpServletRequest의 주요 메서드
		//String getParameter("파라미터명")
		//String[] getParameterValues("파라미터명"): 동일한 파라미터명으로 여러 개의 값을 전송할 때 (checkbox, select multiple)
		//Post방식일 때 한글처리
		//post방식은 파라미터 데이터가 req의 entity body에 포함되어 온다
		req.setCharacterEncoding("utf-8"); //post방식의 한글처리
		
		String uid=req.getParameter("userid");
		String upw=req.getParameter("userpw");
		
		out.println("<h1>아이디: "+uid+"</h1>");
		out.println("<h1>비밀번호: "+upw+"</h1>");
		out.println("<br><br>");
		out.println("<a href='index.html'><h3>index로 돌아가기</h3></a>");
		
		
		
		out.close();
	}

}
