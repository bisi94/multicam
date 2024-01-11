package com.multi.myajax;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.multi.model.ReviewVO;
import com.multi.service.ReviewService;

import lombok.extern.log4j.Log4j;
/* Rest : Representational State Transfer의 약자
 * - 전송방식과 URI를 결합해서 원하는 작업을 지정하여 처리하도록 하는 방식
 * 	 GET/POST/PUT/DELETE
 *   - GET : 조회
 *   - DELETE : 삭제처리
 *   - POST : INSERT처리
 *   - PUT : Update처리
 *   ...
 *   URI +GET/POST/PUT/DELETE
 *   
 *   GET : /users/100 ==> 100번 회원의 정보를 조회하는 로직 처리한다
 *   GET : /users ===> 모든 회원 목록을 조회하는 로직을 처리한다
 *   DELETE: /delete/3 ==> 3번 회원 정보를 삭제 처리...
 * @RestController==> REST방식의 데이터를 처리하는 여러 기능을 쉽게 할 수 있다.
 * */
@RestController//Ajax전용 컨트롤러 -restful 방식 지원
@Log4j
public class ReviewRestController {
	
	@Inject
	private ReviewService rService;
	
	@GetMapping(value="/test", produces = {"application/json; charset=UTF-8"})
	public List<UserVO> test() {
		List<UserVO> arr=new ArrayList<>();
		arr.add(new UserVO(1, "고길동", "서울", "1111"));
		arr.add(new UserVO(2, "이길동", "서천", "1112"));
		return arr;
	}
	@GetMapping("/test2")
	public String test2() {
		
		return "HelloWorld";
		//@RestController에서는 반환되는 문자열이 뷰네임이 아니라 응답데이터(@ResponseBody)로 취급한다
	}
	//GET	/reviews	==> 모든 게시글 보여주기
	//GET	/reviews/10 ==> 10번 게시글 보여주기
	//GET	/reviewForm ==> 글쓰기 폼 보여주기 (WEB-INF/views/shop/reviewWrite.jsp)
	//Post	/reviews ==> 글쓰기 처리
	//Delete /reviews ==> 10번 게시글 삭제
	@GetMapping("/reviewForm")
	/*public String reviewForm() {
		return "shop/reviewWrite"; //데이터취급하여 웹에 텍스트를 출력
	}*/
	public ModelAndView reviewForm() { //==> jsp로 넘기기 위해 ModelAndView로 반환 
		ModelAndView mv=new ModelAndView();
		mv.addObject("title", "상품 리뷰 글쓰기");//데이터
		mv.setViewName("shop/reviewWrite");//뷰네임
		///WEB-INF/views/shop/reviewWrite.jsp 를 찾아간다
		return mv;
	}
	
	@PostMapping(value="/reviews", produces= {"application/json; charset=UTF-8"})
	public ModelMap reviewInsert2(@ModelAttribute("vo") ReviewVO vo,
			@RequestParam("mfilename") MultipartFile mfilename, HttpSession session) {
		log.info("vo: "+vo);
		//1. 업로드 절대경로 얻기
		ServletContext app=session.getServletContext();
		String upDir=app.getRealPath("/resources/review_images");
		log.info("upDir: "+upDir);
		File dir=new File(upDir);
		if(!dir.exists()) {
			dir.mkdirs(); //make directorys
		}
		
		//2. 파일 업로드 처리
		if(!mfilename.isEmpty()) {
			String fname=mfilename.getOriginalFilename();
			try {
				mfilename.transferTo(new File(upDir, fname));
				//3. 업로드 파일명을 ReviewVO에 setting
				vo.setFilename(fname);
			}catch(IOException e) {
				log.error(e);
			}
		}
		
		//4. DB에 글 insert
		
		int n=rService.insertReview(vo);
		String str=(n>0)?"success":"fail";

		ModelMap map=new ModelMap();
		map.put("result", str);
		
		return map;
	}
	
	//파일 업로드를 하지 않는 글쓰기인 경우
	@PostMapping(value="/reviews_old", produces= {"application/json; charset=UTF-8"})
	public Map<String, String> reviewInsert(ReviewVO vo){
		log.info("vo: "+vo);
		
		int n=rService.insertReview(vo);
		
		String str=(n>0)?"success":"fail";
		
		Map<String, String> map=new HashMap<>();
		map.put("result", str);//success, fail
		return map;
	}
	
	@GetMapping(value="/reviews", produces= {"application/json; charset=UTF-8"})
	public List<ReviewVO> reviewList(int pnum){
		log.info("pnum: "+pnum);
		List<ReviewVO> arr=rService.getReviewList(pnum);
		return arr;
	}
	
	@GetMapping(value="reviews/{num}", produces = {"application/json; charset=UTF-8"})
	public ReviewVO getReview(@PathVariable("num") int num) {
		log.info("num: "+num);
		
		ReviewVO vo=rService.getReview(num);
		
		return vo;
	}//---------------------------------------
	
	//데이터를 json유형으로 보낼 경우 @RequestBody
	//데이터를 파라미터유형으로 보낼 경우 @RequestParam 
	@PutMapping(value="/reviews", produces = {"application/json; charset=UTF-8"})
	public ModelMap reviewUpdate(@RequestBody ReviewVO vo) {
		log.info("vo: "+vo);
		int n=rService.updateReview(vo);
		String str=(n>0)?"success":"fail";
		ModelMap map=new ModelMap();
		map.addAttribute("result",str);
		return map;
	}//---------------------------------
	
	@PutMapping(value="/reviews_test", produces = {"application/json; charset=UTF-8"})
	public ModelMap reviewUpdate_test(ReviewVO vo, @RequestParam(value="mfilename", required = false) MultipartFile mfilename) {
		log.info("vo: "+vo);
		log.info("mfile: "+mfilename);
		
		String str="test";
		ModelMap map=new ModelMap();
		map.addAttribute("result",str);
		return map;
	}//--------------------------------
	
	
	@DeleteMapping(value="reviews/{num}", produces= {"application/json; charset=UTF-8"})
	public ModelMap reviewDelete(@PathVariable("num") int num) {
		log.info("num: "+num);
		String str="test";
		int n=rService.deleteReview(num);
		
		str=(n>0)?"success":"fail";
		
		ModelMap map=new ModelMap("result",str);
		return map;
	}//-----------------------------------
	
	

}









