package com.capstone.masala.api

import com.capstone.masala.data.*
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @POST("/summarize/login")
    fun userLogin(
        @Body loginRequest: LoginRequest
    ): Call<LoginResponse>

    @POST("/summarize/register")
    fun userRegister(
        @Body registerRequest: RegisterRequest
    ): Call<RegisterResponse>

    @GET("/summarize/getsummarizelist")
    fun getAll(
        @Header("x-access-token") token : String?
    ) : Call<SummarizeResponse>

}