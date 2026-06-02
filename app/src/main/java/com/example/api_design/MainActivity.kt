package com.example.api_design

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.api_design.ui.components.Logintopbar
import com.example.api_design.ui.screen.IpSettingsDialog
import com.example.api_design.ui.screen.LoginScreen
import com.example.api_design.ui.screen.RegisterScreen
import com.example.api_design.ui.theme.API_designTheme
import com.example.api_design.ui.viewmode.LoginViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            API_designTheme {
                val loginViewModel: LoginViewModel = viewModel()
                val context = LocalContext.current
                var showIpDialog by remember { mutableStateOf(false) }
                
                // State to manage navigation between screens
                var currentScreen by remember { mutableStateOf("login") }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        Logintopbar(onSettingsClick = { showIpDialog = true })
                    }
                ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        when (currentScreen) {
                            "login" -> LoginScreen(
                                viewModel = loginViewModel,
                                onNavigateToRegister = { 
                                    currentScreen = "register"
                                    loginViewModel.loginStatus = "" // Clear status when switching
                                }
                            )
                            "register" -> RegisterScreen(
                                viewModel = loginViewModel,
                                onBackToLogin = { 
                                    currentScreen = "login"
                                    loginViewModel.loginStatus = "" // Clear status when switching
                                }
                            )
                        }
                    }

                    if (showIpDialog) {
                        IpSettingsDialog(
                            onDismiss = { showIpDialog = false },
                            onSave = { newIp ->
                                val prefs = context.getSharedPreferences("api_prefs", Context.MODE_PRIVATE)
                                prefs.edit().putString("server_ip", newIp).apply()
                                showIpDialog = false
                                // Provide feedback when IP is changed
                                loginViewModel.loginStatus = "IP diganti ke: $newIp"
                            },
                            context = context
                        )
                    }
                }
            }
        }
    }
}
