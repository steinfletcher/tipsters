package io.tipsters.uiapi.web;

public class OddsProviderError extends RuntimeException {
  public OddsProviderError(String message) {
    super(message);
  }

  public OddsProviderError(String message, Throwable cause) {
    super(message, cause);
  }
}
