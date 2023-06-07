package com.example.masterremote

import com.example.masterremote.data.User
import com.example.masterremote.data.defaultUser
import com.example.masterremote.managers.AppViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

sealed class AppViewModelTest {

    private lateinit var viewModel: AppViewModel

    @Before
    fun setup() {
        viewModel = AppViewModel()
    }

    @Test
    fun testAuthenticationWithDefaultUser() {
        val user = defaultUser

        var loginResult: Boolean? = null
        viewModel.authenticate(user) { result ->
            loginResult = result
        }

        assertEquals(true, viewModel.isLogged.value)
        assertEquals(null, loginResult)
    }

    @Test
    fun testAuthenticationWithInvalidUser() {
        val user = User("username", "password")

        var loginResult = false
        viewModel.authenticate(user) { result ->
            loginResult = result
        }

        assertEquals(false, viewModel.isLogged.value)
        assertEquals(false, loginResult)
    }

    @Test
    fun testExit() {
        viewModel.exit()

        assertEquals(false, viewModel.isLogged.value)
    }
}
