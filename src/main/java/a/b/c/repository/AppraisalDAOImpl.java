package a.b.c.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import a.b.c.model.AppraisalVO;
import a.b.c.model.BookShelfVO;
import a.b.c.model.DeleteCmd;
import a.b.c.model.UpdateCmd;
import a.b.c.model.allCommentByBookVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@RequiredArgsConstructor
public class AppraisalDAOImpl implements AppraisalDAO {

	private final SqlSessionTemplate sqlSessionTemplate;

	// 평가 작성
	@Override
	public void writeComment(AppraisalVO appraisal) {
		sqlSessionTemplate.insert("writeComment", appraisal);
	}

	// 독서 상태 삽입 및 해당 독서 상태 바로 호출
	@Override
	public BookShelfVO insertBookShelf(BookShelfVO bookShelf) {
		sqlSessionTemplate.insert("insertBookShelf", bookShelf);
		return selectBookShelf(bookShelf);
	}

	// 독서 상태 호출
	@Override
	public BookShelfVO selectBookShelf(BookShelfVO bookShelf) {
		return sqlSessionTemplate.selectOne("selectBookShelf", bookShelf);
	}

	// 해당 도서의 대한 모든 평가 호출
	@Override
	public List<allCommentByBookVO> findAllComment(String isbn) {
		return sqlSessionTemplate.selectList("findAllComment", isbn);
	}

	// 해당 도서의 대한 평가 개수 호출
	@Override
	public int commentCount(String isbn) {
		return sqlSessionTemplate.selectOne("commentCount", isbn);
	}

	// 평가 삭제
	@Override
	public void deleteComment(DeleteCmd deleteCmd) {
		sqlSessionTemplate.delete("deleteComment", deleteCmd);
	}

	// 평가 수정
	@Override
	public void updateComment(UpdateCmd updateAppraisal) {
		sqlSessionTemplate.update("updateComment", updateAppraisal);
	}

	// 한 회원이 작성한 모든 평가 호출
	@Override
	public List<allCommentByBookVO> selectMemComment(Long mem_num) {
		return sqlSessionTemplate.selectList("selectMemComment", mem_num);
	}

	//	한 회원이 작성한 모든 평가 개수 호출
	@Override
	public int memCommentCount(Long mem_num) {
		return sqlSessionTemplate.selectOne("memCommentCount", mem_num);
	}
	
	// 해당 도서의 대한 찜 개수 호출
	@Override
	public int memLikeCount(Long mem_num) {
		return sqlSessionTemplate.selectOne("memLikeCount", mem_num);
	}
	
	//해당 도서의 대한 보는 중 개수 호출
	@Override
	public int memLeadingCount(Long mem_num) {
		return sqlSessionTemplate.selectOne("memLeadingCount", mem_num);
	}
}
