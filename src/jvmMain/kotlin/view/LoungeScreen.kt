package view

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*

@Composable
fun LoungeScreen(onButtonClicked: () -> Unit) {
    Column {
        Button(onClick = onButtonClicked) {
            Text("Hello, LoungeScreen!")
        }
    }
}