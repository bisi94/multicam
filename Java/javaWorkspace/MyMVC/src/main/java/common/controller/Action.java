package common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
	
	//추상메소드
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception;
	
}
