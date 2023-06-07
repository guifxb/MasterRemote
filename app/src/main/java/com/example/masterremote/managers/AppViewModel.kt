package com.example.masterremote.managers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.masterremote.data.User
import com.example.masterremote.data.defaultUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AppViewModel : ViewModel() {


    private val _isLogged = MutableStateFlow(false)
    val isLogged: StateFlow<Boolean> = _isLogged.asStateFlow()


    fun authenticate(user: User, onLoginResult: (Boolean) -> Unit) {
        if (user == defaultUser) {
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
    }


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                AppViewModel()
            }
        }
    }
}