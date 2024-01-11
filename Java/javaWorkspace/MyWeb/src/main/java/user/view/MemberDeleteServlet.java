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

@WebServlet("/MemberDelete")
public class MemberDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out=res.getWriter();
		
		//[1] 회원번호 파라미터값 받기
		String noStr=req.getParameter("no");
		out.println(noStr);
		
		//[2] 유효성 체크=> MemberList로 sendRedirect()하기
		if(noStr==null||noStr.trim().equals("")) {
			res.sendRedirect("MemberList");
			return;
		}
		
		//[3] MemberDAO생성...delete(회원번호-int)
		MemberDAO dao=new MemberDAO();
		try {
		int n=dao.delete(Integer.parseInt(noStr));
		
		//[4] 그 실행결과 메시지 alert()로 보여주고 페이지 이동(MemberList)
		String msg=(n>0)?"회원정보 삭제 완료":"삭제 실패";
		String loc="MemberList";
		
		out.println("<script>");
		out.println("alert('"+msg+"');");
		out.println("location.href='"+loc+"';");
		out.println("</script>");
		
		}catch(SQLException e) {
			e.printStackTrace();
		}
		out.close();
	}

}

















