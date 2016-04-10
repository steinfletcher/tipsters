package io.tipsters.controller;

import io.tipsters.error.ErrorContent;
import io.tipsters.error.TeamNotFoundError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RestErrorHandler {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorContent handleException(TeamNotFoundError error) {
        return new ErrorContent(error.getClass().getSimpleName(), error.getMessage());
    }
}
