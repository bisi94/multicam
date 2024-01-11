package board.controller;
import common.controller.*;
import javax.servlet.http.*;

public class BoardFormController extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		this.setViewPage("/board/boardWrite.jsp");
		this.setRedirect(false);
	}
	
}
