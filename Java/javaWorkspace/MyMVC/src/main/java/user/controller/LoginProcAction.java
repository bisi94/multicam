package user.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.controller.AbstractAction;
import user.model.MemberDAO;

public class LoginProcAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// 로그인 로직 처리
		//1. 아이디,비번 받기
		String userid=req.getParameter("userid");
		String userpwd=req.getParameter("userpwd");
		String saveId=req.getParameter("saveId");
		System.out.println("saveId: "+saveId);
		
		//2. 유효성 체크 => login.do로 뷰페이지 설정, redirect방식 이동 설정
		if(userid==null||userpwd==null||userid.trim().isEmpty()||userpwd.trim().isEmpty()) {
			this.setViewPage("login.do");
			this.setRedirect(true);
			return;
		}
		
		//3. MemberDAO loginCheck(userid, userpwd) 호출
		//==> 반환값 : int 
		//		1 => 로그인 성공
		//	   -1 => 로그인 실패
		MemberDAO dao=new MemberDAO();
		
		int result=dao.loginCheck(userid, userpwd);
		System.out.println("result: "+result);
		HttpSession session=req.getSession();
		if(result>0) {
			//세션에 로그인한 사람의 아이디 저장
			session.setAttribute("loginId", userid);
			session.setAttribute("loginResult", "Y");
			session.setAttribute("loginMstate", result); //Integer객체로 저장된다
			
			//아이디 저장에 체크한 경우라면=> 쿠키 생성해서 쿠키에 uid key값으로 회원 아이디 저장하기
			Cookie ck=new Cookie("uid", userid);
			if(saveId!=null) {
				ck.setMaxAge(7*24*60*60); //7일간 유효
			}else {
				//체크하지 않은 경우
				ck.setMaxAge(0); //삭제
			}
			ck.setPath("/");
			res.addCookie(ck); //응답에 쿠키 추가
			
			// 로그인 인증이 되면 /main.do로 redirect 이동하자
			this.setViewPage("main.do");
			this.setRedirect(true);
		}else {
		// 로그인 인증 실패시 /message.jsp로 forward 이동한다
		session.setAttribute("loginResult", "N");
		req.setAttribute("msg", "아이디 또는 비밀번호가 일치하지 않습니다.");
		req.setAttribute("loc", "login.do");
		this.setViewPage("/message.jsp");
		this.setRedirect(false);
		}
		
	}

}
