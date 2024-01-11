package user.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.model.MemberDAO;
import user.model.MemberVO;


@WebServlet("/MemberList")
public class MemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out=res.getWriter();

		try {
		out.println("<link rel='stylesheet' type='text/css' href='css/member.css'>"); //css파일 참조
		out.println("<script src='js/member.js'></script>"); //외부 스크립트 파일 참조
		
		out.println("<div id='wrap'>");
		out.println("<h2 id='title'> 전체 회원 목록</h2>");
		
		out.println("<div style='text-align:center'><a href='member/join.html'>회원가입</a></div>");
		out.println("<table border='1'");
		
		out.println("<tr>");
		out.println("<td class='m1'>번호</td>");
		out.println("<td class='m1'>회원 이름</td>");
		out.println("<td class='m1'>회원 아이디</td>");
		out.println("<td class='m1'>회원 연락처</td>");
		out.println("<td class='m1'>수정|삭제</td>");
		out.println("</tr>");
		
		//1. MemberDAO 객체 생성후 selectAll()호출
		MemberDAO dao=new MemberDAO();
		ArrayList<MemberVO> arr=dao.selectAll();
		//2. ArrayList를 반복문 돌려서 MemberVO 를 꺼낸 뒤 getter를 이용해서 회원정보를 출력
		if(arr==null||arr.size()==0) {
			out.println("<tr>");
			out.println("<td colspan='5'><b>데이터가 없습니다</b></td>");
			out.println("</tr>");
		}else {
			for(MemberVO user:arr) {
				//---------------------------------------
				out.println("<tr>");
				
				out.println("<td>"+user.getNo()+"</td>");
				out.println("<td>"+user.getName()+"</td>");
				out.println("<td>"+user.getUserid()+"</td>");
				out.println("<td>"+user.getTel()+"</td>");
				
				out.println("<td><button class='btn' onclick='editMember("+user.getNo()+")'>수정</button>");
				out.println("<button class='btn' onclick='location.href=\"MemberDelete?no="+user.getNo()+"\"'>삭제</button></td>");
				
				out.println("</tr>");
				//---------------------------------------
			}//for------------
		}//else--------------
		out.println("</table>");
		out.println("</div>");
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		out.close();
	}

}
