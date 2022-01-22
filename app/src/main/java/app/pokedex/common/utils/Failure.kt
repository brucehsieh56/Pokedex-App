package app.pokedex.common.utils

/**
 * Base Class for handling errors.
 */
sealed class Failure : Throwable() {
    object NetworkConnection : Failure()

    data class ServerError(
        override val message: String?,
        override val cause: Throwable?
    ) : Failure() {
        constructor(message: String?) : this(message, null)
        constructor(cause: Throwable?) : this(cause?.toString(), cause)
    }

    /**
     * Extend this class for feature specific failures.
     * */
    abstract class FeatureFailure : Failure()
}
