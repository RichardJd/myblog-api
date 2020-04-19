package br.com.rjsystems.myblog.exceptionhandler;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class MyBlogExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler({ HttpStatusCodeException.class })
	public ResponseEntity<Object> handleHttpStatusCodeException(HttpStatusCodeException ex, WebRequest request) {
		String messageUser = messageSource.getMessage("resource.not-found", null, LocaleContextHolder.getLocale());
		String messageDev = ex.getCause() != null ? ex.getCause().toString() : ex.toString();

		List<Error> errors = Arrays.asList(new Error(messageUser, messageDev));

		return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler({ EmptyResultDataAccessException.class })
	public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex,
			WebRequest request) {

		String messageUser = messageSource.getMessage("resource.not-found", null, LocaleContextHolder.getLocale());
		String messageDev = ex.toString();
		
		List<Error> errors = Arrays.asList(new Error(messageUser, messageDev));
		return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

	public static class Error {

		private String messageUser;
		private String messageDev;

		public Error(String messageUser, String messageDev) {
			this.messageUser = messageUser;
			this.messageDev = messageDev;
		}

		public String getMessageUser() {
			return messageUser;
		}

		public String getMessageDev() {
			return messageDev;
		}
	}
}
