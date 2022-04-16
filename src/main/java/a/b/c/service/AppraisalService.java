package a.b.c.service;

import java.util.List;

import a.b.c.model.AppraisalVO;
import a.b.c.model.BookShelfVO;
import a.b.c.model.allCommentByBookCmd;

public interface AppraisalService {
	void writeComment(AppraisalVO appraisal);	//평가(코멘트) 작성
	BookShelfVO insertBookShelf(BookShelfVO bookShelf); 	//평가 작성 시 도서ISBN 과 상태(독서완료)저장 
	List<allCommentByBookCmd> findAllComment(String isbn); //해당 도서의 대한 모든 평가 불러오기
	int commentCount(String isbn); 	//해당 도서의 대한 평가 갯수
}
