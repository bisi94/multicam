package com.multi.service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service("ocrService")
@PropertySource("classpath:apiKey.properties")
public class OCRServiceImpl implements OCRService {

	@Value("${secretKey}")
	private String secretKey;
	
	@Value("${apiUrl}")
	private String apiUrl;
	
	Logger log=LoggerFactory.getLogger(getClass());
	
	@Override
	public String extractTextFromOcr(String filePath) {
		
		//-------------------------------------------------

		try {
			URL url = new URL(apiUrl);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setUseCaches(false);
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setReadTimeout(30000);
			con.setRequestMethod("POST");
			String boundary = "----" + UUID.randomUUID().toString().replaceAll("-", "");
			con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
			con.setRequestProperty("X-OCR-SECRET", secretKey);

			JSONObject json = new JSONObject();
			json.put("version", "V2");
			json.put("requestId", UUID.randomUUID().toString());
			json.put("timestamp", System.currentTimeMillis());
			JSONObject image = new JSONObject();
			image.put("format", "jpg");
			image.put("name", "demo");
			JSONArray images = new JSONArray();
			images.put(image);
			json.put("images", images);
			String postParams = json.toString();

			con.connect();
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			long start = System.currentTimeMillis();
			File file = new File(filePath);
			writeMultiPart(wr, postParams, file, boundary);
			wr.close();

			int responseCode = con.getResponseCode();
			BufferedReader br;
			if (responseCode == 200) {
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				response.append(inputLine);
			}
			br.close();
			String result=myJsonToString(response.toString());
			log.info(result);
			return result;
		} catch (Exception e) {
			log.error("error: "+e.toString());
			return e.toString();
		}//-------------------------------------------------
		
	}//------------------------------------------
	//Json형태의 문자열에서 필요한 문자열만 추출하여 반환하는 메소드
	private String myJsonToString(String jsonStr) {
		String result="";
		JSONObject jsonObj=new JSONObject(jsonStr);
		//images키값을 추출 => 배열형태 images:[{...fields:[]}]
		JSONArray imgArray=jsonObj.getJSONArray("images"); //images키값에 해당하는 배열을 추출
		//images 배열에 요소가 1개 있었으므로 index 0을 지정해서 꺼내오자
		JSONObject imgObj=imgArray.getJSONObject(0);
		JSONArray fieldArray=imgObj.getJSONArray("fields");
		//images:[{...fields:[{},{},{},{}]}]
		
		for(int i=0; i<fieldArray.length();i++) {
			JSONObject obj=fieldArray.getJSONObject(i);
			result+=obj.getString("inferText")+" ";
		}
		return result;
	}//---------------------------------

	private void writeMultiPart(OutputStream out, String jsonMessage, File file, String boundary) throws
	IOException {
	StringBuilder sb = new StringBuilder();
	sb.append("--").append(boundary).append("\r\n");
	sb.append("Content-Disposition:form-data; name=\"message\"\r\n\r\n");
	sb.append(jsonMessage);
	sb.append("\r\n");

	out.write(sb.toString().getBytes("UTF-8"));
	out.flush();

	if (file != null && file.isFile()) {
		out.write(("--" + boundary + "\r\n").getBytes("UTF-8"));
		StringBuilder fileString = new StringBuilder();
		fileString
			.append("Content-Disposition:form-data; name=\"file\"; filename=");
		fileString.append("\"" + file.getName() + "\"\r\n");
		fileString.append("Content-Type: application/octet-stream\r\n\r\n");
		out.write(fileString.toString().getBytes("UTF-8"));
		out.flush();

		try (FileInputStream fis = new FileInputStream(file)) {
			byte[] buffer = new byte[8192];
			int count;
			while ((count = fis.read(buffer)) != -1) {
				out.write(buffer, 0, count);
			}
			out.write("\r\n".getBytes());
		}

		out.write(("--" + boundary + "--\r\n").getBytes("UTF-8"));
	}
	out.flush();
}

}////////////////////////////////////////////////////
