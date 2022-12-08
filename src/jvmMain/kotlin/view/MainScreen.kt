package view

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import navigation.NavHost
import navigation.composable
import navigation.rememberNavController

@Composable
fun MainScreen() {
    val navController by rememberNavController(Screen.Home.name)
    val currentScreen = Screen.valueOf(navController.currentBackStackScreen)

    Scaffold(
        topBar = {
            OurStoryAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackScreen != null,
                navigateUp = navController::navigateUp
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.name) {
                HomeScreen(onButtonClicked = { navController.navigate(Screen.PostDetail.name) })
            }
            composable(Screen.PostDetail.name) {
                PostDetailScreen(onButtonClicked = { navController.navigate(Screen.CreatePost.name) })
            }
            composable(Screen.CreatePost.name) {
                CreatePostScreen()
            }
        }.build()
    }
}

@Composable
fun OurStoryAppBar(
    currentScreen: Screen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit
) {
    TopAppBar(
        title = { Text(text = currentScreen.name) },
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