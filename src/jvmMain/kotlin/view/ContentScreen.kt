package view

import androidx.compose.runtime.Composable
import viewmodel.ContentViewModel

@Composable
fun ContentScreen() {
    val contentViewModel = ContentViewModel()

    if (contentViewModel.user != null) {
        MainScreen()
    } else {
        LoginScreen()
    }
}