package view

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*

@Composable
fun LoungeScreen() {
    var text by remember { mutableStateOf("Hello, Lounge!") }

    Column {
        Button(onClick = {
            text = "Hello, OurStory!"
        }) {
            Text(text)
        }
    }
}