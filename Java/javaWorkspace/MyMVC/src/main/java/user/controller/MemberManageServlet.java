package user.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.model.MemberDAO;

/**
 * Servlet implementation class MemberMabageServlet
 */
@WebServlet("/admin/userMgr.do")
public class MemberManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		//1. 파라미터값 받기 (userid,mstate) 
		String userid=request.getParameter("userid");
		String mstate=request.getParameter("mstate");
		
		//2. 유효성 체크 -> ../memberList.do 로 리다이렉트 이동
		if(userid==null||mstate==null) {
			response.sendRedirect("../memberList.do");
			return;
		}
		
		//3. MemberDAO의 manageMstate(userid, mstate)호출하기==>update문 수행
		MemberDAO dao=new MemberDAO();
		System.out.println(mstate);
		try {
			int n=dao.manageMstate(userid, Integer.parseInt(mstate));
			
			//4. 실행 결과 메시지,이동경로 처리
			String msg=(n>0)?"회원상태정보 수정완료":"수정 실패";
		
			request.setAttribute("msg", msg);
			request.setAttribute("loc", "memberList.do");
			
			RequestDispatcher disp=request.getRequestDispatcher("/message.jsp");
			disp.forward(request, response);
		}catch(SQLException e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
		
	}

}
