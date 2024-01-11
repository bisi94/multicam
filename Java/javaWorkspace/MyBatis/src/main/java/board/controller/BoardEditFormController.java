package board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.BoardDAOMyBatis;
import board.model.BoardVO;
import common.controller.AbstractAction;

public class BoardEditFormController extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
//		[1] 글번호,비번 받기 (no,pwd)
		String noStr=req.getParameter("no");
		String pwd=req.getParameter("pwd");
		
//		[2] 유효성 체크 list.me로 redirect 설정
		if(noStr==null||pwd==null||noStr.trim().isEmpty()||pwd.trim().isEmpty()) {
			this.setViewPage("list.me");
			this.setRedirect(true);
			return;
		}
//		[3] 해당글의 비밀번호가 일치하는지 여부를 체크
		BoardDAOMyBatis dao=new BoardDAOMyBatis();
		
		int no=Integer.parseInt(noStr.trim());
		
		BoardVO vo=dao.getBoard(no);
		String dbPwd="";
		if(vo!=null) {
			dbPwd=vo.getPwd(); //해당 글의 비밀번호을 할당
		}
		
		//비밀번호가 불일치하는 경우
		if(!pwd.equals(dbPwd)) {
			
			req.setAttribute("msg", "비밀번호가 일치하지 않습니다");
			//location.href='history.back()' [x]
			req.setAttribute("loc", "javascript:history.back()");
			
			this.setViewPage("/message.jsp");
			this.setRedirect(false);
			return;
		}
		
//		-비번 불일치
			
//		-비번 일치
		req.setAttribute("vo", vo);
		
		this.setViewPage("/board/boardEdit.jsp");
		this.setRedirect(false);
	}

}
