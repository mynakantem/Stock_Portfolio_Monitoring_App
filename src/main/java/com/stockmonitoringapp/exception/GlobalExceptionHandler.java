package com.stockmonitoringapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	public static class ErrorResponse {
		private LocalDateTime timestamp;
		private String message;
		private String errorCode;
		private int status;
	
		
		public ErrorResponse(String message, String errorCode, int status) {
			this.timestamp = LocalDateTime.now();
			this.message = message;
			this.errorCode = errorCode;
			this.status = status;
		}
		 // Getters
        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        public String getMessage() {
            return message;
        }

        public String getErrorCode() {
            return errorCode;
        }

        public int getStatus() {
            return status;
        }
    }

	//Handles API Exceptions along with it's subclasses
	@ExceptionHandler(ApiException.class)
	public ResponseEntity<ErrorResponse> handleApiException(ApiException ex) {
		ErrorResponse response = new ErrorResponse(
				ex.getMessage(),
				ex.geterrorCode(),
				ex.getStatus().value()
				);
		return new ResponseEntity<>(response, ex.getStatus());	
	}
	//handles all other errors like NullPointerException
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
		ErrorResponse response = new ErrorResponse(
				"Something went wrong",
				"INTERNAL_ERROR",
				HttpStatus.INTERNAL_SERVER_ERROR.value()
				);
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
				
	}
}

