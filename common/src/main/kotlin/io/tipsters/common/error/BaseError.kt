package io.tipsters.common.error

/**
 * Base class for all application errors
 */
open class BaseError : RuntimeException {
    constructor(message: String, cause: Exception) : super(message, cause)
    constructor(message: String) : super(message)
}