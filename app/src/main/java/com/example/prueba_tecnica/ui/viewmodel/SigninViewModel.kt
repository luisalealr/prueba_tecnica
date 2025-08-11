package com.example.prueba_tecnica.ui.viewmodel

import android.content.Context
import android.util.Patterns
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prueba_tecnica.data.helper.TokenManager
import com.example.prueba_tecnica.data.model.SigninRequest
import com.example.prueba_tecnica.data.network.AuthApi
import com.example.prueba_tecnica.data.network.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SigninViewModel(private val context: Context) : ViewModel() {

    //Validar email y contraseña para enviar los datos
    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _loginEnable = MutableLiveData<Boolean>()
    val loginEnable: LiveData<Boolean> = _loginEnable

    fun onLoginChange(email: String, password: String) {
        _email.value = email
        _password.value = password
        _loginEnable.value = isValidEmail(email) && isValidPassword(password)
    }

    private fun isValidEmail(email: String): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(email).matches()

    private fun isValidPassword(password: String): Boolean = password.length > 6

    val apiService: AuthApi = RetrofitClient.authApi

    suspend fun loginUser(email: String, password: String): String? {
        val response = apiService.signin(SigninRequest(email, password))
        if (response.isSuccessful) {
            return response.body()?.token
        }
        return null
    }

    private val _token = MutableStateFlow<String?>(null)
    val token: StateFlow<String?> = _token

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    val state = mutableStateOf("")

    fun signInUser(email: String, password: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val result = loginUser(email, password)
            if (result != null) {
                _token.value = result
                _error.value = null
                TokenManager.saveToken(context, result)
                onResult(true)
            } else {
                _error.value = "Error al iniciar sesión"
                onResult(false)
            }
        }
    }

}