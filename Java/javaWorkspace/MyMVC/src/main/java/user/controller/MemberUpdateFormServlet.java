package user.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.model.MemberDAO;
import user.model.MemberVO;

@WebServlet("/user/memberUpdate.do")
public class MemberUpdateFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 로그인한 회원의 아이디 알아내기 -> 세션에서 꺼내오기
		HttpSession session=request.getSession();
		
		String loginId=(String)session.getAttribute("loginId");
		
		// 2. MemberDAO의 selectByUserid(loginId) 호출===>MemberVO반환
		MemberDAO dao=new MemberDAO();
		try {
			MemberVO vo = dao.selectByUserid(loginId);	
			// 3. request에 MemberVO객체 저장<= "user" 키값으로
			request.setAttribute("loginId", loginId);
			request.setAttribute("user", vo);
		
		RequestDispatcher disp=request.getRequestDispatcher("/member/memberUpdate.jsp");
		disp.forward(request, response);
		
		}catch(SQLException e) {
			throw new ServletException(e);
		}
	}

}
