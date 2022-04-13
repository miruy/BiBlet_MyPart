package a.b.c.model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonPasing {
	static final String BASE_URLd = "https://dapi.kakao.com/v3/search/book";
	static String AUTH_KEYd = "KakaoAK" + " 6f9ab74953bbcacc4423564a74af264e";
	public static void main(String[] args) throws ParseException, org.json.simple.parser.ParseException {

		/**
		 *  REST API 호출하기
		 */
		URL url = null;
		HttpURLConnection con= null;
		JSONObject result = null;
		StringBuilder sb = new StringBuilder();
		try {
			// URL 객채 생성 (BASE_URL)
			url = new URL(BASE_URLd);
			// URL을 참조하는 객체를 URLConnection 객체로 변환
			con = (HttpURLConnection) url.openConnection();

			// 커넥션 request 방식 "GET"으로 설정
			con.setRequestMethod("GET");

			// 커넥션 request 값 설정(key,value) 
			con.setRequestProperty("Content-type", "application/json");
			con.setRequestProperty("Authorization", AUTH_KEYd);
			// setRequestProperty (key,value) 다른 예시
			// con.setRequestProperty("X-Auth-Token", AUTH_TOKEN);

			// 받아온 JSON 데이터 출력 가능 상태로 변경 (default : false)
			con.setDoOutput(true);

			// 데이터 입력 스트림에 담기
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
			while(br.ready()) {
				sb.append(br.readLine());
			}
			con.disconnect();
		}catch(Exception e) {
			e.printStackTrace();
		}

		/**
		 *  JSON 데이터 파싱하기
		 */
		// JSONParser에 입력 스트림에 담은 JSON데이터(sb.toString())를 넣어 파싱한 다음 JSONObject로 반환한다.
		
			result = (JSONObject) new JSONParser().parse(sb.toString());
			

		// REST API 호출 상태 출력하기
		StringBuilder out = new StringBuilder();
		out.append(result.get("status") +" : " + result.get("status_message") +"\n");

		// JSON데이터에서 "documents"라는 JSONObject를 가져온다.
		JSONObject meta = (JSONObject) result.get("meta");
		// JSONObject에서 Array데이터를 get하여 JSONArray에 저장한다.
		JSONArray array = (JSONArray) meta.get("documents");

		// 데이터 출력하기 (도서 제목만 우선꺼내기 테스트용 )
		JSONObject tmp;
		out.append("데이터 출력하기 \n");
		for(int i=0; i<array.size(); i++) {
			tmp = (JSONObject) array.get(i);
			out.append("title("+i+") :"+ tmp.get("title") +"\n");

			// movies[] 배열 안에 있는 genres[] 데이터 꺼내기
//			JSONArray array2 = (JSONArray) tmp.get("genres");
//			out.append("genres("+i+"): ");
//			for(int j=0; j<array2.size(); j++) {
//				out.append(array2.get(j));
//				if(j!=array2.size()-1) {
//					out.append(",");
//				}
//			}
			out.append("\n");
			out.append("\n");
		}
		System.out.println(out.toString());
	}
}
