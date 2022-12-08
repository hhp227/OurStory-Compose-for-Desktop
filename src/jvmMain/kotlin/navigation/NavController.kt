package navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable

class NavController(
    private val startDestination: String,
    var backStackScreens: MutableList<String> = mutableListOf(startDestination)
) {
    private var currentScreen: MutableState<String> = mutableStateOf(startDestination)

    val currentBackStackScreen: String
        get() = currentScreen.value

    val previousBackStackScreen: String?
        get() {
            val iterator = backStackScreens.reversed().iterator()

            if (iterator.hasNext()) {
                iterator.next()
            }
            return iterator.asSequence().firstOrNull()
        }

    fun navigate(route: String) {
        if (route != currentScreen.value) {
            currentScreen.value = route

            backStackScreens.add(currentScreen.value)
        }
    }

    fun navigateUp() {
        if (backStackScreens.isNotEmpty()) {
            backStackScreens.remove(currentScreen.value)
            backStackScreens.lastOrNull()?.also { currentScreen.value = it }
        }
    }
}

@Composable
fun rememberNavController(
    startDestination: String,
    backStackScreens: MutableList<String> = mutableListOf(startDestination)
): MutableState<NavController> = rememberSaveable {
    mutableStateOf(NavController(startDestination, backStackScreens))
}