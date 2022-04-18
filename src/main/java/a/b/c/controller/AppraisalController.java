package a.b.c.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import a.b.c.model.AppraisalVO;
import a.b.c.model.BookShelfVO;
import a.b.c.model.CommentCmd;
import a.b.c.model.MemberVO;
import a.b.c.model.UpdateAndDeleteCmd;
import a.b.c.model.allCommentByBookCmd;
import a.b.c.service.AppraisalService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor // @Autowried
public class AppraisalController {

	private final AppraisalService appraisalService;

	/**
	 * 도서 검색
	 */
	@GetMapping("/list")
	public String findAllBook(@RequestParam(value = "query", required = false) String query, Model model) {

		if (query != null) {
			model.addAttribute("query", query);
		}

		return "bookInfoList";
	}

	/**
	 * 도서 상세보기 - 해당 도서의 대한 모든 평가 추출
	 */
	@GetMapping("/read/{isbn}")
	public String bookDetailAndComment(@RequestParam(required = false) String query, @PathVariable String isbn,
			Model model) {

		// 해당 도서의 대한 평가 개수
		int commentCount = appraisalService.commentCount(isbn);

		// 해당 도서의 대한 모든 평가 불러오기
		List<allCommentByBookCmd> commentsByMembers = appraisalService.findAllComment(isbn);

		model.addAttribute("query", query.split(",")[0]);
		model.addAttribute("commentCount", commentCount);
		model.addAttribute("commentsByMembers", commentsByMembers);

		// 비밀번호 인증 및 평가 삭제
//		bookshelf를 파라미터로 넘겨줘야함

		return "detailAndComment";
	}

	/**
	 * 도서 상세보기
	 */
	@PostMapping("/read")
	public String writeComment(int actionFlag, @ModelAttribute("appraisal") CommentCmd commentCmd,
			@ModelAttribute("updateAndDeleteCmd") UpdateAndDeleteCmd updateAndDeleteCmd, Model model) throws UnsupportedEncodingException {
		System.out.println("actionFlag:" + actionFlag);
		AppraisalVO appraisal = new AppraisalVO();
		BookShelfVO bookShelf = new BookShelfVO();
		String encodedParam = URLEncoder.encode(commentCmd.getQuery(), "UTF-8");

		// 테스트 하기 전마다 회원 등록 후 평가작성을 하지 않은 새로운 회원번호로 진행해야함
		MemberVO member = new MemberVO();
		Long mem_num = (long) 14; // 테스트용 회원 번호(현재 테이블에 6번회원까지 있음)
		member.setMem_num(mem_num);

		String redirectUrl = "";
		if(actionFlag == 1) {
			redirectUrl = writeComment(commentCmd, mem_num) + encodedParam;
		}else if(actionFlag == 2) {
			redirectUrl = deleteComment(updateAndDeleteCmd, mem_num) + encodedParam;
		}else if(actionFlag == 3) {
			redirectUrl = updateComment(updateAndDeleteCmd, mem_num) + encodedParam;
		}

		return redirectUrl;

	}
	
	/**
	 * 평가 저장
	 */
	private String writeComment(CommentCmd commentCmd, Long mem_num) {
		AppraisalVO appraisal = new AppraisalVO();
		BookShelfVO bookShelf = new BookShelfVO();
		
		bookShelf.setBook_status(commentCmd.getOption());
		bookShelf.setMem_num(mem_num);
		bookShelf.setIsbn(commentCmd.getIsbn());
		bookShelf = appraisalService.insertBookShelf(bookShelf);

		appraisal.setStar(commentCmd.getStar());
		appraisal.setBook_comment(commentCmd.getBook_comment());
		appraisal.setStart_date(commentCmd.getStart_date());
		appraisal.setEnd_date(commentCmd.getEnd_date());
		appraisal.setCo_prv(commentCmd.getCo_prv());
		appraisal.setBook_status_num(bookShelf.getBook_status_num());

		appraisalService.writeComment(appraisal);
		
		return "redirect:/read/" + commentCmd.getIsbn() + "?query=";
	}
	
	/**
	 * 평가 수정
	 */
	public String updateComment(@ModelAttribute("updateAndDeleteCmd") UpdateAndDeleteCmd updateAndDeleteCmd, Long mem_num) {
		BookShelfVO bookShelf = new BookShelfVO();

		bookShelf.setIsbn(updateAndDeleteCmd.getIsbn());
		bookShelf.setMem_num(mem_num);

		appraisalService.updateComment(bookShelf);
		System.out.println("평가 수정 성공");
		return "redirect:/read/" + updateAndDeleteCmd.getIsbn() + "?query=";
	}

	/**
	 * 평가 삭제
	 */
	public String deleteComment(@ModelAttribute("updateAndDeleteCmd") UpdateAndDeleteCmd updateAndDeleteCmd, Long mem_num) throws UnsupportedEncodingException {
		BookShelfVO bookShelf = new BookShelfVO();

		bookShelf.setIsbn(updateAndDeleteCmd.getIsbn());
		bookShelf.setMem_num(mem_num);

		appraisalService.deleteComment(bookShelf);
		
		return "redirect:/read/" + updateAndDeleteCmd.getIsbn() + "?query=";
	}

}
