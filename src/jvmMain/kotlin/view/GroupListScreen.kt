package view

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*

@Composable
fun GroupListScreen() {
    var text by remember { mutableStateOf("Hello, GroupListScreen!") }

    Column {
        Button(onClick = {
            text = "Hello, OurStory!"
        }) {
            Text(text)
        }
    }
}