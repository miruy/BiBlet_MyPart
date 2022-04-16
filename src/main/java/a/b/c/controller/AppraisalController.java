package a.b.c.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import a.b.c.model.AppraisalVO;
import a.b.c.model.BookShelfVO;
import a.b.c.model.CommentCmd;
import a.b.c.model.MemberVO;
import a.b.c.model.allCommentByBookCmd;
import a.b.c.service.AppraisalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor // @Autowried
public class AppraisalController {
	
	private final AppraisalService appraisalService;

	/**
	 *  도서 검색 
	 */
	@GetMapping("/list")
	public String findAllBook(
			@RequestParam(value = "query", required = false) String query, 
			Model model) {
		
		if (query != null) {
			model.addAttribute("query", query);
		}
		
		return "bookInfoList";
	}

	
	/**
	 *  도서 상세보기
	 *   - 해당 도서의 대한 모든 평가 추출
	 */
	@GetMapping("/read/{isbn}")
	public String bookDetailAndComment(
			@RequestParam(required = false) String query, 
			@PathVariable String isbn,
			Model model) {
		
		// 해당 도서의 대한 평가 개수
		int commentCount = appraisalService.commentCount(isbn);		

		// 해당 도서의 대한 모든 평가 불러오기
		List<allCommentByBookCmd> commentsByMembers = appraisalService.findAllComment(isbn);

		model.addAttribute("query", query.split(",")[0]);
		model.addAttribute("commentCount", commentCount);
		model.addAttribute("commentsByMembers", commentsByMembers);

		return "detailAndComment";
	}

	/**
	 * 도서 상세보기
	 *  - 평가 저장
	 */
	@PostMapping("/read/{isbn}")
	public String writeComment(@ModelAttribute("appraisal") CommentCmd commentCmd, Model model)
			throws UnsupportedEncodingException {
		
		AppraisalVO appraisal = new AppraisalVO();
		BookShelfVO bookShelf = new BookShelfVO();

		//테스트 하기 전마다 회원 등록 후 평가작성을 하지 않은 새로운 회원번호로 진행해야함 
		MemberVO member = new MemberVO();
		Long mem_num = (long) 12; // 테스트용 회원 번호(현재 테이블에 6번회원까지 있음)
		member.setMem_num(mem_num);
		
		bookShelf.setBook_status(commentCmd.getOption());	
		bookShelf.setMem_num(member.getMem_num());
		bookShelf.setIsbn(commentCmd.getIsbn());
		bookShelf = appraisalService.insertBookShelf(bookShelf);

		appraisal.setStar(commentCmd.getStar());
		appraisal.setBook_comment(commentCmd.getBook_comment());
		appraisal.setStart_date(commentCmd.getStart_date());
		appraisal.setEnd_date(commentCmd.getEnd_date());
		appraisal.setCo_prv(commentCmd.getCo_prv());
		appraisal.setBook_status_num(bookShelf.getBook_status_num());
		
		appraisalService.writeComment(appraisal);
		
		String encodedParam = URLEncoder.encode(commentCmd.getQuery(), "UTF-8");
		
		return "redirect:/read/" + commentCmd.getIsbn() + "?query=" + encodedParam;
	}
}
