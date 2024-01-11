package common.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*FrontController : *.do 패턴의 모든 요청을 받아들인다.
 * - command.properties 파일에 있는 매핑 정보를 읽어들여 해당 요청uri와 매핑되어 있는
 *   SubController(XXXAction)을 찾아 객체화 한 뒤 해당 객체의 메소드(execute)를 호출한다.
 * - 서브 컨트롤러는 해당 작업을 수행한 뒤에 다시 FrontController로 돌아와 보여줘야 할 View
 *   페이지(JSP) 정보를 넘긴다.
 * - FrontController는 해당 뷰페이지로 이동시킨다. (forward방식 이동 or redirect방식 이동)    
 * */
@WebServlet(
		urlPatterns = { "*.do" }, 
		initParams = { 
				@WebInitParam(name = "config", 
						value = "C:\\DEV\\Java\\javaWorkspace\\MyMVC\\src\\main\\webapp\\WEB-INF\\command.properties")
		})
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private HashMap<String, Object> cmdMap=new HashMap<>();
	//command.properties파일에 있는 값들을 해시맵에 옮긴다
	
	private AbstractAction action=null;
	
	public void init(ServletConfig conf) throws ServletException {
		System.out.println("init()호출됨...");//첫 방문자일때 한번 호출됨
		String propsPath=conf.getInitParameter("config");
		System.out.println("propsPath: "+propsPath);
		
		Properties pr=new Properties();	
		try {
			FileInputStream fis=new FileInputStream(propsPath);
			pr.load(fis);
			//command.properties파일 mapping정보를 Properties객체로 옮긴다
			
			if(fis!=null) fis.close();
			
//			String val=pr.getProperty("/main.do");
//			System.out.println("val="+val);
			
//			pr에서 key값들을 추출하자
			
			Set<Object> set=pr.keySet();
			if(set!=null) {
				for(Object key:set) {
					String cmd=key.toString(); //key값 "/main.do"
					String className=pr.getProperty(cmd);//"common.controller.MainAction"
					if(className!=null){
						className=className.trim(); //앞뒤 공백 제거
					}
					System.out.println(cmd+": "+className);
					
					//className을 실제 객체로 인스턴스화
					Class<?> cls=Class.forName(className);
					Object cmdInstance=cls.getDeclaredConstructor().newInstance();
					//해당 클래스의 객체를 생성해줌
					//////////////////////////
					cmdMap.put(cmd, cmdInstance);
					//////////////////////////
				}//for------------
				System.out.println("cmdMap저장완료: cmdMap.size() => "+cmdMap.size());
			}//if-----------------
			
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new ServletException(e); //익셉션을 브라우저에도 출력하려면 이 기능 사용
		}
	}//init()----------------------

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd=request.getServletPath(); //컨텍스트명 이후의 경로를 반환
		System.out.println("cmd==="+cmd);
		Object instance=cmdMap.get(cmd);
		if(instance==null) {
			System.out.println("Action이 null");
			throw new ServletException("Action이 null입니다");
		}
		
		System.out.println("instance==="+instance);
		///////////////////////////////////
		if(instance instanceof AbstractAction) {
			action=(AbstractAction)instance;
		}//if-----
			//execute()메소드에서 로직을 수행한 뒤 뷰페이지와 이동방식을 지정(setting)
		try {
			action.execute(request, response);
		}catch(Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
		
		
		String viewPage=action.getViewPage();
		boolean isRedirect=action.isRedirect();
		
		if(isRedirect) {
			//redirect로 이동
			response.sendRedirect(viewPage);
			return;
			
		}else {
			//forward로 이동
			System.out.println(viewPage);
			if(viewPage==null) {
				System.out.println("viewPage가 null");
				
			}
			RequestDispatcher disp=request.getRequestDispatcher(viewPage);
			disp.forward(request, response);
		}
		
		///////////////////////////////////
		
	}
	
	
	
	
	
	
	

}/////////////////////////////////////

















