package com.example.exception;

/**
 * InvalidRequestException
 *
 * Custom exception thrown when a request is invalid.
 * Can be used for validation or bad input errors.
 */
public class InvalidRequestException extends BaseException {

    /**
     * Constructor for InvalidRequestException
     * @param detail detailed message describing why the request is invalid
     */
    public InvalidRequestException(String detail) {
        super(MessageType.VALIDATION_ERROR, detail);
    }
}
