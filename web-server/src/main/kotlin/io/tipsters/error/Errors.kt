package io.tipsters.error

import io.tipsters.common.error.BaseError

/**
 * Response returned by controllers when an application
 * error is thrown.  [ErrorMapper] maps application errors
 * to this dto.
 */
class ErrorResponse(val type: String, val message: String)

class OddsApiError(message: String) : BaseError(message)

class CompetitionNotFoundError(message: String) : BaseError(message)
