package view

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*

@Composable
fun ChatListScreen() {
    var text by remember { mutableStateOf("Hello, ChatListScreen!") }

    Column {
        Button(onClick = {
            text = "Hello, OurStory!"
        }) {
            Text(text)
        }
    }
}