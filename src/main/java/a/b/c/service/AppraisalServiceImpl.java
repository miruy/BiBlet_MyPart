package a.b.c.service;

import java.util.List;

import org.springframework.stereotype.Service;

import a.b.c.model.AppraisalVO;
import a.b.c.model.BookShelfVO;
import a.b.c.model.DeleteCmd;
import a.b.c.model.UpdateCmd;
import a.b.c.model.allCommentByBookVO;
import a.b.c.repository.AppraisalDAO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppraisalServiceImpl implements AppraisalService {

	private final AppraisalDAO appraisalDAO;
	
	// 평가 작성
	@Override
	public void writeComment(AppraisalVO appraisal) {
		appraisalDAO.writeComment(appraisal);
	}
	
	// 해당 도서의 대한 모든 평가 호출
	@Override
	public List<allCommentByBookVO> findAllComment(String isbn) {
		return appraisalDAO.findAllComment(isbn);
	}
	
	// 독서 상태 삽입
	@Override
	public BookShelfVO insertBookShelf(BookShelfVO bookShelf) {
		return appraisalDAO.insertBookShelf(bookShelf);
	}
	
	// 독서 상태 호출
	@Override
	public BookShelfVO selectBookShelf(BookShelfVO bookShelf) {
		return appraisalDAO.selectBookShelf(bookShelf);
	}
	
	// 해당 도서의 대한 평가 개수 호출
	@Override
	public int commentCount(String isbn) {
		return appraisalDAO.commentCount(isbn);
	}
	
	// 평가 삭제
	@Override
	public void deleteComment(DeleteCmd deleteCmd) {
		appraisalDAO.deleteComment(deleteCmd);
	}
	
	// 평가 수정
	@Override
	public void updateComment(UpdateCmd updateComment) {
		appraisalDAO.updateComment(updateComment);
	}
	
	//한 회원이 작성한 모든 평가 호출
	@Override
	public List<allCommentByBookVO> selectMemComment(Long mem_num){
		return appraisalDAO.selectMemComment(mem_num);
	}
	
	//한 회원이 작성한 모든 평가 개수 호출
	@Override
	public int memCommentCount(Long mem_num) {
		return appraisalDAO.memCommentCount(mem_num);
	}
	
	// 해당 도서의 대한 찜 개수 호출
	@Override
	public int memLikeCount(Long mem_num) {
		return appraisalDAO.memLikeCount(mem_num);
	}

	//해당 도서의 대한 보는 중 개수 호출
	@Override
	public int memLeadingCount(Long mem_num) {
		return appraisalDAO.memLeadingCount(mem_num);
	}

}







