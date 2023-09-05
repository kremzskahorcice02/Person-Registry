package org.example.exceptions;

public class WrongUserInputException extends RuntimeException{
    public WrongUserInputException(String message) {
        super(message);
    }
}
