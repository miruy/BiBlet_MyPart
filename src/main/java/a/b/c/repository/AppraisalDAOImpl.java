package a.b.c.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import a.b.c.HomeController;
import a.b.c.model.AppraisalVO;
import a.b.c.model.allCommentByBookCmd;

@Repository
public class AppraisalDAOImpl implements AppraisalDAO {

	// DB데이터 확인용 Debug코드
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	// 상세,평가 페이지에서 사용되는 기능을 모아놓은 인터페이스를 구현한 클래스
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public AppraisalDAOImpl(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	// 도서 전체 목록
//	@Override
//	public List<BookInfoVO> findAllBook() {
//		return sqlSessionTemplate.selectList("findAllBook");
//	}

	// 도서 상세보기
//	@Override
//	public BookInfoVO bookDetail(String isbn) {
//		return sqlSessionTemplate.selectOne("bookDetail", isbn);
//	}

	// 평가(코멘트) 작성
	@Override
	public void writeComment(AppraisalVO appraisal) {
		sqlSessionTemplate.insert("writeComment", appraisal);
	}

//	//해당 도서의 대한 모든 평가 불러오기
	@Override
	public List<allCommentByBookCmd> findAllComment(String isbn) {
		return sqlSessionTemplate.selectList("findAllComment", isbn);
	}
//
//	//해당 도서의 대한 평가 갯수
//	@Override
//	public int commentCount(String isbn) {
//		return sqlSessionTemplate.selectOne("commentCount", isbn);
//	}
}
