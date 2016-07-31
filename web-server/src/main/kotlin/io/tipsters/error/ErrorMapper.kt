package io.tipsters.error

import io.tipsters.common.error.OddsProviderError
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
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
    private val log = LoggerFactory.getLogger(ErrorMapper::class.java)

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
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ResponseBody
    fun handleException(error: OddsProviderError): ErrorResponse {
        log.error("${error.message}", error)
        return createResponseBody(error)
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    fun handleException(error: MethodArgumentNotValidException): ErrorResponse {
        return ErrorResponse("ValidationError", error.message ?: "")
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    fun handleException(error: HttpMessageNotReadableException): ErrorResponse {
        return ErrorResponse(error.javaClass.simpleName, "Invalid request attributes")
    }

    // catch all errors
    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    fun handleException(error: Exception): ErrorResponse {
        log.error("${error.message}", error)
        return ErrorResponse("Server Error", "Something failed.")
    }
    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    fun handleException(error: RuntimeException): ErrorResponse {
        log.error("${error.message}", error)
        return ErrorResponse("Server Error", "Something failed.")
    }

    private fun createResponseBody(error: Exception): ErrorResponse {
        return ErrorResponse(error.javaClass.simpleName, error.message.orEmpty())
    }
}
