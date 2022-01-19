package app.pokedex.common.utils

/**
 * Represents a value of one of two possible types (a disjoint union). Instances of [Either] are
 * either an instance of [Left] or [Right]. [Left] is used for "failure" and [Right] is used for
 * "success".
 *
 * https://github.com/android10/Android-CleanArchitecture-Kotlin
 */
sealed class Either<out L, out R> {
    /**
     * Represents the left side of [Either] class which by convention is a "Failure".
     * */
    data class Left<out L>(val a: L) : Either<L, Nothing>()

    /**
     * Represents the right side of [Either] class which by convention is a "Success".
     * */
    data class Right<out R>(val b: R) : Either<Nothing, R>()

    /**
     * Returns true if this is a Right, false otherwise.
     */
    val isRight get() = this is Right<R>

    /**
     * Returns true if this is a Left, false otherwise.
     */
    val isLeft get() = this is Left<L>

    /**
     * Creates a Left type.
     */
    fun <L> left(a: L) = Either.Left(a)


    /**
     * Creates a Left type.
     */
    fun <R> right(b: R) = Either.Right(b)
}