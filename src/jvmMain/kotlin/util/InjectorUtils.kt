package util

import api.AuthService
import data.UserRepository
import helper.PreferenceManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import viewmodel.ContentViewModel
import viewmodel.LoginViewModel

object InjectorUtils {
    private val loginViewModel = LoginViewModel(getUserRepository(), getPreferenceManager())

    private val contentViewModel = ContentViewModel(getPreferenceManager())

    private fun getUserRepository(): UserRepository {
        return UserRepository.getInstance(AuthService.create())
    }

    private fun getPreferenceManager() = PreferenceManager()

    fun provideLoginViewModel(): StateFlow<LoginViewModel> {
        return MutableStateFlow(loginViewModel)
    }

    fun provideContentViewModel(): StateFlow<ContentViewModel> {
        return MutableStateFlow(contentViewModel)
    }
}