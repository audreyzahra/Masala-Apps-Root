package com.capstone.masala.viewmodel

import androidx.lifecycle.ViewModel
import com.capstone.masala.api.ApiConfig
import com.capstone.masala.data.*
import retrofit2.Call

class MainViewModel: ViewModel(){

    fun login(email: String, password:String): Call<LoginResponse> {
        val request = LoginRequest()
        request.email = email
        request.password = password
        return ApiConfig.getApiService().userLogin(request)
    }

    fun register(name: String,email: String, password:String): Call<RegisterResponse> {
        val request = RegisterRequest()
        request.name = name
        request.email = email
        request.passwords = password
        return ApiConfig.getApiService().userRegister(request)
    }

    fun getAll(token: String): Call<SummarizeResponse>{
        return ApiConfig.getApiService().getAll(token)
    }
}