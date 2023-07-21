package com.appsdeveloperblog.estore.ProductService.core.errorhandling;

import java.util.Date;

import org.axonframework.commandhandling.CommandExecutionException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ProductServiceErrorHandler {
	
	@ExceptionHandler (value = {IllegalStateException.class} )
	public ResponseEntity <Object> handleIllegalStateException (IllegalStateException e, WebRequest wr ){
		
		
		ErrorHandler eh = new ErrorHandler (e.getMessage(), new Date());
		return new ResponseEntity <> (eh, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@ExceptionHandler (value = {Exception.class} )
	public ResponseEntity <Object> handleOtherExceptions (Exception e, WebRequest wr ){
		
		ErrorHandler eh = new ErrorHandler (e.getMessage(), new Date());
		return new ResponseEntity <> (eh, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@ExceptionHandler (value = {CommandExecutionException.class} )
	public ResponseEntity <Object> handleCommandExecutionException (CommandExecutionException e, WebRequest wr ){
		
		ErrorHandler eh = new ErrorHandler (e.getMessage(), new Date());
		return new ResponseEntity <> (eh, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		
	}

}
