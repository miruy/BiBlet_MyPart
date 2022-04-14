package a.b.c.repository;

import java.util.List;

import a.b.c.model.AppraisalVO;
import a.b.c.model.BookShelfVO;
import a.b.c.model.allCommentByBookCmd;

public interface AppraisalDAO {
//	public List<BookInfoVO> findAllBook();	//전체 도서 목록 
//	public BookInfoVO bookDetail(String isbn);	//도서 상세보기
	public void writeComment(AppraisalVO appraisal);	//평가(코멘트) insert
	public void insertBookShelf(BookShelfVO bookShelf);	//평가 작성 시 보관함에 isbn,상태(독서완료) winsert
	public List<allCommentByBookCmd> findAllComment(String isbn);	//해당 도서의 대한 모든 평가 select
//	public int commentCount(String isbn);	//해당 도서의 대한 코멘트 갯수
}
