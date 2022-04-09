package a.b.c.model;

public class allCommentByBookCmd {
	private Long appraisal_num;	// 평가 번호
	private int star; // 별점
	private String book_comment; // 코멘트(평가)
	private String start_date; // 도서 구독 시작 날짜
	private String end_date; // 도서 구독 완료 날짜
	private String mem_id; // 회원 아이디
	private String mem_pic; // 회원 프로필 사진
	
	public allCommentByBookCmd() {}

	public allCommentByBookCmd(Long appraisal_num, int star, String book_comment, String start_dater,
			String end_date, String mem_id, String mem_pic) {
		this.appraisal_num = appraisal_num;
		this.star = star;
		this.book_comment = book_comment;
		this.start_date = start_dater;
		this.end_date = end_date;
		this.mem_id = mem_id;
		this.mem_pic = mem_pic;
	}

	public Long getAppraisal_num() {
		return appraisal_num;
	}

	public void setAppraisal_num(Long appraisal_num) {
		this.appraisal_num = appraisal_num;
	}

	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		this.star = star;
	}

	public String getBook_comment() {
		return book_comment;
	}

	public void setBook_comment(String book_comment) {
		this.book_comment = book_comment;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public String getMem_pic() {
		return mem_pic;
	}

	public void setMem_pic(String mem_pic) {
		this.mem_pic = mem_pic;
	}
}
