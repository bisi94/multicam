package board.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board.model.BoardDAOMyBatis;
import board.model.BoardVO;
import common.controller.AbstractAction;

//cos.jar를 WEB-INF/lib 아래 두어야 함
public class BoardWriteEndController extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// 0. 파일 업로드 처리하기
		//	[1]업로드할 디렉토리 절대경로 알아내기
		//String upDir="C:\\Users\\qltl0\\OneDrive\\Desktop\\multicamp";
		ServletContext app=req.getServletContext();
		String upDir=app.getRealPath("/upload");
		System.out.println(upDir);
		
		File dir=new File(upDir);
		
		if(!dir.exists()) {
			//디렉토리 생성
			dir.mkdirs();
		}
		
		MultipartRequest mreq=null; //cos.jar에 있는 객체
		try {
			DefaultFileRenamePolicy df=new DefaultFileRenamePolicy();
			
			mreq=new MultipartRequest(req, upDir, 100*1024*1024,"utf-8", df);
			System.out.println("업로드 성공: "+upDir);
			
		}catch(IOException e) {
			throw new ServletException("파일 업로드 실패: "+e);
		}
		
		
		// 1. name, subject, content, pwd, filename 값 받기
		String name=mreq.getParameter("name");
		String subject=mreq.getParameter("subject");
		String content=mreq.getParameter("content");
		String pwd=mreq.getParameter("pwd");
		//첨부파일명
		String filename=mreq.getFilesystemName("filename");
		//첨부파일 크기
		File file=mreq.getFile("filename");
		long filesize=0;
		if(file!=null) {
			filesize=file.length();//파일크기
		}

		//2. 유효성 체크 -redirect, write.me
		if(name==null||pwd==null||name.trim().isEmpty()||pwd.trim().isEmpty()) {
			this.setViewPage("write.me");
			this.setRedirect(true);
			
			return;
		}
		//3. BoardVO 에 담기
		BoardVO vo=new BoardVO();
		vo.setName(name);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPwd(pwd);
		vo.setFilename(filename);
		vo.setFilesize(filesize);
		
		//4. BoardDAOMyBatis insertBoard()호출
		BoardDAOMyBatis dao=new BoardDAOMyBatis();
		int n=0;
			//for(int i=0;i<30;i++) 
				n=dao.insertBoard(vo);
				
		System.out.println("n: "+n);
			
		
		//5. list.me로 redirect이동
		if(n>0) {
			this.setViewPage("list.me");
			this.setRedirect(true);
		}else {
			this.setViewPage("write.me");
			this.setRedirect(true);
		}

	}

}
