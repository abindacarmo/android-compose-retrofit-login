package com.example.api_design.data.model

data class LoginResponse(
    val status: String,
    val message: String,
    val userId: Int? = null
) {
    // Tambahkan 'get()' agar variabel ini memiliki nilai otomatis
    val success: Boolean get() = status == "success"
}