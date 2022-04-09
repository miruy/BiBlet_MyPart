package a.b.c.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import a.b.c.HomeController;
import a.b.c.model.AppraisalVO;
import a.b.c.model.BookInfoVO;
import a.b.c.model.MemberVO;
import a.b.c.model.allCommentByBookCmd;
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
	public String findAllBook(Model model) {
		List<BookInfoVO> books = appraisalService.findAllBook();

////		DB에서 불러온 데이터 확인용
//		for (BookInfoVO book : books) {
//			logger.debug("isbn:" + book.getIsbn());
//			logger.debug("Book_name:" + book.getBook_name());
//			logger.debug("Publisher:" + book.getPublisher());
//			logger.debug("Author:" + book.getAuthor());
//			// logger.debug("Produc_year:"+book.getProduc_year().toString());
//			logger.debug("Book_page:" + Integer.toString(book.getBook_page()));
//			logger.debug("Book_category:" + book.getBook_category());
//			logger.debug("Age_grade:" + book.getAge_grade());
//			logger.debug("Book_sum:" + book.getBook_sum());
//			logger.debug("Book_cover:" + book.getBook_cover());
//		}
		model.addAttribute("books", books);
		return "bookInfoList";
	}

	// 도서 상세보기 및 평가작성(form)  
	@GetMapping(value = "/read/{isbn}")
	public String bookDetail(@PathVariable("isbn") String isbn, Model model) {
		
		//도서 상세보기
		BookInfoVO book = appraisalService.bookDetail(isbn);
		if (book == null) {
			return "bookInfoList";
		}
		model.addAttribute("book", book);
		
		//해당 도서의 대한 평가 갯수
		int commentCount = appraisalService.commentCount(isbn);
//		logger.debug("count : " + commentCount);
		model.addAttribute("commentCount", commentCount);
		
		//해당 도서의 대한 모든 평가 불러오기
		List<allCommentByBookCmd> commentsByMembers = appraisalService.findAllComment(isbn);
		
//		for (allCommentByBookCmd test : commentsByMembers) {
//			logger.debug("mem_id:" + test.getMem_id());
//			logger.debug("mem_pic:" + test.getMem_pic());
//			logger.debug("star:" + Integer.toString(test.getStar()));
//			logger.debug("book_comment:" + test.getBook_comment());
//			logger.debug("start_date:" + test.getStart_date());
//			logger.debug("end_date:" + test.getEnd_date());
//		}
//		
		model.addAttribute("commentsByMembers", commentsByMembers);
		return "detailAndComment";
	}
	
	//평가 작성(insert)
	@PostMapping(value = "/read/{isbn}")
	public String writeComment(@PathVariable("isbn") String isbn, AppraisalVO appraisal, Model model) {		

		AppraisalVO comment = new AppraisalVO();
		MemberVO member = new MemberVO();
		Long mem_num = (long) 6;	//테스트용 회원 번호(현재 테이블에 6번회원까지 있음)
		member.setMem_num(mem_num);

		comment.setStar(appraisal.getStar());
//		System.out.println(appraisal.getStar());		
		comment.setBook_comment(appraisal.getBook_comment());
//		System.out.println(appraisal.getBook_comment());		
		comment.setStart_date(appraisal.getStart_date());
//		System.out.println(appraisal.getStart_date());		
		comment.setEnd_date(appraisal.getEnd_date());
//		System.out.println(appraisal.getEnd_date());		
		comment.setCo_prv(appraisal.getCo_prv());
//		System.out.println(appraisal.getCo_prv());
		comment.setMem_num(member.getMem_num());
//		System.out.println(member.getMem_num());
		comment.setIsbn(isbn);
//		System.out.println(appraisal.getIsbn());
		appraisalService.writeComment(comment);
		
		return "redirect:/AppraisalPage/read/{isbn}";
	}

//	// 해당 도서의 대한 평가 갯수
//	@RequestMapping(value = "/read/{isbn}")
//	public allCommentByBookCmd commentCount(String isbn, Model model) {
//		allCommentByBookCmd commentCount = appraisalService.commentCount(isbn);
//		model.addAttribute("commentCount", commentCount);
//		return "detailAndComment";
//	}

}
