package view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import util.InjectorUtils
import viewmodel.LoginViewModel

@Composable
fun LoginScreen() {
    val viewModel: LoginViewModel = remember { InjectorUtils.provideLoginViewModel() }

    Box(contentAlignment = Alignment.Center) {
        Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Column {
                Text("Welcome", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center, style = MaterialTheme.typography.h4)
                Column(modifier = Modifier.padding(10.dp), horizontalAlignment = Alignment.Start) {
                    Text("Email")
                    TextField(
                        viewModel.email,
                        onValueChange = { viewModel.email = it },
                        modifier = Modifier.fillMaxWidth().padding(10.dp),
                        placeholder = {
                            Text("Email")
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                    )
                    Text("Password")
                    TextField(
                        viewModel.password,
                        onValueChange = { viewModel.password = it },
                        modifier = Modifier.fillMaxWidth().padding(10.dp),
                        placeholder = {
                            Text("Password")
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                    )
                }
            }
            Button(onClick = viewModel::login, modifier = Modifier.width(200.dp)) {
                Text("LOGIN", fontSize = 15.sp, textAlign = TextAlign.Center)
            }
            TextButton(onClick = {

            }) {
                Text("Register")
            }
        }.also {
            viewModel.state.user?.let { user ->
                viewModel.storeUser(user)
            } ?: run {
                if (viewModel.state.error.isNotEmpty()) remember {
                    viewModel.showSnackBar()
                }
            }
        }
        if (viewModel.state.isLoading) {
            CircularProgressIndicator()
        }
        SnackbarHost(hostState = viewModel.snackbarHostState, modifier = Modifier.align(Alignment.BottomCenter))
    }
}