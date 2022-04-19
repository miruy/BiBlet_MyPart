package a.b.c.service;

import java.util.List;

import a.b.c.model.AppraisalVO;
import a.b.c.model.BookShelfVO;
import a.b.c.model.DeleteCmd;
import a.b.c.model.UpdateCmd;
import a.b.c.model.allCommentByBookVO;

public interface AppraisalService {
	void writeComment(AppraisalVO appraisal);	//평가(코멘트) 작성
	BookShelfVO insertBookShelf(BookShelfVO bookShelf); 	//평가 작성 시 도서ISBN 과 상태(독서완료)저장 
	BookShelfVO selectBookShelf(BookShelfVO bookShelf);
	List<allCommentByBookVO> findAllComment(String isbn); //해당 도서의 대한 모든 평가 불러오기
	int commentCount(String isbn); 	//해당 도서의 대한 평가 갯수
	void deleteComment(DeleteCmd deleteComment);	//평가 삭제 
	void updateComment(UpdateCmd updateComment); //평가 수정
}
