package com.example.exception;

import org.springframework.http.HttpStatus;

/**
 * MessageType
 *
 * Enum representing different types of API messages and their corresponding HTTP statuses.
 * Used for standardized error responses across the application.
 */
public enum MessageType {

    /** Resource not found */
    NOT_FOUND(HttpStatus.NOT_FOUND, "Resource not found"),

    /** Validation error (bad request) */
    VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "Validation error"),

    /** General unexpected exception */
    GENERAL_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error"),

    /** No record exists (custom) */
    NO_RECORD_EXIST(HttpStatus.NOT_FOUND, "No record found");

    /** HTTP status associated with this message type */
    private final HttpStatus httpStatus;

    /** Default message for this message type */
    private final String defaultMessage;

    MessageType(HttpStatus httpStatus, String defaultMessage) {
        this.httpStatus = httpStatus;
        this.defaultMessage = defaultMessage;
    }

    /** Get the HTTP status */
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    /** Get the default message */
    public String getDefaultMessage() {
        return defaultMessage;
    }
}
