package com.example.exception;

/**
 * BaseException
 *
 * Custom runtime exception class for standardized API error handling.
 * Holds a MessageType and an ApiError object to provide detailed error information.
 */
public class BaseException extends RuntimeException {

    /** MessageType defining HTTP status and default message */
    private final MessageType messageType;

    /** Detailed API error object */
    private final ApiError errorMessage;

    /**
     * Constructor for BaseException
     * @param type MessageType containing HTTP status and default message
     * @param detail optional detailed message
     */
    public BaseException(MessageType type, String detail) {
        super(detail == null ? type.getDefaultMessage() : type.getDefaultMessage() + " - " + detail);
        this.messageType = type;
        this.errorMessage = ApiError.of(type, detail == null ? type.getDefaultMessage() : detail);
    }

    /** Get the MessageType of the exception */
    public MessageType getMessageType() {
        return messageType;
    }

    /** Get the ApiError object associated with this exception */
    public ApiError getErrorMessage() {
        return errorMessage;
    }
}
