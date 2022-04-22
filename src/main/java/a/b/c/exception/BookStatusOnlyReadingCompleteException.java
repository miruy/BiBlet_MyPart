package a.b.c.exception;

public class BookStatusOnlyReadingCompleteException extends RuntimeException{
	/**
	 * 평가 작성 시 '구동완료'만 가능
	 */
	public BookStatusOnlyReadingCompleteException(String message) {
		super(message);
	}
}
