package io.tipsters.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Handler to map application errors to response codes/messages.
 * {@link ErrorResponse} is the response body.
 */
@ControllerAdvice
class RestErrorHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    ErrorResponse handleException(TeamNotFoundError error) {
        return createResponseBody(error);
    }

    private ErrorResponse createResponseBody(BaseError error) {
        return new ErrorResponse(error.getClass().getSimpleName(), error.getMessage());
    }
}
