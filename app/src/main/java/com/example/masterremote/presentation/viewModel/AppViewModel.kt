package com.example.masterremote.presentation.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import com.example.masterremote.data.FeatureRepository
import com.example.masterremote.data.UserRepository
import com.example.masterremote.domain.Feature
import com.example.masterremote.domain.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class AppViewModel(
    private val featureRepository: FeatureRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _isLogged = MutableStateFlow(false)
    val isLogged: StateFlow<Boolean> = _isLogged.asStateFlow()

    private val _usernameState = mutableStateOf(TextFieldValue())
    val usernameState: State<TextFieldValue> get() = _usernameState

    private val _passwordState = mutableStateOf(TextFieldValue())
    val passwordState: State<TextFieldValue> get() = _passwordState

    fun updateUsernameState(value: TextFieldValue) {
        _usernameState.value = value
    }

    fun updatePasswordState(value: TextFieldValue) {
        _passwordState.value = value
    }

    fun getFeatures(): List<Feature> {
        return featureRepository.getFeatures()
    }

    fun getUser(): User {
        return userRepository.getUser()
    }


    fun authenticate(user: User, onLoginResult: (Boolean) -> Unit) {
        if (user == getUser()) {
            _isLogged.update {
                true
            }
        } else {
            onLoginResult(false)
        }
    }

    fun exit() {
        _isLogged.update {
            false
        }
        updatePasswordState(TextFieldValue().copy(text = ""))
        updateUsernameState(TextFieldValue().copy(text = ""))
    }

}