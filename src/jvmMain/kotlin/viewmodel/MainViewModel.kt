package viewmodel

import helper.PreferenceManager
import kotlinx.coroutines.launch

class MainViewModel(private val preferenceManager: PreferenceManager) : ViewModel() {
    fun clear() {
        viewModelScope.launch {
            preferenceManager.clearUser()
        }
    }
}