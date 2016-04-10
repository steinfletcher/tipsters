package io.tipsters.error;

/**
 * Base class for all application errors
 */
class BaseError extends RuntimeException {
    BaseError(String message) {
        super(message);
    }
}
