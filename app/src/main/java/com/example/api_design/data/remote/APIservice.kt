package com.example.api_design.data.remote

import com.example.api_design.data.model.LoginResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("my_api/connection.php") // file php nia naran
    suspend fun loginUser(
        @Field("username") user: String,
        @Field("password") pass: String
    ): LoginResponse

    @FormUrlEncoded
    @POST("my_api/register.php") // bolu file register.php
    suspend fun registerUser(
        @Field("username") user: String,
        @Field("name") name: String,
        @Field("password") pass: String
    ): LoginResponse
}
