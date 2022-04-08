package a.b.c.controller;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import a.b.c.HomeController;
import a.b.c.model.AppraisalVO;
import a.b.c.model.BookInfoVO;
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

	// 도서상세보기 및 평가(코멘트)작성
	@RequestMapping(value = "/read/{isbn}")
	public String detailAndwriteComment(@PathVariable("isbn") String isbn, AppraisalVO appraisals, Model model) {
		Long mem_num = (long) 1; // 테스트용 회원번호 삽입

		// 도서 상세보기
		BookInfoVO book = appraisalService.bookDetail(isbn);
		if (book == null) {
			return "bookInfoList";
		}
		model.addAttribute("book", book);

		
		// 해당 도서의 대한 모든 평가 불러오기
		List<AppraisalVO> comments = appraisalService.findAllComment(isbn);
		model.addAttribute("comments", comments);
		
		for(AppraisalVO test : comments) {
			logger.debug("isbn:" + test.getIsbn());
		}
		
		// 평가 작성
		AppraisalVO appraisal = new AppraisalVO();
		MemberVO member = new MemberVO();
		member.setMem_num(mem_num);

		appraisal.setStar(appraisals.getStar());
//		System.out.println(appraisal.getStar());		
		appraisal.setBook_comment(appraisals.getBook_comment());
//		System.out.println(appraisal.getBook_comment());		
		appraisal.setStart_date(appraisals.getStart_date());
//		System.out.println(appraisal.getStart_date());		
		appraisal.setEnd_date(appraisals.getEnd_date());
//		System.out.println(appraisal.getEnd_date());		
		appraisal.setCo_prv(appraisals.getCo_prv());
//		System.out.println(appraisal.getCo_prv());
		appraisal.setMem_num(member.getMem_num());
//		System.out.println(member.getMem_num());
		appraisal.setIsbn(isbn);
//		System.out.println(appraisal.getIsbn());
		appraisalService.writeComment(appraisal);

		return "detailAndComment";
	}

	// 해당 도서에 작성된 모든 평가 불러오기
//	@RequestMapping(value = "/read/{isbn}")
//	public String findAllComment(String isbn, Model model) {
//		List<AppraisalVO> comments = appraisalService.findAllComment(isbn);
//		model.addAttribute("comments", comments);
//		return "detailAndComment";
//	}

}
