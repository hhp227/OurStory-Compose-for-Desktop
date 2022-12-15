package view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import helper.navigation.NavHost
import helper.navigation.Screen
import helper.navigation.composable
import helper.navigation.rememberNavController
import kotlinx.coroutines.launch
import util.InjectorUtils
import view.ui.asyncimage.AsyncImage
import view.ui.asyncimage.loadImageBitmap
import viewmodel.MainViewModel

@Composable
fun MainScreen() {
    val viewModel: MainViewModel = remember { InjectorUtils.proviceMainViewModel() }
    val navController by rememberNavController(Screen.Lounge.name)
    val currentScreen = Screen.valueOf(navController.currentBackStackScreen)
    //
    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))

    // TODO 구조를 다시짜야겠다
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            OurStoryAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackScreen != null,
                navigateUp = navController::navigateUp
            )
        },
        drawerContent = {
            Column(
                Modifier.fillMaxWidth().wrapContentHeight().background(MaterialTheme.colors.primary).padding(16.dp),
                Arrangement.Bottom
            ) {
                AsyncImage(
                    load = { loadImageBitmap("https://image.xportsnews.com/contents/images/upload/article/2022/1209/mb_1670540580679975.jpg") },
                    painterFor = { remember { BitmapPainter(it) } },
                    contentDescription = "Sample",
                    modifier = Modifier.size(100.dp)
                )
                Text(
                    text = "Android Studio",
                    modifier = Modifier.padding(top = 8.dp),
                    color = Color.White,
                    style = MaterialTheme.typography.body1
                )
                Text(text = "Hong227", color = Color.White)
            }
            Divider(color = MaterialTheme.colors.onSurface.copy(alpha = .2f))
            DrawerButton(Icons.Filled.Home, Screen.Lounge.name, currentScreen.name == Screen.Lounge.name) {
                navController.navigate(Screen.Lounge.name) { navController.popBackStack() }
                coroutineScope.launch { scaffoldState.drawerState.close() }
            }
            DrawerButton(Icons.Filled.List, Screen.GroupList.name, currentScreen.name == Screen.GroupList.name) {
                navController.navigate(Screen.GroupList.name) { navController.popBackStack() }
                coroutineScope.launch { scaffoldState.drawerState.close() }
            }
            DrawerButton(Icons.Filled.Person, Screen.FriendList.name, currentScreen.name == Screen.FriendList.name) {
                navController.navigate(Screen.FriendList.name) { navController.popBackStack() }
                coroutineScope.launch { scaffoldState.drawerState.close() }
            }
            DrawerButton(Icons.Filled.Info, Screen.ChatList.name, currentScreen.name == Screen.ChatList.name) {
                navController.navigate(Screen.ChatList.name) { navController.popBackStack() }
                coroutineScope.launch { scaffoldState.drawerState.close() }
            }
            DrawerButton(Icons.Filled.ExitToApp, Screen.Logout.name, currentScreen.name == Screen.Logout.name) {
                navController.navigate(Screen.Logout.name) { navController.popBackStack() }
                coroutineScope.launch { scaffoldState.drawerState.close() }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Lounge.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Lounge.name) {
                LoungeScreen(onButtonClicked = { navController.navigate(Screen.PostDetail.name) })
            }
            composable(Screen.GroupList.name) {
                GroupListScreen()
            }
            composable(Screen.FriendList.name) {
                FriendScreen(onButtonClicked = { println("Hello FriendScreen") })
            }
            composable(Screen.ChatList.name) {
                ChatListScreen()
            }
            composable(Screen.Logout.name) {
                viewModel.clear()
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

@Composable
private fun DrawerButton(icon: ImageVector, label: String, isSelected: Boolean, modifier: Modifier = Modifier, action: () -> Unit) {
    val colors = MaterialTheme.colors
    val textIconColor = if (isSelected) colors.primary else colors.onSurface.copy(alpha = 0.6f)

    Surface(
        modifier = modifier.padding(start = 8.dp, top = 8.dp, end = 8.dp).fillMaxWidth(),
        color = if (isSelected) colors.primary.copy(alpha = 0.12f) else Color.Transparent,
        shape = MaterialTheme.shapes.small
    ) {
        TextButton(
            onClick = action,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    imageVector = icon,
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(textIconColor),
                    alpha = if (isSelected) 1f else 0.6f
                )
                Spacer(Modifier.width(16.dp))
                Text(
                    text = label,
                    style = MaterialTheme.typography.body2,
                    color = textIconColor
                )
            }
        }
    }
}
