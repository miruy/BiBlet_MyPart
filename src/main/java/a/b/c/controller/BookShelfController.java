package a.b.c.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import a.b.c.model.MemberVO;
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
		Long mem_num = (long) 5; // 테스트용 회원 번호
		MemberVO member = new MemberVO();

		member.setMem_num(mem_num);

		// 한 회원의 '찜' 도서 개수
		int memLikeCount = appraisalService.memLikeCount(member.getMem_num());
		// 한 회원의 '찜' 도서 isbn 검색
		List<String> likeIsbn = appraisalService.likeIsbn(mem_num);

		// 한 회원의 '보는 중' 도서 개수
		int memLeadingCount = appraisalService.memLeadingCount(member.getMem_num());
		// 한 회원의 '보는 중' 도서 isbn 검색
		List<String> leadingIsbn = appraisalService.leadingIsbn(mem_num);

		// 한 회원의 '독서 완료' 도서 개수
		int memCommentCount = appraisalService.memCommentCount(member.getMem_num());
		// 한 회원의 '독서 완료' 도서 isbn 검색
		List<String> completeIsbn = appraisalService.completeIsbn(mem_num);

//		한 회원이 작성한 모든 평가 불러오기 
		List<allCommentByBookVO> memComment = appraisalService.selectMemComment(mem_num);

		model.addAttribute("MyLikeCount", memLikeCount);
		model.addAttribute("likeIsbn", likeIsbn);

		model.addAttribute("MyLeadingCount", memLeadingCount);
		model.addAttribute("leadingIsbn", leadingIsbn);

		model.addAttribute("MyCommentCount", memCommentCount);
		model.addAttribute("MyComment", memComment);
		model.addAttribute("completeIsbn", completeIsbn);
		return "bookShelf";
	}

}
