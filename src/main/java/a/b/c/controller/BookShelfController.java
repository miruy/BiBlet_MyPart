package a.b.c.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import a.b.c.model.BookShelfVO;
import a.b.c.model.allCommentByBookVO;
import a.b.c.service.AppraisalService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor // @Autowried
public class BookShelfController {

	private final AppraisalService appraisalService;

	/**
	 * 마이페이지에서 보관함 이동
	 */
	@GetMapping("/MyPage")
	public String intoBookShelf() {
		return "testMyPage";
	}

	/**
	 * 보관함
	 */
	@GetMapping("/BookShelf")
	public String BookShelf(Model model) {
		
		// 테스트 하기 전마다 회원 등록 후 평가작성을 하지 않은 새로운 회원번호로 진행해야함
		Long mem_num = (long) 5; // 테스트용 회원 번호(현재 테이블에 6번회원까지 있음)

//		한 회원이 작성한 모든 평가 불러오기 
		List<allCommentByBookVO> memComment = appraisalService.selectMemComment(mem_num);
		
		model.addAttribute("MyComment", memComment);
		return "bookShelf";
	}

}
