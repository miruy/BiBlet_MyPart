package a.b.c.service;

import java.util.List;

import a.b.c.model.AppraisalVO;
import a.b.c.model.BookShelfVO;
import a.b.c.model.allCommentByBookCmd;

public interface AppraisalService {
//	public List<BookInfoVO> findAllBook();	//전체 도서 목록
//	public BookInfoVO bookDetail(String isbn);	//도서 상세보기
	public void writeComment(AppraisalVO appraisal);	//평가(코멘트) 작성
	public void insertBookShelf(BookShelfVO bookShelf); 	//평가 작성 시 도서ISBN 과 상태(독서완료)저장 
	public List<allCommentByBookCmd> findAllComment(String isbn); //해당 도서의 대한 모든 평가 불러오기
//	public int commentCount(String isbn); 	//해당 도서의 대한 평가 갯수
}
