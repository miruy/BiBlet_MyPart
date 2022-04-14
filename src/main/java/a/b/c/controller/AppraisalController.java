package a.b.c.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import a.b.c.HomeController;
import a.b.c.model.AppraisalVO;
import a.b.c.model.BookShelfVO;
import a.b.c.model.CommentCmd;
import a.b.c.model.MemberVO;
import a.b.c.service.AppraisalService;

@Controller
@SessionAttributes("BookInfoVO")
@RequestMapping("/AppraisalPage")
public class AppraisalController {
	// DB데이터 확인용 Debug코드
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private AppraisalService appraisalService;

	// 모든 도서 정보 불러오기
	@GetMapping(value = "/list")
	public String findAllBook() {
		return "bookInfoList";
	}

	// 도서 상세보기 및 평가작성(form)
	@GetMapping(value = "/read/{isbn}")
	public String bookDetailAndComment(@RequestParam(required = false)String query, Model model) {
		System.out.println("bookDetailAndComment");	
		
		System.out.println(query);
		
		model.addAttribute("query", query);

		// 해당 도서의 대한 평가 갯수
//		int commentCount = appraisalService.commentCount(isbn);
//		logger.debug("count : " + commentCount);
//		model.addAttribute("commentCount", commentCount);

		// 해당 도서의 대한 모든 평가 불러오기
//		String isbn = "1162203625";
//		List<allCommentByBookCmd> commentsByMembers = appraisalService.findAllComment(isbn);
//
//		for (allCommentByBookCmd test : commentsByMembers) {
//			logger.debug("mem_id:" + test.getMem_id());
//			logger.debug("mem_pic:" + test.getMem_pic());
//			logger.debug("star:" + Integer.toString(test.getStar()));
//			logger.debug("book_comment:" + test.getBook_comment());
//			logger.debug("start_date:" + test.getStart_date());
//			logger.debug("end_date:" + test.getEnd_date());
//		}
//		
//		model.addAttribute("commentsByMembers", commentsByMembers);
		
		return "detailAndComment";
	}

	// 평가 작성(insert)
	@PostMapping(value = "/read/{isbn}")
	public String writeComment(@ModelAttribute("appraisal")CommentCmd commentCmd, Model model) {
	
		AppraisalVO appraisal = new AppraisalVO();
		BookShelfVO bookShelf = new BookShelfVO();
		
		MemberVO member = new MemberVO();
		Long mem_num = (long) 1; // 테스트용 회원 번호(현재 테이블에 6번회원까지 있음)
		member.setMem_num(mem_num);
		
		
		appraisal.setStar(commentCmd.getStar());
		System.out.println("별점 : " + commentCmd.getStar());	
		
		appraisal.setBook_comment(commentCmd.getBook_comment());
		System.out.println("평가 : " + commentCmd.getBook_comment());	
		
		appraisal.setStart_date(commentCmd.getStart_date());
		System.out.println("시작날짜 : " + commentCmd.getStart_date());
		
		appraisal.setEnd_date(commentCmd.getEnd_date());
		System.out.println("다읽은날짜 : " + commentCmd.getEnd_date());
		
		appraisal.setCo_prv(commentCmd.getCo_prv());
		System.out.println("공개여부 : " + commentCmd.getCo_prv());
		
		appraisal.setBook_status_num((long)2);
		System.out.println("독성상태 : " + appraisal.getBook_status_num());
		
		System.out.println("isbn : " + commentCmd.getIsbn());
		
		bookShelf.setBook_status(2);
		System.out.println("보관함에 들어갈 독서상태 : " + bookShelf.getBook_status());
		
		bookShelf.setMem_num(member.getMem_num());
		System.out.println("보관함에 들어갈 이평가작성한 회원번호 : " + bookShelf.getMem_num());
		
		bookShelf.setIsbn(commentCmd.getIsbn());
		System.out.println("보관함에 들어갈 ISBN : " + bookShelf.getIsbn());
		
		appraisalService.insertBookShelf(bookShelf);
		appraisalService.writeComment(appraisal);

		return "detailAndComment";
	}
	
	//메인 페이지
//	@RequestMapping(value="/list", produces = "application/json")
//	public String jsonPasing()throws Exception {
//	
//	System.out.println("?????");
//		final String base_url = "https://dapi.kakao.com/v3/search/book?target=title";
//		final String auth_key = "KakaoAK" + " 6f9ab74953bbcacc4423564a74af264e";
//		
//
//			/**
//			 *  REST API 호출하기
//			 */
//			URL url = null;
//			HttpURLConnection con= null;
//			JSONObject result = null;
//			StringBuilder sb = new StringBuilder();
//			try {
//				// URL 객채 생성 (BASE_URL)
//				url = new URL(base_url);
//				// URL을 참조하는 객체를 URLConnection 객체로 변환
//				con = (HttpURLConnection) url.openConnection();
//
//				// 커넥션 request 방식 "GET"으로 설정
//				con.setRequestMethod("GET");
//
//				// 커넥션 request 값 설정(key,value) 
//				con.setRequestProperty("Content-type", "application/json");
//				con.setRequestProperty("Authorization", auth_key);
//				// setRequestProperty (key,value) 다른 예시
//				// con.setRequestProperty("X-Auth-Token", AUTH_TOKEN);
//
//				// 받아온 JSON 데이터 출력 가능 상태로 변경 (default : false)
//				con.setDoOutput(true);
//
//				// 데이터 입력 스트림에 담기
//				BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
//				while(br.ready()) {
//					sb.append(br.readLine());
//				}
//				con.disconnect();
//			}catch(Exception e) {
//				e.printStackTrace();
//			}

			/**
			 *  JSON 데이터 파싱하기
			 */
			// JSONParser에 입력 스트림에 담은 JSON데이터(sb.toString())를 넣어 파싱한 다음 JSONObject로 반환한다.
			
//				result = (JSONObject) new JSONParser().parse(sb.toString());
//				
//
//			// REST API 호출 상태 출력하기
//			StringBuilder out = new StringBuilder();
//			out.append(result.get("status") +" : " + result.get("status_message") +"\n");
//
//			// JSON데이터에서 "documents"라는 JSONObject를 가져온다.
//			JSONObject meta = (JSONObject) result.get("meta");
//			// JSONObject에서 Array데이터를 get하여 JSONArray에 저장한다.
//			JSONArray array = (JSONArray) meta.get("documents");
//
//			// 데이터 출력하기 (도서 제목만 우선꺼내기 테스트용 )
//			JSONObject tmp;
//			out.append("데이터 출력하기 \n");
//			for(int i=0; i<array.size(); i++) {
//				tmp = (JSONObject) array.get(i);
//				out.append("title("+i+") :"+ tmp.get("title") +"\n");
//
//				// movies[] 배열 안에 있는 genres[] 데이터 꺼내기
////				JSONArray array2 = (JSONArray) tmp.get("genres");
////				out.append("genres("+i+"): ");
////				for(int j=0; j<array2.size(); j++) {
////					out.append(array2.get(j));
////					if(j!=array2.size()-1) {
////						out.append(",");
////					}
////				}
//				out.append("\n");
//				out.append("\n");
//			}
//			System.out.println(out.toString());
//			
//			return "detailAndComment";
//	}
}
