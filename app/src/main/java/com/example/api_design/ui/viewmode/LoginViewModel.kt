package com.example.api_design.ui.viewmode

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.api_design.data.remote.RetrofitClient
import kotlinx.coroutines.launch
import java.net.ConnectException
import java.net.SocketTimeoutException

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    var loginStatus by mutableStateOf("")
    var isLoading by mutableStateOf(false)

    fun performLogin(username: String, password: String) {
        val context = getApplication<Application>()
        loginStatus = ""

        if (username.isBlank() || password.isBlank()) {
            loginStatus = "Error: Favor prense field sira hotu!"
            return
        }

        isLoading = true
        val currentUrl = RetrofitClient.getBaseUrl(context)

        viewModelScope.launch {
            try {
                val response = RetrofitClient.getInstance(context).loginUser(username, password)
                if (response.success) {
                    loginStatus = "Login Susesu: ${response.message}"
                } else {
                    loginStatus = "Faila: ${response.message}"
                }
            } catch (e: ConnectException) {
                loginStatus = "Error: Labele konekta ba server.\nIP: $currentUrl\nVerifika fali IP no Porta!"
            } catch (e: SocketTimeoutException) {
                loginStatus = "Error: Tempu liu ona (Timeout)."
            } catch (e: Exception) {
                loginStatus = "Error: ${e.localizedMessage ?: "Akontese fali error ruma"}"
            } finally {
                isLoading = false
            }
        }
    }

    fun performRegister(username: String, name: String, password: String) {
        val context = getApplication<Application>()
        loginStatus = ""

        if (username.isBlank() || name.isBlank() || password.isBlank()) {
            loginStatus = "Error: Favor prense field sira hotu!"
            return
        }

        isLoading = true
        val currentUrl = RetrofitClient.getBaseUrl(context)

        viewModelScope.launch {
            try {
                val response = RetrofitClient.getInstance(context).registerUser(username, name, password)
                if (response.success) {
                    loginStatus = "Registu Susesu: ${response.message}"
                } else {
                    loginStatus = "Faila: ${response.message}"
                }
            } catch (e: ConnectException) {
                loginStatus = "Error: Labele konekta ba server.\nIP: $currentUrl"
            } catch (e: Exception) {
                loginStatus = "Error: ${e.localizedMessage ?: "Akontese error"}"
            } finally {
                isLoading = false
            }
        }
    }
}
