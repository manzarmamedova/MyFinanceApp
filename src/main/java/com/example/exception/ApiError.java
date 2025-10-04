package com.example.exception;

import java.time.LocalDateTime;

/**
 * ApiError
 *
 * Represents a standardized error response for API endpoints.
 * Can be used to return detailed error information to the client.
 */
public class ApiError {

    /** Timestamp when the error occurred */
    private LocalDateTime timestamp;

    /** HTTP status code */
    private int status;

    /** Short error description */
    private String error;

    /** Detailed error message */
    private String message;

    /** Request path that caused the error (optional) */
    private String path;

    /**
     * Private constructor to enforce using the factory method.
     */
    private ApiError(LocalDateTime timestamp, int status, String error, String message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    /**
     * Factory method to create ApiError from a MessageType and detailed message.
     * @param type MessageType containing HTTP status and default message
     * @param detail detailed error message
     * @return ApiError instance
     */
    public static ApiError of(MessageType type, String detail) {
        return new ApiError(
                LocalDateTime.now(),
                type.getHttpStatus().value(),
                type.getDefaultMessage(),
                detail,
                null
        );
    }

    // Getters
    public LocalDateTime getTimestamp() { return timestamp; }
    public int getStatus() { return status; }
    public String getError() { return error; }
    public String getMessage() { return message; }
    public String getPath() { return path; }

    /** Setter for path (optional, can be set later) */
    public void setPath(String path) { this.path = path; }
}
