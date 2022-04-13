package a.b.c.model;

public class AppraisalVO {

	private Long appraisal_num; // 평가 번호(시퀀스)
	private int star; // 별점
	private String book_comment; // 코멘트(평가)
	private String start_date; // 도서 구독 시작 날짜
	private String end_date; // 도서 구독 완료 날짜
	private String co_prv; // 코멘트 공개 여부
	private int book_status_num;	//상태번호FK

	public AppraisalVO() {}

	public AppraisalVO(Long appraisal_num, int star, String book_comment, String start_dater,
			String end_date, String co_prv, int book_status_num) {

		this.appraisal_num = appraisal_num;
		this.star = star;
		this.book_comment = book_comment;
		this.start_date = start_dater;
		this.end_date = end_date;
		this.co_prv = co_prv;
		this.book_status_num = book_status_num;
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

	public String getCo_prv() {
		return co_prv;
	}

	public void setCo_prv(String co_prv) {
		this.co_prv = co_prv;
	}

	public int getBook_status_num() {
		return book_status_num;
	}

	public void setBook_status_num(int book_status_num) {
		this.book_status_num = book_status_num;
	}

	@Override
	public String toString() {
		return "AppraisalVO [appraisal_num=" + appraisal_num + ", star=" + star + ", book_comment=" + book_comment
				+ ", start_date=" + start_date + ", end_date=" + end_date + ", co_prv=" + co_prv + ", book_status_num="
				+ book_status_num + "]";
	}
	
}

