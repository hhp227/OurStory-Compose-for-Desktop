package view

import androidx.compose.runtime.Composable

@Composable
fun ContentScreen() {
    var user: String? = ""

    if (user != null) {
        MainScreen()
    } else {
        LoginScreen()
    }
}