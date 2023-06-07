package com.example.masterremote.core

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.masterremote.core.di.appModule
import com.example.masterremote.presentation.Dashboard
import com.example.masterremote.presentation.LoginScreen
import com.example.masterremote.ui.theme.MasterRemoteTheme
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MainActivity : ComponentActivity() {

    private val appViewModel: AppViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        setContent {

            val loggedIn = appViewModel.isLogged.collectAsState().value

            MasterRemoteTheme {
                if (loggedIn) {
                    Dashboard(onExitClicked = { appViewModel.exit() })
                } else {
                    LoginScreen(
                        usernameState = appViewModel.usernameState.value,
                        onUsernameChanged = { appViewModel.updateUsernameState(it) },
                        passwordState = appViewModel.passwordState.value,
                        onPasswordChanged = { appViewModel.updatePasswordState(it) },
                        onLoginClicked = { credentials ->
                        appViewModel.authenticate(credentials) { success ->
                            if (!success) {
                                showFailedLoginToast(context = this@MainActivity)
                            }
                        }
                    })
                }
            }
        }

        startKoin {
            androidContext(this@MainActivity)
            modules(appModule)
        }
    }

    private fun showFailedLoginToast(context: Context) {
        val message = "Login failed. Please try again."
        val duration = Toast.LENGTH_SHORT

        Toast.makeText(context, message, duration).show()
    }
}



