package a.b.c.service;

import java.util.List;

import org.springframework.stereotype.Service;

import a.b.c.model.AppraisalVO;
import a.b.c.model.BookShelfVO;
import a.b.c.model.allCommentByBookVO;
import a.b.c.model.updateCommentVO;
import a.b.c.repository.AppraisalDAO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppraisalServiceImpl implements AppraisalService {

	private final AppraisalDAO appraisalDAO;
	
	//평가(코멘트) 작성
	@Override
	public void writeComment(AppraisalVO appraisal) {
		appraisalDAO.writeComment(appraisal);
	}
	
	//해당 도서의 대한 모든 평가 불러오기
	@Override
	public List<allCommentByBookVO> findAllComment(String isbn) {
		return appraisalDAO.findAllComment(isbn);
	}
	
	//평가 작성 시 도서isbn과 상태(독서완료) 저장
	@Override
	public BookShelfVO insertBookShelf(BookShelfVO bookShelf) {
		return appraisalDAO.insertBookShelf(bookShelf);
	}
	
	@Override
	public BookShelfVO selectBookShelf(BookShelfVO bookShelf) {
		return appraisalDAO.selectBookShelf(bookShelf);
	}
	
	//해당 도서의 대한 평가 갯수
	@Override
	public int commentCount(String isbn) {
		return appraisalDAO.commentCount(isbn);
	}
	
	@Override
	public void deleteComment(BookShelfVO bookShelf) {
		appraisalDAO.deleteComment(bookShelf);
	}
	
	@Override
	public void updateComment(updateCommentVO updateAppraisal) {
		appraisalDAO.updateComment(updateAppraisal);
	}
}







