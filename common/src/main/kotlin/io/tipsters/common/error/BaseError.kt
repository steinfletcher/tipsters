package io.tipsters.common.error

/**
 * Base class for all application errors
 */
open class BaseError(message: String) : RuntimeException(message)