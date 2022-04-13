package a.b.c.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


public class BookSearchAPI {

	/*	
	 * https://dapi.kakao.com/v3/search/book (/isbn})로 url요청이 왔을 경우
	 *  isbn에 해당하는 도서를 검색하는 아래 컨트롤러가 실행될 것이며, 
	 *  json형식으로 응답 받겠다.
	 */
	@RequestMapping(value = "/search/book" , produces ="application/json") 
	public void bookSearch(
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "book_name", required = false) String book_name, 
			HttpServletResponse response) throws Exception {
		
		//결과를 담을 변수들 
		StringBuffer result = new StringBuffer(); 
		String strResult = "";
		
		try{ 
		// URL 설정
		StringBuilder urlBuilder = new StringBuilder("https://dapi.kakao.com/v3/search/book");
		
		// 요청파라미터 변수 인코딩
		urlBuilder.append("?target=" + URLEncoder.encode(title, "UTF-8"));
		urlBuilder.append("&query=" + book_name);
//		urlBuilder.append("&type=json");
		URL url = new URL(urlBuilder.toString());

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		// Request 형식 설정
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-Type", "application/json");

		// 응답 데이터 받아오기
		BufferedReader rd;
		if (conn.getResponseCode() >= 200 & conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		
		String line;
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		rd.close();
		conn.disconnect();
		strResult = result.toString();
		}catch(Exception e){
			e.printStackTrace();
		}

		// Response 형식 설정 -> JSON으로 데이터 보내기
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(strResult);
		}
	}
