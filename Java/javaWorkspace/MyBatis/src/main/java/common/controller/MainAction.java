package common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("MainAction의 execute()호출됨...");
	
		//뷰페이지 설정
		this.setViewPage("/main.jsp");
		//이동방식
		this.setRedirect(false); //false면 forward이동
	}

}
