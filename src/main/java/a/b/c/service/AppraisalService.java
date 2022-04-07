package a.b.c.service;

import java.util.List;

import a.b.c.model.AppraisalVO;
import a.b.c.model.BookInfoVO;
import a.b.c.model.MemberVO;

public interface AppraisalService {
	public List<BookInfoVO> findAllBook();	//전체 도서 목록
	public BookInfoVO bookDetail(String isbn);	//도서 상세보기
	public void writeComment(AppraisalVO appraisal);	//평가(코멘트) 작성
	
	public MemberVO memberInfo(Long mem_num); //회원(한명) 정보 보기 
}
