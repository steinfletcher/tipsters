package io.tipsters.error;

public class ErrorContent {
    private final String type;
    private final String message;

    public ErrorContent(String type, String message) {
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
