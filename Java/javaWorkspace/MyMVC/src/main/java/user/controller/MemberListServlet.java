package user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.model.MemberDAO;
import user.model.MemberVO;
import java.sql.*;
import java.util.*;
import javax.servlet.*;

//Controller
/**
 * 
 */
@WebServlet("/admin/memberList.do")
public class MemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. MemberDAO생성해서 selectAll()호출
		MemberDAO dao = new MemberDAO();
		try {
				
		// 2. 반환하는 ArrayList를 받아서 request에 저장
			ArrayList<MemberVO> arr = dao.selectAll();
		
		// key값: users 로 통일시키기
			request.setAttribute("users", arr);
		
		// 3. "member/memberList.jsp"로 forward 이동시키기
		
			RequestDispatcher dispatcher = request.getRequestDispatcher("/member/memberList.jsp");
			dispatcher.forward(request, response);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

}
