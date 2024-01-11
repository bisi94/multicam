package user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.controller.AbstractAction;

public class LogoutAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// [1] 세션에 저장한 변수들을 모두 삭제한다 - removeAttribute(key값)
		HttpSession session=req.getSession();
		session.removeAttribute("loginId");
		session.removeAttribute("loginResult");
		
		
		// [2]세션에 저장한 모든 변수들을 삭제한다 => invalidate() 메서드 사용 권장
		session.invalidate();
		
		// 홈페이지로 redirect 방식으로 이동
		this.setViewPage("main.do");
		this.setRedirect(true);
		
	}

}
