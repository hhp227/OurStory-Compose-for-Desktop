package view

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*

@Composable
fun LoginScreen() {
    var text by remember { mutableStateOf("Hello, Login!") }

    Column {
        Button(onClick = {
            text = "Hello, OurStory!"
        }) {
            Text(text)
        }
    }
}