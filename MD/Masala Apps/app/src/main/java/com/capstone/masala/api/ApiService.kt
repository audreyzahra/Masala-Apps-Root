package com.capstone.masala.api

import com.capstone.masala.data.*
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @POST("/user/login")
    fun userLogin(
        @Body loginRequest: LoginRequest
    ): Call<LoginResponse>

    @POST("/user/register")
    fun userRegister(
        @Body registerRequest: RegisterRequest
    ): Call<RegisterResponse>

    @GET("/summarize/getsummarizelist")
    fun getAll(
        @Header("x-access-token") token : String?
    ) : Call<SummarizeResponse>

    @GET("/summarize/getsummarize/{category}")
    fun getTweet(
        @Header("x-access-token") token : String?,
        @Path("category") category: String
    ) : Call<TweetByCategoryResponse>
}