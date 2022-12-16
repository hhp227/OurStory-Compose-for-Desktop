package helper.dataStore

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.serialization.json.*
import model.User
import util.toJsonObject
import java.util.prefs.Preferences

class UserDataStore : DataStore<User?> {
    private val json = Json { ignoreUnknownKeys = true }

    private val userPreferences = Preferences.userRoot()

    private val _data = MutableStateFlow(getUser())
    override val data: Flow<User?> get() = _data

    override suspend fun updateData(transform: suspend (t: User?) -> User?): User? {
        return _data.updateAndGet { transform.invoke(it) }.also { user ->
            if (user != null) {
                json.encodeToJsonElement(user).jsonObject.forEach { t, u ->
                    userPreferences.put(t, u.toString().replace("\"", ""))
                }
            } else {
                userPreferences.clear()
            }
        }
    }

    private fun getUser(): User? {
        val map = userPreferences.keys().associateWith { userPreferences[it, null] }
        return if (map.isNotEmpty()) json.decodeFromJsonElement(map.toJsonObject()) else null
    }
}