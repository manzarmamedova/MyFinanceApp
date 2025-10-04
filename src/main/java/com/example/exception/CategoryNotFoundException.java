package com.example.exception;

/**
 * CategoryNotFoundException
 *
 * Custom exception thrown when a Category with the given ID is not found.
 */
public class CategoryNotFoundException extends BaseException {

    /**
     * Constructor for CategoryNotFoundException
     * @param id the ID of the category that was not found
     */
    public CategoryNotFoundException(Long id) {
        super(MessageType.NO_RECORD_EXIST, "Category id=" + id);
    }
}







