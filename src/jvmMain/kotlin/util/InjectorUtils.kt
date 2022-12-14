package util

import api.AuthService
import com.sun.tools.javac.Main
import data.UserRepository
import helper.PreferenceManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import viewmodel.ContentViewModel
import viewmodel.LoginViewModel
import viewmodel.MainViewModel

object InjectorUtils {
    private val preferenceManager = PreferenceManager()

    private fun getUserRepository(): UserRepository {
        return UserRepository.getInstance(AuthService.create())
    }

    private fun getPreferenceManager() = preferenceManager

    fun provideLoginViewModel(): LoginViewModel {
        return LoginViewModel(getUserRepository(), getPreferenceManager())
    }

    fun provideContentViewModel(): ContentViewModel {
        return ContentViewModel(getPreferenceManager())
    }

    fun proviceMainViewModel(): MainViewModel {
        return MainViewModel(getPreferenceManager())
    }
}