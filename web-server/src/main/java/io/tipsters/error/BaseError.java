package io.tipsters.error;

public class BaseError extends RuntimeException {
    public BaseError(String message) {
        super(message);
    }
}
