package a.b.c.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import a.b.c.model.AppraisalVO;
import a.b.c.model.allCommentByBookCmd;
import a.b.c.repository.AppraisalDAO;

@Service
public class AppraisalServiceImpl implements AppraisalService {

	private AppraisalDAO appraisalDAO;
	
	// appraisalDAOsetter
	@Autowired
	public void setAppraisalDAO(AppraisalDAO appraisalDAO) {
		this.appraisalDAO = appraisalDAO;
	}

	// 도서 전체 목록
//	@Override
//	public List<BookInfoVO> findAllBook() {
//		return appraisalDAO.findAllBook();
//	}

	// 도서 상세보기
//	@Override
//	public BookInfoVO bookDetail(String isbn) {
//		return appraisalDAO.bookDetail(isbn);
//	}

	//평가(코멘트) 작성
	@Override
	public void writeComment(AppraisalVO appraisal) {
		appraisalDAO.writeComment(appraisal);
	}
	
	
	//해당 도서의 대한 모든 평가 불러오기
	@Override
	public List<allCommentByBookCmd> findAllComment(String isbn) {
		return appraisalDAO.findAllComment(isbn);
	}
//	
//	//해당 도서의 대한 평가 갯수
//	@Override
//	public int commentCount(String isbn) {
//		return appraisalDAO.commentCount(isbn);
//	}
}







