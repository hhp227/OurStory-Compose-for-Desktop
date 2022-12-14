package view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import util.InjectorUtils

@Composable
fun ContentScreen() {
    val contentViewModel = remember { InjectorUtils.provideContentViewModel() }

    if (contentViewModel.user != null) {
        MainScreen()
    } else {
        LoginScreen()
    }
}