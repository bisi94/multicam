package com.multi.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/*
 참조문서
 https://guide.ncloud-docs.com/docs/naveropenapiv3-speech-clovavoice
 개요
 https://api.ncloud-docs.com/docs/ai-naver-clovavoice
 */
@RestController
@PropertySource("classpath:apikey.properties")
public class NaverVoiceController {

	@Value("${clientId}")
	private String clientId;

	@Value("${clientSecret}")
	private String clientSecret;

	private Logger log = LoggerFactory.getLogger(NaverVoiceController.class);

	@GetMapping("/voiceForm")
	public ModelAndView voiceForm() {

		return new ModelAndView("naver/clova_voice");
	}

	@PostMapping(value = "/voiceToText", produces = { "text/plain; charset=utf-8" })
	public String voiceToText(@RequestParam("content") String content, HttpSession ses) {
		log.info("content={}", content);
		ServletContext app = ses.getServletContext();
		String upDir = app.getRealPath("/upload");
		log.info("upDir={}", upDir);
		String fileName = "mp3파일명들어올예정";

		// ----------------------------------------
		try {
			////////////////////////////////////////////////////////
			String text = URLEncoder.encode(content, "UTF-8"); // 13자
			////////////////////////////////////////////////////////
			String apiURL = "https://naveropenapi.apigw.ntruss.com/tts-premium/v1/tts";
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
			con.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);
			// post request
			String postParams = "speaker=nara&volume=0&speed=0&pitch=0&format=mp3&text=" + text;
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(postParams);
			wr.flush();
			wr.close();
			int responseCode = con.getResponseCode();
			BufferedReader br;
			if (responseCode == 200) { // 정상 호출
				InputStream is = con.getInputStream();
				int read = 0;
				byte[] bytes = new byte[1024];
				// 랜덤한 이름으로 mp3 파일 생성
				String tempname = Long.valueOf(new Date().getTime()).toString();
				File f = new File(upDir, tempname + ".mp3");
				f.createNewFile();
				OutputStream outputStream = new FileOutputStream(f);
				while ((read = is.read(bytes)) != -1) {
					outputStream.write(bytes, 0, read);
				}
				is.close();
				outputStream.close();
				fileName=tempname + ".mp3";
			} else { // 오류 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();
				while ((inputLine = br.readLine()) != null) {
					response.append(inputLine);
				}
				br.close();
				System.out.println(response.toString());
				fileName="error.mp3";
			}
		} catch (Exception e) {
			System.out.println(e);
			fileName="error.mp3";
		}

		// ----------------------------------------

		return fileName;
	}

}//////////////////////////
