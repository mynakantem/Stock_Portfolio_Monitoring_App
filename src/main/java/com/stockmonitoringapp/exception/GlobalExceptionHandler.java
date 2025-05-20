package com.stockmonitoringapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

// This handles all exceptions in the entire project
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Inner class to send error response in clean JSON format
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

    // Handles the custom ApiException 
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponse> handleApiException(ApiException ex) {
        ErrorResponse response = new ErrorResponse(
                ex.getMessage(),
                ex.getErrorCode(),
                ex.getStatus().value()
        );
        return new ResponseEntity<>(response, ex.getStatus());
    }

    // Handles all other unknown errors (like NullPointerException)
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
