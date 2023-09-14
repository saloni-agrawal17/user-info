package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class UserRestExceptionHandler extends ResponseEntityExceptionHandler{	
	

    @Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
    	
    	Map<String, Object> responseBody = new LinkedHashMap<>();
        
        responseBody.put("status", status.value());
          
        List<String> errors = new ArrayList<String>();
        
        List<FieldError> fieldErrorList = ex.getBindingResult().getFieldErrors();
        
        for(FieldError fieldError : fieldErrorList) {
        	errors.add(fieldError.getDefaultMessage());
        }
          
         responseBody.put("errors", errors);
         
         return new ResponseEntity<>(responseBody, headers, status);
	}
    
    @ExceptionHandler
	public ResponseEntity<UserErrorResponse> handleException(UserNotFoundException e){
		
		UserErrorResponse userErrorResponse = new UserErrorResponse();
		userErrorResponse.setMessage(e.getMessage());
		
		return new ResponseEntity<UserErrorResponse>(userErrorResponse,HttpStatus.NOT_FOUND);
		
	}
	
}
