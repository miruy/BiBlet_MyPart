package a.b.c.repository;

import java.util.List;

import a.b.c.model.AppraisalVO;
import a.b.c.model.BookShelfVO;
import a.b.c.model.allCommentByBookVO;
import a.b.c.model.updateCommentVO;

public interface AppraisalDAO {
	void writeComment(AppraisalVO appraisal);	//평가(코멘트) insert
	BookShelfVO insertBookShelf(BookShelfVO bookShelf);	//평가 작성 시 보관함에 isbn,상태(독서완료) winsert
	List<allCommentByBookVO> findAllComment(String isbn);	//해당 도서의 대한 모든 평가 select
	int commentCount(String isbn);	//해당 도서의 대한 코멘트 갯수
	BookShelfVO selectBookShelf(BookShelfVO bookShelf);
	void deleteComment(BookShelfVO bookShelf);	//평가 삭제 
	void updateComment(updateCommentVO updateAppraisal);	//평가 수정
}
