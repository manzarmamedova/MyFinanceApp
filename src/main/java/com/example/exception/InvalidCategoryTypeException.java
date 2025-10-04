package com.example.exception;

/**
 * InvalidCategoryTypeException
 *
 * Custom exception thrown when a provided category type is invalid.
 */
public class InvalidCategoryTypeException extends BaseException {

    /**
     * Constructor for InvalidCategoryTypeException
     * @param type the invalid category type provided
     */
    public InvalidCategoryTypeException(String type) {
        super(MessageType.VALIDATION_ERROR, "Invalid category type: " + type);
    }
}
