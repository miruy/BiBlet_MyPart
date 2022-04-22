package a.b.c.utils;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import a.b.c.model.BookStatusCmd;

public class OnlyReadingComplateValidator implements Validator{
	
	@Override
	public boolean supports(Class<?> arg0) {
		return BookStatusCmd.class.isAssignableFrom(arg0);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		BookStatusCmd bookStatus = (BookStatusCmd) target;
		
		if (bookStatus.getOption() != 2) {
			errors.rejectValue("bookStatus", "readingComplate");
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "option", "readingComplate");
	}
}
