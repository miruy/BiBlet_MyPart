package a.b.c.model;

import java.util.Date;

public class BookInfoVO {

	private String isbn; // 일련번호(PK)
	private String book_name; // 도서 이름
	private String publisher; // 출판사
	private String author; // 저자
	private Date produc_year; // 제작년도
	private int book_page; // 총 페이지 수
	private String book_category; // 도서 종류
	private String age_grade; // 연령 등급
	private String book_sum; // 도서 줄거리
	private String book_cover; // 도서표지, 사용자에게 보여지는 image이름(ex:book1.jpg)

	public BookInfoVO() {}

	public BookInfoVO(String isbn, String book_name, String publisher, String author, Date produc_year,
			int book_page, String book_category, String age_grade, String book_sum, String book_cover) {

		this.isbn = isbn;
		this.book_name = book_name;
		this.publisher = publisher;
		this.author = author;
		this.produc_year = produc_year;
		this.book_page = book_page;
		this.book_category = book_category;
		this.age_grade = age_grade;
		this.book_sum = book_sum;
		this.book_cover = book_cover;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getBook_name() {
		return book_name;
	}

	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getProduc_year() {
		return produc_year;
	}

	public void setProduc_year(Date produc_year) {
		this.produc_year = produc_year;
	}

	public int getBook_page() {
		return book_page;
	}

	public void setBook_page(int book_page) {
		this.book_page = book_page;
	}

	public String getBook_category() {
		return book_category;
	}

	public void setBook_category(String book_category) {
		this.book_category = book_category;
	}

	public String getAge_grade() {
		return age_grade;
	}

	public void setAge_grade(String age_grade) {
		this.age_grade = age_grade;
	}

	public String getBook_sum() {
		return book_sum;
	}

	public void setBook_sum(String book_sum) {
		this.book_sum = book_sum;
	}

	public String getBook_cover() {
		return book_cover;
	}

	public void setBook_cover(String book_cover) {
		this.book_cover = book_cover;
	}

}
