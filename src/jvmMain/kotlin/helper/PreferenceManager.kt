package helper

import helper.dataStore.UserDataStore
import kotlinx.coroutines.flow.*
import model.User
import java.io.IOException
import java.util.prefs.Preferences

class PreferenceManager {
    private val userDataStore = UserDataStore()

    val userFlow: Flow<User?>
        get() = userDataStore.data.catch { e ->
            if (e is IOException) {
                println("Error reading preference. $e")
                emit(User())
            } else {
                throw e
            }
        }

    suspend fun storeUser(user: User) {
        userDataStore.updateData { user }
    }

    suspend fun clearUser() {
        userDataStore.updateData { null }
    }

    suspend fun fetchInitialPreferences() = userDataStore.data.first()
}