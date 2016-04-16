package io.tipsters.error

import io.tipsters.common.error.BaseError
import io.tipsters.common.error.OddsProviderError
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

/**
 * Handler to map application errors to response codes/messages.
 * [ErrorResponse] is the response body.
 */
@ControllerAdvice
internal class ErrorMapper {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    fun handleException(error: OddsApiError): ErrorResponse {
        return createResponseBody(error)
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    fun handleException(error: CompetitionNotFoundError): ErrorResponse {
        return createResponseBody(error)
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    fun handleException(error: OddsProviderError): ErrorResponse {
        return createResponseBody(error)
    }

    private fun createResponseBody(error: BaseError): ErrorResponse {
        return ErrorResponse(error.javaClass.simpleName, error.message.orEmpty())
    }
}
