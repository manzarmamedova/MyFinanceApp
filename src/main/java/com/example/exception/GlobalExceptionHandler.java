package com.example.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.http.converter.HttpMessageNotReadableException;

/**
 * GlobalExceptionHandler
 *
 * Handles exceptions globally across all controllers.
 * Provides standardized API error responses for different types of exceptions.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handle custom BaseException
     */
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ApiError> handleBaseException(BaseException ex) {
        MessageType type = ex.getMessageType();
        ApiError body = ex.getErrorMessage();
        return ResponseEntity.status(type.getHttpStatus()).body(body);
    }

    /**
     * Handle validation errors (Bean Validation)
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException ex) {
        String detail = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .findFirst().orElse(ex.getMessage());
        ApiError body = ApiError.of(MessageType.VALIDATION_ERROR, detail);
        return ResponseEntity.status(MessageType.VALIDATION_ERROR.getHttpStatus()).body(body);
    }

    /**
     * Handle JSON parse errors (HttpMessageNotReadableException)
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> handleNotReadable(HttpMessageNotReadableException ex) {
        ApiError body = ApiError.of(MessageType.VALIDATION_ERROR, ex.getMostSpecificCause().getMessage());
        return ResponseEntity.status(MessageType.VALIDATION_ERROR.getHttpStatus()).body(body);
    }

    /**
     * Handle type mismatches for request parameters
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiError> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String detail = ex.getName() + " parameter is not of expected type: " + ex.getMessage();
        ApiError body = ApiError.of(MessageType.VALIDATION_ERROR, detail);
        return ResponseEntity.status(MessageType.VALIDATION_ERROR.getHttpStatus()).body(body);
    }

    /**
     * Handle all other exceptions (fallback)
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleOther(Exception ex) {
        ApiError body = ApiError.of(MessageType.GENERAL_EXCEPTION, ex.getMessage());
        return ResponseEntity.status(MessageType.GENERAL_EXCEPTION.getHttpStatus()).body(body);
    }
}
