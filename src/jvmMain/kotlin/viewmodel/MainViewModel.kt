package viewmodel

import helper.PreferenceManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel(private val preferenceManager: PreferenceManager) : ViewModel() {
    fun clear() {
        viewModelScope.launch {
            delay(100)
            preferenceManager.clearUser()
        }
    }
}