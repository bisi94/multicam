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

@WebServlet("/user/memberUpdateEnd.do")
public class MemberUpdateProcServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. post 방식 한글 처리
		/* request.setCharacterEncoding("utf-8"); */
		
		// 2. 사용자가 입력한 값 받기
		String name=request.getParameter("name");
		String userid=request.getParameter("userid");
		String userpwd=request.getParameter("userpwd");
		String hp1=request.getParameter("hp1");
		String hp2=request.getParameter("hp2");
		String hp3=request.getParameter("hp3");
		String post=request.getParameter("post");
		String addr1=request.getParameter("addr1");
		String addr2=request.getParameter("addr2");
		
		// 2_2. 로그인한 아이디 받아오기 => 세션에서
		HttpSession session=request.getSession();
		
		String loginId=(String)session.getAttribute("loginId");
		
		// 3. 유효성 체크 
 		if(name==null||userid==null||userpwd==null||hp1==null||hp2==null||hp3==null){
			response.sendRedirect("memberUpdate.do");
 			return;
 		}
 		
 		// 4. MemberVO에 2번에서 받은 값 담아주기
		MemberVO user=new MemberVO(name, userid, userpwd, hp1, hp2, hp3, post, addr1, addr2);
	
		// 5. MemberDAO의 update()호출
		MemberDAO dao=new MemberDAO();
		int n=0;
		try {
			n=dao.update(loginId,user);
		}catch(SQLException e) {
			throw new ServletException(e);
		}
		
		// 6. 그 결과 메시지, 이동경로 지정
		String msg=(n>0)?"수정완료":"수정실패";
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", "memberUpdate.do");
		
		RequestDispatcher disp=request.getRequestDispatcher("/message.jsp");
		disp.forward(request, response);
	}

}
