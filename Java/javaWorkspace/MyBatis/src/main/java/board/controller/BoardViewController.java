package board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.BoardDAOMyBatis;
import board.model.BoardVO;
import common.controller.AbstractAction;

public class BoardViewController extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
//	- 글번호 받기
	String noStr=req.getParameter("no");

//	- 유효성 체크 list.me redirect 설정
	if(noStr==null||noStr.trim().isEmpty()) {
		this.setViewPage("list.me");
		this.setRedirect(true);
		return;
	}
			
//	- BoardDAOMyBatis
//	   BoardVO   getBoard(int no) 호출
//	  반환받은 BoardVO를 req에 저장
	BoardDAOMyBatis dao=new BoardDAOMyBatis();
	
	//해당 글 조회수 증가
	int n=dao.updateReadnum(Integer.parseInt(noStr.trim()));
	
	BoardVO board=dao.getBoard(Integer.parseInt(noStr.trim()));
	
	req.setAttribute("board", board);
	
//	 - 뷰페이지 지정 /board/boardView.jsp
//	   forward방식 지정
	this.setViewPage("/board/boardView.jsp");
	this.setRedirect(false);


	}

}
