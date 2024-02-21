package com.stocks.Controller;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.stocks.Exceptions.StudentException;

@ControllerAdvice
public class GlobalExceptionHandler {
	   @ExceptionHandler(StudentException.class)
	    public ResponseEntity<Object> handleStudentException(StudentException ex) {
	        Map<String, Object> body = new LinkedHashMap<>();
	        body.put("timestamp", LocalDateTime.now());
	        body.put("message", ex.getMessage());

	        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	    }
}
