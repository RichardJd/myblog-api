package br.com.rjsystems.myblog.exceptionhandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class MyBlogExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String messageUser = messageSource.getMessage("invalid.message", null, LocaleContextHolder.getLocale());
		String messageDev = ex.getCause() != null ? ex.getCause().toString() : ex.toString();
		
		List<Error> errors = Arrays.asList(new Error(messageUser, messageDev));
		
		return handleExceptionInternal(ex, errors , headers, status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		var errors = createListError(ex.getBindingResult());
		
		return handleExceptionInternal(ex, errors, headers, status, request);
	}

	@ExceptionHandler({ HttpStatusCodeException.class })
	public ResponseEntity<Object> handleHttpStatusCodeException(HttpStatusCodeException ex, WebRequest request) {
		var messageUser = messageSource.getMessage("github.not-found", null, LocaleContextHolder.getLocale());
		var messageDev = ex.getCause() != null ? ex.getCause().toString() : ex.toString();

		var errors = Arrays.asList(new Error(messageUser, messageDev));

		return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler({ EmptyResultDataAccessException.class })
	public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex,
			WebRequest request) {

		var messageUser = messageSource.getMessage("resource.not-found", null, LocaleContextHolder.getLocale());
		var messageDev = ex.toString();
		
		List<Error> errors = Arrays.asList(new Error(messageUser, messageDev));
		return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler({ DataIntegrityViolationException.class })
	public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
		var messageUser = messageSource.getMessage("resource.operation-not-allowed",  null, LocaleContextHolder.getLocale());
		var messageDev = ExceptionUtils.getRootCauseMessage(ex);
		
		List<Error> errors = Arrays.asList(new Error(messageUser, messageDev));
		
		return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	private List<Error> createListError(BindingResult bindingResult) {
		var errors = new ArrayList<Error>();
		
		bindingResult.getFieldErrors().forEach(f -> {
			var messageUser = messageSource.getMessage(f, LocaleContextHolder.getLocale());
			var messageDev = f.toString();
			errors.add(new Error(messageUser, messageDev));			
		});
		
		return errors;
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
