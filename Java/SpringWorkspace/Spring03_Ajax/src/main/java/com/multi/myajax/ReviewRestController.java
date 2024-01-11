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
/* Rest : Representational State Transfer�� ����
 * - ���۹�İ� URI�� �����ؼ� ���ϴ� �۾��� �����Ͽ� ó���ϵ��� �ϴ� ���
 * 	 GET/POST/PUT/DELETE
 *   - GET : ��ȸ
 *   - DELETE : ����ó��
 *   - POST : INSERTó��
 *   - PUT : Updateó��
 *   ...
 *   URI +GET/POST/PUT/DELETE
 *   
 *   GET : /users/100 ==> 100�� ȸ���� ������ ��ȸ�ϴ� ���� ó���Ѵ�
 *   GET : /users ===> ��� ȸ�� ����� ��ȸ�ϴ� ������ ó���Ѵ�
 *   DELETE: /delete/3 ==> 3�� ȸ�� ������ ���� ó��...
 * @RestController==> REST����� �����͸� ó���ϴ� ���� ����� ���� �� �� �ִ�.
 * */
@RestController//Ajax���� ��Ʈ�ѷ� -restful ��� ����
@Log4j
public class ReviewRestController {
	
	@Inject
	private ReviewService rService;
	
	@GetMapping(value="/test", produces = {"application/json; charset=UTF-8"})
	public List<UserVO> test() {
		List<UserVO> arr=new ArrayList<>();
		arr.add(new UserVO(1, "��浿", "����", "1111"));
		arr.add(new UserVO(2, "�̱浿", "��õ", "1112"));
		return arr;
	}
	@GetMapping("/test2")
	public String test2() {
		
		return "HelloWorld";
		//@RestController������ ��ȯ�Ǵ� ���ڿ��� ������� �ƴ϶� ���䵥����(@ResponseBody)�� ����Ѵ�
	}
	//GET	/reviews	==> ��� �Խñ� �����ֱ�
	//GET	/reviews/10 ==> 10�� �Խñ� �����ֱ�
	//GET	/reviewForm ==> �۾��� �� �����ֱ� (WEB-INF/views/shop/reviewWrite.jsp)
	//Post	/reviews ==> �۾��� ó��
	//Delete /reviews ==> 10�� �Խñ� ����
	@GetMapping("/reviewForm")
	/*public String reviewForm() {
		return "shop/reviewWrite"; //����������Ͽ� ���� �ؽ�Ʈ�� ���
	}*/
	public ModelAndView reviewForm() { //==> jsp�� �ѱ�� ���� ModelAndView�� ��ȯ 
		ModelAndView mv=new ModelAndView();
		mv.addObject("title", "��ǰ ���� �۾���");//������
		mv.setViewName("shop/reviewWrite");//�����
		///WEB-INF/views/shop/reviewWrite.jsp �� ã�ư���
		return mv;
	}
	
	@PostMapping(value="/reviews", produces= {"application/json; charset=UTF-8"})
	public ModelMap reviewInsert2(@ModelAttribute("vo") ReviewVO vo,
			@RequestParam("mfilename") MultipartFile mfilename, HttpSession session) {
		log.info("vo: "+vo);
		//1. ���ε� ������ ���
		ServletContext app=session.getServletContext();
		String upDir=app.getRealPath("/resources/review_images");
		log.info("upDir: "+upDir);
		File dir=new File(upDir);
		if(!dir.exists()) {
			dir.mkdirs(); //make directorys
		}
		
		//2. ���� ���ε� ó��
		if(!mfilename.isEmpty()) {
			String fname=mfilename.getOriginalFilename();
			try {
				mfilename.transferTo(new File(upDir, fname));
				//3. ���ε� ���ϸ��� ReviewVO�� setting
				vo.setFilename(fname);
			}catch(IOException e) {
				log.error(e);
			}
		}
		
		//4. DB�� �� insert
		
		int n=rService.insertReview(vo);
		String str=(n>0)?"success":"fail";

		ModelMap map=new ModelMap();
		map.put("result", str);
		
		return map;
	}
	
	//���� ���ε带 ���� �ʴ� �۾����� ���
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
	
	//�����͸� json�������� ���� ��� @RequestBody
	//�����͸� �Ķ������������ ���� ��� @RequestParam 
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









