package io.tipsters.error;

/**
 * Response returned by controllers when an application
 * error is thrown.  {@link RestErrorHandler} maps application errors
 * to this dto.
 */
class ErrorResponse {
    private final String type;
    private final String message;

    ErrorResponse(String type, String message) {
        this.type = type;
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }
}
