package a.b.c.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAndDeleteCmd {
private String isbn;
private String query;
private int star; // 별점
private String book_comment; // 코멘트(평가)
private String start_date; // 도서 구독 시작 날짜
private String end_date; // 도서 구독 완료 날짜
private String co_prv; // 코멘트 공개 여부
private Long appraisal_num;	//평가번호
}
