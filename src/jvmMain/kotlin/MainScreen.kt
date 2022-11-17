import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*

@Composable
fun MainScreen() {
    var text by remember { mutableStateOf("Hello, World!") }

    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "MainScreen") }) },
        content = {
            Button(onClick = {
                text = "Hello, OurStory!"
            }) {
                Text(text)
            }
        }
    )
}