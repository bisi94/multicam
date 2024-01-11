package com.multi.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

//Naver Clova Speech Recognition
@RestController
@PropertySource("classpath:apiKey.properties") //프로퍼티파일이 위치한 곳을 지정
public class NaverCSRController {
	
	private Logger log=LoggerFactory.getLogger(getClass());
	
	@Value("${clientId}") //apiKey.properties파일에서 clientId에 해당하는 값을 찾아 주입해준다
	private String clientId;
	
	@Value("${clientSecret}")
	private String clientSecret;
	
	private String apiUrl="https://naveropenapi.apigw.ntruss.com/recog/v1/stt";
	
	@GetMapping("/csrform")
	public ModelAndView csrform() {
		log.info("clientId={}", clientId);
		log.info("clientSecret={}", clientSecret);
		ModelAndView mv=new ModelAndView("naver/clova_speech");
		return mv;
	}//--------------------------------
	/*
		 https://guide.ncloud-docs.com/docs/ko/naveropenapiv3-application
		 https://api.ncloud-docs.com/docs/ai-naver-clovaspeechrecognition
		 https://api.ncloud-docs.com/docs/ai-naver-clovaspeechrecognition-stt
	 */	
	
	@PostMapping(value="/csrSpeech", produces= {"application/json; charset=utf-8"})
	public ModelMap speechRecognition(@RequestParam("mp3file") MultipartFile mfile, 
			@RequestParam("language") String lang, HttpSession ses) {
		
		ServletContext app=ses.getServletContext();
		String upDir=app.getRealPath("/upload");
		log.info("upDir={}", upDir);
		
		File dir=new File(upDir);
		if(!dir.exists()) {//디렉토리가 없다면
			dir.mkdirs();//업로드 디렉토리 생성 => src/main/webapp/upload 
		}
		
		String fname=mfile.getOriginalFilename();//첨부파일명
		String msg="test";
		try {
			mfile.transferTo(new File(upDir, fname));
			//------------------------------
			//String imgFile = "음성 파일 경로";
            File voiceFile = new File(upDir, fname);

            String language = lang;        // 언어 코드 ( Kor, Jpn, Eng, Chn )
            String apiURL = "https://naveropenapi.apigw.ntruss.com/recog/v1/stt?lang=" + language;
            URL url = new URL(apiURL);

            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setUseCaches(false);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestProperty("Content-Type", "application/octet-stream");
            conn.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
            conn.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);

            ///네이버로 데이터 전송-----------------------
            OutputStream outputStream = conn.getOutputStream();
            FileInputStream inputStream = new FileInputStream(voiceFile);
            byte[] buffer = new byte[4096];
            int bytesRead = -1;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);//네이버로 바이너리파일 전송
            }
            outputStream.flush();
            inputStream.close();
            //-------------------------------------
            
            //네이버의 응답 듣기-------------------------
            BufferedReader br = null;
            int responseCode = conn.getResponseCode();
            if(responseCode == 200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {  // 오류 발생
                System.out.println("error!!!!!!! responseCode= " + responseCode);
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            }
            String inputLine;

            if(br != null) {
                StringBuffer response = new StringBuffer();
                while ((inputLine = br.readLine()) != null) {
                    response.append(inputLine);
                }
                br.close();
                System.out.println(response.toString());
                
                //msg=new JSONObject(response.toString()).toString();
                msg=response.toString();
                
                log.info("msg: "+msg);
            } else {
                System.out.println("error !!!");
                msg="error!!!";
            }
			
		//------------------------------
			
		}catch(Exception e) {
			log.error("error: {}",e.getMessage());
			msg="error "+e.getMessage();
		}
		
		ModelMap map=new ModelMap();
		map.addAttribute("result", msg);
		return map;
	}
	
	
}//////////////////////////////////////







