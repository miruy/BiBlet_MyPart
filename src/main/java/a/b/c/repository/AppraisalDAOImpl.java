package a.b.c.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import a.b.c.model.AppraisalVO;
import a.b.c.model.BookShelfVO;
import a.b.c.model.allCommentByBookCmd;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@RequiredArgsConstructor
public class AppraisalDAOImpl implements AppraisalDAO {

	private final SqlSessionTemplate sqlSessionTemplate;

	// 평가(코멘트) 작성
	@Override
	public void writeComment(AppraisalVO appraisal) {
		sqlSessionTemplate.insert("writeComment", appraisal);
	}
	
	//평가 작성 시 도서 ISBN과 상태(독서완료) 작성
	@Override
	public BookShelfVO insertBookShelf(BookShelfVO bookShelf) {
			sqlSessionTemplate.insert("insertBookShelf", bookShelf);
		return selectBookShelf(bookShelf);
	}
	
	@Override
	public BookShelfVO selectBookShelf(BookShelfVO bookShelf) {
		return sqlSessionTemplate.selectOne("selectBookShelf", bookShelf);
	}
	

	//해당 도서의 대한 모든 평가 불러오기
	@Override
	public List<allCommentByBookCmd> findAllComment(String isbn) {
		return sqlSessionTemplate.selectList("findAllComment", isbn);
	}

	//해당 도서의 대한 평가 갯수
	@Override
	public int commentCount(String isbn) {
		return sqlSessionTemplate.selectOne("commentCount", isbn);
	}
	
	//평가 삭제 
	@Override
	public void deleteComment(BookShelfVO bookShelf) {
		sqlSessionTemplate.delete("deleteComment", bookShelf);
	}
	
	@Override
	public void updateComment(BookShelfVO bookShelf) {
		sqlSessionTemplate.update("updateComment", bookShelf);
	}
}
