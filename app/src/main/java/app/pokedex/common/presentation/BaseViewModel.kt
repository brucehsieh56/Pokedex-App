package app.pokedex.common.presentation

import androidx.lifecycle.ViewModel
import app.pokedex.common.utils.Failure
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Base [ViewModel] with default [Failure] handling.
 * */
abstract class BaseViewModel : ViewModel() {

    private val _failure = MutableStateFlow<Failure?>(null)
    val failure = _failure.asStateFlow()

    protected fun handleFailure(failure: Failure) {
        _failure.value = failure
    }

    protected fun resetFailure() {
        _failure.value = null
    }
}