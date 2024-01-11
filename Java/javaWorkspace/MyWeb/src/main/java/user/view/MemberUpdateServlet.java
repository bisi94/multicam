package user.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.model.MemberDAO;
import user.model.MemberVO;

@WebServlet("/MemberUpdate")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out=res.getWriter();
		
//		(0) post방식일때 한글처리
		req.setCharacterEncoding("utf-8");
		
//		(1) 회원정보 받기(no,name,userid,userpw,tel)
		String noStr=req.getParameter("no");
		String name=req.getParameter("name");
		String userid=req.getParameter("userid");
		String userpw=req.getParameter("userpw");
		String tel=req.getParameter("tel");
		
//		(2) 유효성 체크=>member/edit.jsp?no=번호 redirect이동
		if(noStr==null||noStr.trim().isEmpty()) {
			res.sendRedirect("MemberList");
			return;
		}
		if(name==null||userid==null||userpw==null||name.trim().isEmpty()||
				userid.trim().isEmpty()||userpw.trim().isEmpty()) {
			res.sendRedirect("member/edit.jsp?no="+noStr);
			return;
		}
		int no=Integer.parseInt(noStr.trim());
		
//		(3) MemberVO객체생성해서 1번값 담아주기
		MemberVO user=new MemberVO(no,name,userid, userpw, tel);
		
//		(4) MemberDAO생성해서 update()호출
		MemberDAO dao=new MemberDAO();
		try {
			int n=dao.update(user);
//		(5) 그 결과 메시지 처리 및 페이지 이동
			String msg=(n>0)?"수정처리 완료":"수정처리 실패";
			out.println("<script>");
			out.println("alert('"+msg+"')");
			if(n>0) {
				out.println("window.close()");
				
				//팝업을 띄운 부모창을 새로고침하기
				out.println("opener.location.reload();");
			}else {
				out.println("history.back()");
			}
			out.println("</script>");
			
		}catch(Exception e) {
			e.printStackTrace();
			out.println("<script>");
			out.println("alert('사용중인 아이디입니다 다시 입력하세요')");
			out.println("history.back();");
			out.println("</script>");
		}
		
		out.close();
	}

}
