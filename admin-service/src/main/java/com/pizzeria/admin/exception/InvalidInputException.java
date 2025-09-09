package com.pizzeria.admin.exception;

/**
 * Custom exception to be thrown when user credentials (e.g., username/password) are invalid.
 * This is used to provide a specific error message for login failures.
 */
public class InvalidInputException extends RuntimeException {

    public InvalidInputException(String message) {
        super(message);
    }
}
