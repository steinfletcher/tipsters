package io.tipsters.common.error

class OddsProviderError : BaseError {
    constructor(message: String, e: Exception) : super(message, e)
    constructor(message: String) : super(message)
}
