package view

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*

@Composable
fun PostDetailScreen(onButtonClicked: () -> Unit) {
    var text by remember { mutableStateOf("Hello, PostDetail!") }

    Column {
        Button(onClick = onButtonClicked) {
            Text(text)
        }
    }
}