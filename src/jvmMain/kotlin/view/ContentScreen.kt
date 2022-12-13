package view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import util.InjectorUtils

@Composable
fun ContentScreen() {
    val contentViewModel by InjectorUtils.provideContentViewModel().collectAsState()

    if (contentViewModel.user != null) {
        MainScreen()
    } else {
        LoginScreen()
    }
}