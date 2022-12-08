package view

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import navigation.NavHost
import navigation.composable
import navigation.rememberNavController

@Composable
fun MainScreen() {
    val navController by rememberNavController(Screen.Home.name)

    Scaffold(
        topBar = {
            OurStoryAppBar(
                currentScreen = navController.currentScreen.value,
                canNavigateBack = navController.previousBackStackScreen != null,
                navigateUp = navController::navigateUp
            )
        }
    ) {
        NavHost(
            navController = navController
        ) {
            composable(Screen.Home.name) {
                HomeScreen(onButtonClicked = { navController.navigate(Screen.PostDetail.name) })
            }
            composable(Screen.PostDetail.name) {
                PostDetailScreen()
            }
        }.build()
    }
}

@Composable
fun OurStoryAppBar(
    currentScreen: String,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit
) {
    TopAppBar(
        title = { Text(text = currentScreen) },
        navigationIcon = if (canNavigateBack) {
            {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        } else null
    )
}