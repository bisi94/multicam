package user.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SignupFormServlet
 */
@WebServlet("/signup.do")
public class SignupFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//View 페이지로 이동
		//signup.jsp로 forward 이동한다
		//<jsp:forward page="/member/signup.jsp">
		
		RequestDispatcher disp = req.getRequestDispatcher("/member/signup.jsp");
		disp.forward(req,res);//서버 내부에서 이동. req, res를 가져간다
	
	}

}
