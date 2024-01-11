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
 * Servlet implementation class IdCheckProcServlet
 */
@WebServlet("/idCheckEnd.do")
public class IdCheckProcServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. userid값 받기
		String userid=request.getParameter("userid");
		
		// 2. null체크 =idCheck.do로 redirect이동
		if(userid==null) {
			response.sendRedirect("idCheck.do");
			return;
		}
		
		//3. MemberDAO생성, idCheck()호출
		MemberDAO dao=new MemberDAO();
		try {
			boolean flag=dao.idCheck(userid);
			String msg="", result="";
			if(flag) {//아이디 사용가능
				msg="["+userid+"]를 사용할 수 있습니다.";
				result="success";
			}else {//아이디 이미 사용중
				msg="["+userid+"]는 이미 사용중인 아이디입니다.";
				result="fail";
			}
			
			request.setAttribute("msg", msg);
			request.setAttribute("result", result);
			request.setAttribute("userid", userid);
			
			RequestDispatcher disp=request.getRequestDispatcher("/member/idCheckResult.jsp");
			disp.forward(request, response);
			
		}catch(SQLException e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
				
		//4. 그 결과 받아서
		// request에 msg,userid 저장		
		// true  ==> "/member/idCheckResult.jsp"
		// false ==> "/member/idCheckResult.jsp"
		// 로 forward이동
			
			
		
		
	}

}
