package common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AdminCheckFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// "/admin/*" 패턴의 요청이라면 동작한다
		// 세션에 저장된 loginMstate를 꺼내서 9(관리자 mstate)가 아니라면
		// "관리자만 이용가능합니다" 보여주고 return;
		// web.xml에 AdminCheckFilter 등록
		System.out.println("AdminCheckFilter...");
		
		HttpServletRequest req=(HttpServletRequest)request;
		
		HttpSession session=req.getSession();
		
		/* int loginMstate=(int)session.getAttribute("loginMstate"); */
		Integer mstate=(Integer)session.getAttribute("loginMstate");
		
		// 9라면
		if(mstate==null || mstate!=9) {
			req.setAttribute("msg", "관리자만 이용가능합니다.");
			req.setAttribute("loc", req.getContextPath()+"/main.do"); //절대경로+파일명
			
			RequestDispatcher disp=req.getRequestDispatcher("/message.jsp");
			disp.forward(request, response);
			
			return;
		}
		
		
		chain.doFilter(request, response);
	}

}
