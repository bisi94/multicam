package com.multi.controller;

import java.io.File;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.multi.service.OCRService;

@RestController
public class NaverOCRController {

	@Inject
	private OCRService ocrService;
	
	@GetMapping("/ocrForm")
	public ModelAndView ocrForm() {
		return new ModelAndView("naver/clova_ocrView");
	}
	
	@PostMapping(value="/ocrEnd", produces= {"text/plain; charset=utf-8"})
	public String ocrProcess(@RequestParam("uploadFile") MultipartFile mfile, HttpServletRequest req) {
		String result="test";
		ServletContext app=req.getServletContext();
		String upDir=app.getRealPath("/ocr_images");
		System.out.println(upDir);
		File dir=new File(upDir);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		String fname=mfile.getOriginalFilename();
		File file=null;
		try {
		mfile.transferTo(file=new File(upDir, fname));
		String filePath=file.getAbsolutePath();
		result=ocrService.extractTextFromOcr(filePath);
		
		}catch(Exception e) {
			e.printStackTrace();
			result=e.getMessage();
		}
		return result;
	}
	
}
