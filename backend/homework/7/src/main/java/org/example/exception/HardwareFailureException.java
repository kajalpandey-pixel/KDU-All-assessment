package org.example.exception;

public class HardwareFailureException extends RuntimeException {

    public HardwareFailureException() {
        super();
    }

    public HardwareFailureException(String message) {
        super(message);
    }
}
