package a.b.c.model;

public class BookJsonVO {
	private String thumbnail;
	private String title;
	private String author;
	private String publisher;
	private String contents;
	private String isbn;
	
	public BookJsonVO() {}

	public BookJsonVO(String thumbnail, String title, String author
						,String publisher, String contents, String isbn) {
		this.thumbnail = thumbnail;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.contents = contents;
		this.isbn = isbn;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

}
