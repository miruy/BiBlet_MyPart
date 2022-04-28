package a.b.c.service;

import java.util.List;

import a.b.c.model.AppraisalVO;
import a.b.c.model.BookShelfVO;
import a.b.c.model.DeleteCmd;
import a.b.c.model.UpdateCmd;
import a.b.c.model.allCommentByBookVO;

public interface AppraisalService {
	void writeComment(AppraisalVO appraisal);	//평가 작성
	
	BookShelfVO insertBookShelf(BookShelfVO bookShelf); 	//독서 상태 삽입
	BookShelfVO selectBookShelf(BookShelfVO bookShelf);		//독서 상태 호출
	
	int commentCount(String isbn); 		//해당 도서의 대한 평가 개수 호출
	List<allCommentByBookVO> findAllComment(String isbn); 	//해당 도서의 대한 모든 평가 호출
	void updateComment(UpdateCmd updateComment); 	//평가 수정
	void deleteComment(DeleteCmd deleteCmd);	//평가 삭제 
	
	int memCommentCount(Long mem_num);	//한 회원의 '독서 완료' 도서 개수 호출
	List<allCommentByBookVO> selectMemComment(Long mem_num);	//한 회원이 작성한 모든 평가 호출
	List<String> completeIsbn(Long mem_num);	// 한 회원의 '독서 완료' 도서 isbn 호출
	
	int memLikeCount(Long mem_num);	//한 회원의 '찜' 도서 개수 호출
	List<String> likeIsbn(Long mem_num);	// 한 회원의 '찜' 도서 isbn 호출
	
	int memLeadingCount(Long mem_num);	//한 회원의 '보는 중' 도서 개수 호출
	List<String> leadingIsbn(Long mem_num);	// 한 회원의 '보는 중' 도서 isbn 호출
}
