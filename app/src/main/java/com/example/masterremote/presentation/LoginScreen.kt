package com.example.masterremote.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.masterremote.R
import com.example.masterremote.domain.User


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    usernameState: TextFieldValue,
    onUsernameChanged: (TextFieldValue) -> Unit,
    passwordState: TextFieldValue,
    onPasswordChanged: (TextFieldValue) -> Unit,
    onLoginClicked: (User) -> Unit
) {
    val logo = R.drawable.master_remote


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(logo),
            contentDescription = null,
            modifier = Modifier.size(350.dp, 80.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = usernameState,
            onValueChange = onUsernameChanged,
            label = { Text("Usuário") },
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = passwordState,
            onValueChange = onPasswordChanged,
            visualTransformation = PasswordVisualTransformation(),
            label = { Text("Senha") },
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            shape = MaterialTheme.shapes.extraSmall,
            onClick = { onLoginClicked(User(usernameState.text, passwordState.text)) },
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Text("Login")
        }
    }
}
