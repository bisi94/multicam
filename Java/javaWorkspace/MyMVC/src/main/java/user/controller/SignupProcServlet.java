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
import user.model.MemberVO;

/**
 * Controller
 */
@WebServlet("/signupEnd.do")
public class SignupProcServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. post방식일때 한글 처리
		/* request.setCharacterEncoding("utf-8"); */
		//2. 사용자가 입력한 값 받기(name,userid,userpwd,hp1,hp2,hp3,post,addr1,addr2)
			String name=request.getParameter("name");
			String userid=request.getParameter("userid");
			String userpwd=request.getParameter("userpwd");
			String hp1=request.getParameter("hp1");
			String hp2=request.getParameter("hp2");
			String hp3=request.getParameter("hp3");
			String post=request.getParameter("post");
			String addr1=request.getParameter("addr1");
			String addr2=request.getParameter("addr2");
			
		//3. 유효성 체크 (name,userid,userpwd,hp1,hp2,hp3)
	 		if(name==null||userid==null||userpwd==null||hp1==null||hp2==null||hp3==null){
				response.sendRedirect("signup.do");
	 			return;
	 		}
	 		
 		//4. 2번 값을 MemberVO객체에 전달
			MemberVO user=new MemberVO(name, userid, userpwd, hp1, hp2, hp3, post, addr1, addr2);
		
		//5. MemberDAO생성해서 insert()호출
			MemberDAO dao=new MemberDAO();
			int n=0;
			try {
				n=dao.insert(user);
			}catch(SQLException e) {
				System.out.println(e);
			}
			
		//6. 그 결과 메시지,이동경로 처리
			String msg=(n>0)?"회원가입 완료":"회원가입 실패";
			String loc=(n>0)?"login.do":"signup.do";
			
		//7. 6번의 데이터를 request에 저장한다
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			
		//8. /message.jsp로 forward이동한다
			RequestDispatcher disp = request.getRequestDispatcher("/message.jsp"); //view page
			disp.forward(request, response);
		
	}

}
