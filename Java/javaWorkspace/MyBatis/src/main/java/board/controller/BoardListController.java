package board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.BoardDAOMyBatis;
import board.model.BoardVO;
import common.controller.AbstractAction;

public class BoardListController extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//현재 보여줄 페이지 번호를 받는다
		String cpageStr=req.getParameter("cpage");
		if(cpageStr==null||cpageStr.trim().isEmpty()) {
			cpageStr="1"; //1페이지를 디폴트로
		}		
		
		int cpage=Integer.parseInt(cpageStr.trim());
		if(cpage<1) {
			cpage=1; //1페이지를 디폴트로
		}
		
		BoardDAOMyBatis dao=new BoardDAOMyBatis();
		//[1] 총게시글 수 알아내기-totalCount ==> db에서 가져오기
		int totalCount=dao.getTotalCount();
		
		//[2] 한 페이지 당 보여줄 목록 개수 정하기-pageSize : 5개
		int pageSize=10;//5;
		
		//[3] 총 페이지수 구하기- pageCount ==> 연산
		int pageCount=1;
//		if(totalCount%pageSize==0) {
//			pageCount=totalCount/pageSize;
//		}else {
//			pageCount=totalCount/pageSize+1;
//		}
		pageCount=(totalCount-1)/pageSize+1;
		
		if(cpage>pageCount) {
			cpage=pageCount; //마지막 페이지로 지정
		}
		
//		[4] boardList.jsp에서 페이지 네비게이션 만들기
		
//		[5] BoardListController에서
//		cpage 파라미터값을 받는다
//		(cpage에 따라 DB에서 데이터를 끊어서 가져와야 하므로)
		
		int end=cpage*pageSize;
		int start=end-(pageSize-1);
		
		//페이징 처리시 전달할 변수를 해시맵에 저장
		Map<String,String> map=new HashMap<>();
		map.put("start", String.valueOf(start));
		map.put("end", String.valueOf(end));
		
		//게시글 목록 가져오기
		List<BoardVO> arr=dao.listBoard(map);
	
/*		[6] 페이지 네비게이션 링크를 블럭단위로 처리해보자
		이전 10개 | [11][12][13]....[20] |이후10개
		BoardListController에서
		페이지 블럭 단위를 설정하자

		pagingBlock=5;
		//5개 단위로 페이지를 묶는다
			이전5개| ..... |이후5개
		[1][2][3][4][5] | [6][7][8][9][10] | [11][12][13][14][15] | [16]....

		---------------------------------------------------------------------------------
		cpage		pagingBlock	prevBlock		nextBlock
		1,2,3,4,5		5		0		6
		6,7,8,9,10		5		5		11
		11,12,13,14,15	5		10		16
		---------------------------------------------------------------------------------

		prevBlock=(cpage-1)/pagingBlock * pagingBlock;
		nextBlock=prevBlock + (pagingBlock+1);
*/
		int pagingBlock=5; //페이지를 5개단위 블럭으로 묶어 처리하자
		int prevBlock=(cpage-1) /pagingBlock *pagingBlock;
		int nextBlock=prevBlock +(pagingBlock+1);
		
		req.setAttribute("boardArr", arr);
		req.setAttribute("totalCount", totalCount);
		req.setAttribute("pageCount", pageCount);
		req.setAttribute("cpage", cpage);
		
		req.setAttribute("pagingBlock", pagingBlock);
		req.setAttribute("prevBlock", prevBlock);
		req.setAttribute("nextBlock", nextBlock);
		
		//this.setViewPage("/board/boardList.jsp");
		
		this.setViewPage("/board/boardList2.jsp");
		
		this.setRedirect(false); //false ==> forward이동 / true ==> redirect이동
	}

}
