package a.b.c.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import a.b.c.exception.BookStatusOnlyReadingCompleteException;
import a.b.c.model.AppraisalVO;
import a.b.c.model.BookShelfVO;
import a.b.c.model.BookStatusCmd;
import a.b.c.model.DeleteCmd;
import a.b.c.model.InsertCmd;
import a.b.c.model.MemberVO;
import a.b.c.model.PassCheckCmd;
import a.b.c.model.UpdateCmd;
import a.b.c.model.allCommentByBookVO;
import a.b.c.service.AppraisalService;
import a.b.c.utils.OnlyReadingComplateValidator;
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

		return "selectBook";
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
		List<allCommentByBookVO> commentsByMembers = appraisalService.findAllComment(isbn);

		model.addAttribute("query", query.split(",")[0]);
		model.addAttribute("commentCount", commentCount);
		model.addAttribute("commentsByMembers", commentsByMembers);

		return "detailAndComment";
	}

	/**
	 * 도서 상세보기
	 */
	@PostMapping("/read")
	public String writeComment(int actionFlag, Model model, RedirectAttributes rttr,
			/*@ModelAttribute("bookStatusCmd") BookStatusCmd bookStatusCmd,Errors errors,*/
			@ModelAttribute("passCheckCmd") PassCheckCmd passCheckCmd, @ModelAttribute("insertCmd") InsertCmd insertCmd,
			@ModelAttribute("deleteCmd") DeleteCmd deleteCmd, @ModelAttribute("updateCmd") UpdateCmd updateCmd,
			HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {

		
		response.setCharacterEncoding("UTF-8"); 
		response.setContentType("text/html; charset=UTF-8");
		String btn = request.getParameter("button");
		
		
		// 테스트 하기 전마다 회원 등록 후 평가작성을 하지 않은 새로운 회원번호로 진행해야함
		MemberVO member = new MemberVO();
		Long mem_num = (long) 15; // 테스트용 회원 번호(현재 테이블에 6번회원까지 있음)
		member.setMem_num(mem_num);

		String redirectUrl = "";
		if (actionFlag == 1) {
			
			if(btn.equals("확인")) {
				
				System.out.println("확인버튼");
				return insertBookStatus(insertCmd, rttr, mem_num);
				
			}else if(btn.equals("등록")) {
				System.out.println("등록버튼");
			}

			String encodedParam = URLEncoder.encode(insertCmd.getQuery(), "UTF-8");
			redirectUrl = writeComment(insertCmd, mem_num) + encodedParam;

			return redirectUrl;

		} else if (actionFlag == 2) {
			String encodedParam = URLEncoder.encode(deleteCmd.getQuery(), "UTF-8");

			if (deleteCmd.getMem_pass().equals(deleteCmd.getPassCheck())) {
				redirectUrl = deleteComment(deleteCmd, mem_num) + encodedParam;
				return redirectUrl;
			}

		} else if (actionFlag == 3) {
			String encodedParam = URLEncoder.encode(passCheckCmd.getQuery(), "UTF-8");

			if (passCheckCmd.getMem_pass().equals(passCheckCmd.getPassCheck())) {
				rttr.addFlashAttribute("passCheckTrue", passCheckCmd.getPassCheck());

				return "redirect:/read/" + passCheckCmd.getIsbn() + "?query=" + encodedParam;
			}

		} else if (actionFlag == 4) {
			String encodedParam = URLEncoder.encode(updateCmd.getQuery(), "UTF-8");
			redirectUrl = updateComment(updateCmd, mem_num) + encodedParam;

			return redirectUrl;

		}
//		if (actionFlag == 5) {
//			return insertBookStatus(bookStatusCmd, errors, rttr, mem_num);
//		}
		return redirectUrl;
	}

	/**
	 * 독서 상태 삽입
	 */
	private String insertBookStatus(InsertCmd insertCmd, RedirectAttributes rttr, Long mem_num)
			throws UnsupportedEncodingException {

//		new OnlyReadingComplateValidator().validate(insertCmd, errors);

		String encodedParam = URLEncoder.encode(insertCmd.getQuery(), "UTF-8");
		BookShelfVO bookShelf = new BookShelfVO();
		insertCmd.setIsbn(insertCmd.getIsbn().substring(0, 10));
		
//		if (errors.hasErrors()) {
//			return "errorTest";
//		}
//		try {
			
				System.out.println("option이 1아니면 0임");
				bookShelf.setBook_status(insertCmd.getOption());
				bookShelf.setMem_num(mem_num);
				bookShelf.setIsbn(insertCmd.getIsbn());

				appraisalService.insertBookShelf(bookShelf);
			
//		} CATCH (BOOKSTATUSONLYREADINGCOMPLETEEXCEPTION E) {
//			ERRORS.REJECTVALUE("BOOKSTATUS", "READINGCOMPLATE");
//		}
		return "redirect:/read/" + insertCmd.getIsbn() + "?query=" + encodedParam;
	}

	/**
	 * 평가 저장
	 */
	private String writeComment(InsertCmd insertCmd, Long mem_num) {
		AppraisalVO appraisal = new AppraisalVO();
		BookShelfVO bookShelf = new BookShelfVO();
		insertCmd.setIsbn(insertCmd.getIsbn().substring(0, 10));

		// 독서 상태가 2가 아닌 경우 에러

//		bookShelf.setBook_status(insertCmd.getOption());
//		bookShelf.setMem_num(mem_num);
//		bookShelf.setIsbn(insertCmd.getIsbn());
		bookShelf = appraisalService.selectBookShelf(bookShelf);
		

		appraisal.setStar(insertCmd.getStar());
		appraisal.setBook_comment(insertCmd.getBook_comment());
		appraisal.setStart_date(insertCmd.getStart_date());
		appraisal.setEnd_date(insertCmd.getEnd_date());
		appraisal.setCo_prv(insertCmd.getCo_prv());
		appraisal.setBook_status_num(bookShelf.getBook_status_num());

		appraisalService.writeComment(appraisal);

		return "redirect:/read/" + insertCmd.getIsbn() + "?query=";
	}

	/**
	 * 평가 수정
	 */
	public String updateComment(UpdateCmd updateCmd, Long mem_num) {
		UpdateCmd updateAppraisal = new UpdateCmd();
		updateCmd.setIsbn(updateCmd.getIsbn().substring(0, 10));

		updateAppraisal.setMem_num(mem_num);
		updateAppraisal.setIsbn(updateCmd.getIsbn());
		updateAppraisal.setAppraisal_num(updateCmd.getAppraisal_num());
		updateAppraisal.setStar(updateCmd.getStar());
		updateAppraisal.setBook_comment(updateCmd.getBook_comment());
		updateAppraisal.setStart_date(updateCmd.getStart_date());
		updateAppraisal.setEnd_date(updateCmd.getEnd_date());
		updateAppraisal.setCo_prv(updateCmd.getCo_prv());
		updateAppraisal.setBook_status_num(updateCmd.getBook_status_num());
		System.out.println("뷰에서 가져온 상태번호" + updateAppraisal.getBook_status_num());

		appraisalService.updateComment(updateAppraisal);
		System.out.println("평가 수정 성공");
		return "redirect:/read/" + updateCmd.getIsbn() + "?query=";
	}

	/**
	 * 평가 삭제
	 */
	public String deleteComment(DeleteCmd deleteCmd, Long mem_num) throws UnsupportedEncodingException {
		DeleteCmd deleteComment = new DeleteCmd();
		deleteCmd.setIsbn(deleteCmd.getIsbn().substring(0, 10));

		deleteComment.setIsbn(deleteCmd.getIsbn());
		deleteComment.setMem_num(mem_num);
		deleteComment.setBook_status_num(deleteCmd.getBook_status_num());
		deleteComment.setAppraisal_num(deleteCmd.getAppraisal_num());

		appraisalService.deleteComment(deleteComment);

		return "redirect:/read/" + deleteCmd.getIsbn() + "?query=";
	}

}
