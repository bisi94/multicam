package board.controller;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board.model.BoardDAOMyBatis;
import board.model.BoardVO;
import common.controller.AbstractAction;

public class BoardEditEndController extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// 0. 파일 업로드 처리
		ServletContext app=req.getServletContext();
		String upDir=app.getRealPath("/upload");
		System.out.println(upDir);
		File dir=new File(upDir);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		
		MultipartRequest mreq=null;
		try {
			mreq=new MultipartRequest(req, upDir, 100*1024*1024, "utf-8", 
					new DefaultFileRenamePolicy());
			System.out.println("업로드 성공");
			
		} catch (Exception e) {
			throw new ServletException("파일 업로드 실패: "+e);
		}
		// 1. 입력값 받기(no, name,pwd,subject,content,filename,old_filename)
		String noStr=mreq.getParameter("no");
		String name=mreq.getParameter("name");
		String pwd=mreq.getParameter("pwd");
		String subject=mreq.getParameter("subject");
		String content=mreq.getParameter("content");
		String filename=mreq.getFilesystemName("filename");//새로 첨부한 파일
				//req.getParameter("filename");[x]
		String old_filename=mreq.getParameter("old_filename");//예전에 첨부했던 파일
		
		long filesize=(mreq.getFile("filename")==null)?0:mreq.getFile("filename").length();
		
		// 2. 유효성 체크
		if(noStr==null||name==null||pwd==null||name.trim().isEmpty()||pwd.trim().isEmpty()) {
			this.setViewPage("list.me"); // ""앞에 슬래시를 붙이면 ""앞의 url이 변함
			this.setRedirect(true);
			return;
		}
		// 3. VO에 담기
		BoardVO vo=new BoardVO();
		vo.setNo(Integer.parseInt(noStr.trim()));
		vo.setName(name);
		vo.setPwd(pwd);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setFilename(filename);
		vo.setFilesize(filesize);
		
		// 4. 새로 첨부한 파일이 있다면 => 예전에 첨부했던 파일은 삭제 처리
		if(filename!=null && old_filename!=null) {
			File delFile=new File(upDir, old_filename);
			if(delFile.exists()) {
				boolean b=delFile.delete();
				System.out.println("파일 삭제 여부: "+b); //삭제된다면 true 반환
			}
			
			
		}
		
		// 5. DAO의 updateBoard(vo)
		BoardDAOMyBatis dao=new BoardDAOMyBatis();
		int n=dao.updateBoard(vo);
		String msg=(n>0)?"수정 처리 완료":"수정 실패";
		String loc=(n>0)?"list.me":"javascript:history.go(-1)"; //history.back이랑 같음
		
		req.setAttribute("msg", msg);
		req.setAttribute("loc",	loc);
		
		this.setViewPage("/message.jsp");
		this.setRedirect(false);
		
	}

}





