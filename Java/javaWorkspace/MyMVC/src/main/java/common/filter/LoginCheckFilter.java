package common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns = {"/user/*","/admin/*"})
public class LoginCheckFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 세션에 저장되어 있는 loginId가 있는지 체크
		// 없다면 => "로그인 후 이용할 수 있어요" 보여주고 return
		System.out.println("LoginCheckFilter...");
		
		HttpServletRequest req=(HttpServletRequest)request;
		
		HttpSession session=req.getSession();
		
		String loginId=(String)session.getAttribute("loginId");
		if(loginId==null) {
			
			req.setAttribute("msg", "로그인 후 이용가능합니다");
			req.setAttribute("loc", req.getContextPath()+"/login.do"); //절대경로+파일명
			
			RequestDispatcher disp=req.getRequestDispatcher("/message.jsp");
			disp.forward(request, response);
			
			
			return;
		}
		
		
		
		// 로그인 했다면 => 다음 필터로 넘어간다.
		chain.doFilter(request, response);

	}

}
