package my.com;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
/* [1] web.xml 에 등록
 * [2] @WebServlet() 어노테이션에 등록
 * */
public class HiServlet extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out=res.getWriter();
		out.println("<h1>Hi Servlet</h1>");
		out.println("<h2>url pattern을 web.xml에 등록했습니다</h2>");
		out.println("<hr>");
		
		out.println("<img src='images/lyan.png'>");
		
		out.close();
	}
}
