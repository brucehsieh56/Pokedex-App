package app.pokedex.common.utils

/**
 * Base Class for handling errors.
 */
sealed class Failure : Throwable() {
    object NetworkConnection : Failure()
    data class ServerError(override val message: String = String.empty()) : Failure()

    /**
     * Extend this class for feature specific failures.
     * */
    abstract class FeatureFailure : Failure()
}
