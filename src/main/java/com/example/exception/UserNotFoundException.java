package com.example.exception;

/**
 * UserNotFoundException
 *
 * Custom exception thrown when a User with the given ID is not found.
 */
public class UserNotFoundException extends BaseException {

    /**
     * Constructor for UserNotFoundException
     * @param id the ID of the user that was not found
     */
    public UserNotFoundException(Long id) {
        super(MessageType.NO_RECORD_EXIST, "User id=" + id + " not found");
    }
}
