package my.com;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/GetServlet")
public class GetServlet extends HttpServlet {
	
	//get방식 요청 => doGet() 오버라이드
	//post방식 요청 => doPost()
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out=res.getWriter();
		//사용자가 보낸 요청 파라미터 데이터를 받아보자
		String userid=req.getParameter("userid");
		String userpw=req.getParameter("userpw");
		
		out.println("<h1>사용자 아 이 디: "+userid+"</h1>");
		out.println("<h1>사용자 비밀번호: "+userpw+"</h1>");
		
		out.close();
	}
		
}
