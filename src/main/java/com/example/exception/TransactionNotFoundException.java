package com.example.exception;

/**
 * TransactionNotFoundException
 *
 * Custom exception thrown when a Transaction with the given ID is not found.
 */
public class TransactionNotFoundException extends BaseException {

    /**
     * Constructor for TransactionNotFoundException
     * @param id the ID of the transaction that was not found
     */
    public TransactionNotFoundException(Long id) {
        super(MessageType.NO_RECORD_EXIST, "Transaction id=" + id + " not found");
    }
}
