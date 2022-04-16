package a.b.c.model;

public class BookShelfVO {
	private Long book_status_num;	//상태번호PK
	private int book_status;	//상태 0:찜, 1:보는 중, 2:완료
	private Long mem_num;	//회원번호FK
	private String isbn;
	
	public BookShelfVO(){}
	
	public BookShelfVO(Long book_status_num, int book_status, Long mem_num, String isbn) {
		this.book_status_num = book_status_num;
		this.book_status = book_status;
		this.mem_num = mem_num;
		this.isbn = isbn;
	}

	public Long getBook_status_num() {
		return book_status_num;
	}

	public void setBook_status_num(Long book_status_num) {
		this.book_status_num = book_status_num;
	}

	public int getBook_status() {
		return book_status;
	}

	public void setBook_status(int i) {
		this.book_status = i;
	}

	public Long getMem_num() {
		return mem_num;
	}

	public void setMem_num(Long mem_num) {
		this.mem_num = mem_num;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
}
