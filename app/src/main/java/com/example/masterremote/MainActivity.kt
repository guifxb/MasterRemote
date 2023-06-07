package com.example.masterremote

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.masterremote.managers.AppViewModel
import com.example.masterremote.screens.Dashboard
import com.example.masterremote.screens.LoginScreen
import com.example.masterremote.ui.theme.MasterRemoteTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {

            val appViewModel: AppViewModel = viewModel(factory = AppViewModel.Factory)
            val loggedIn = appViewModel.isLogged.collectAsState().value

            MasterRemoteTheme {
                if (loggedIn) {
                    Dashboard(onExitClicked = { appViewModel.exit() })
                } else {
                    LoginScreen(onLoginClicked = { credentials ->
                        appViewModel.authenticate(credentials) { success ->
                            if (!success) {
                                showFailedLoginToast(context = this@MainActivity)
                            }
                        }
                    })
                }
            }
        }
    }

    private fun showFailedLoginToast(context: Context) {
        val message = "Login failed. Please try again."
        val duration = Toast.LENGTH_SHORT

        Toast.makeText(context, message, duration).show()
    }
}



