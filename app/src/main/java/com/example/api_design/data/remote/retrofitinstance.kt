package com.example.api_design.data.remote

import android.content.Context
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private var retrofit: Retrofit? = null
    private var currentBaseUrl: String? = null

    private fun createOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()
    }


    // Add this helper function to RetrofitClient.kt or a NetworkUtils file
    fun isWifiConnected(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as android.net.ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return capabilities.hasTransport(android.net.NetworkCapabilities.TRANSPORT_WIFI)
    }

    // Lokasi: app/src/main/java/com/example/api_design/data/remote/retrofitinstance.kt

    fun getBaseUrl(context: Context): String {
        val prefs = context.getSharedPreferences("api_prefs", Context.MODE_PRIVATE)
        // GANTI DI SINI
        val savedIp = prefs.getString("server_ip", "10.67.116.88") ?: "10.67.116.88"

        val cleanIp = savedIp.trim()
            .replace("http://", "")
            .replace("https://", "")
            .trimEnd('/')

        return if (cleanIp.contains(":")) "http://$cleanIp/" else "http://$cleanIp:80/"
    }


    fun getInstance(context: Context): ApiService {
        val baseUrl = getBaseUrl(context)

        if (retrofit == null || currentBaseUrl != baseUrl) {
            currentBaseUrl = baseUrl
            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(createOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!.create(ApiService::class.java)
    }
}
