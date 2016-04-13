package io.tipsters.error

/**
 * Base class for all application errors
 */
open class BaseError(message: String) : RuntimeException(message)

/**
 * Response returned by controllers when an application
 * error is thrown.  [ErrorMapper] maps application errors
 * to this dto.
 */
class ErrorResponse(val type: String, val message: String)

class OddsApiError(message: String) : BaseError(message)
