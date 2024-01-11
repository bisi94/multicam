package user.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.model.MemberDAO;
import user.model.MemberVO;

/**
 * Servlet implementation class SignupServlet
 */
@WebServlet("/Signup")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out=res.getWriter();
		
		//1. Post방식일 때 한글처리
		req.setCharacterEncoding("utf-8");
		
		//2. 사용자가 입력한 값 받기 (이름,아이디,비밀번호,연락처)
		String name=req.getParameter("name");
		String uid=req.getParameter("userid");
		String upw=req.getParameter("userpw");
		String tel=req.getParameter("tel");
		
//		out.println(name+"/"+uid+"/"+upw+"/"+tel+"<br>");
		//3. 서버단에서 유효성 체크
		if(name==null||uid==null||upw==null||name.trim().equals("")||uid.trim().equals("")||upw.trim().equals("")) {
			//빈 문자열이 온다면 join.html로 돌려보내자
			
			res.sendRedirect("member/join.html"); 
			//페이지 이동시키는 메소드=> 브라우저의 url을 ""안의 값으로 바꾸어 서버에 새롭게 요청을 보냄
			return;
		}
		//4. 2번에서 받은 값을 MemberVO객체에 담아준다
		MemberVO user=new MemberVO(0,name,uid,upw,tel);
		
		//5. MemberDAO객체 생성해서 insert()호출=>예외처리
		MemberDAO dao=new MemberDAO();
		
		try {
			int n=dao.insert(user);
			String msg=(n>0)?"회원가입 완료":"회원가입 실패 - 다시 시도하세요";
//			out.println(msg);
			
			String loc=(n>0)?"MemberList":"member/join.html";//이동할 페이지
			//MemberList=> 서블릿
			
			out.println("<script>");
			out.println("alert('"+msg+"')");
			out.println("location.href='"+loc+"'");
			out.println("</script>");
			
		}catch(SQLException e) {
			e.printStackTrace();//톰캣 콘솔에 출력됨
			//throw new ServletException(e); //브라우저에도 예외 스택기록 출력
			out.println("<script>");
			out.println("alert('사용중인 아이디입니다 다시 입력하세요')");
			out.println("history.back();");
			out.println("</script>");
		}
		
		//6. 결과 메시지 처리후 페이지 이동
		
		
		out.close();
	}

}
