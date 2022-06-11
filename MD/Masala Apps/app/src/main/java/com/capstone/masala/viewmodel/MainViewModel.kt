package com.capstone.masala.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capstone.masala.api.ApiConfig
import com.capstone.masala.data.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel(){
    val dataSummarize = MutableLiveData<ArrayList<ListCategory>>()

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

    fun getSummarize(token: String){
        ApiConfig.getApiService()
            .getAll(token)
            .enqueue(object : Callback<SummarizeResponse> {
                override fun onResponse(
                    call: Call<SummarizeResponse>,
                    response: Response<SummarizeResponse>
                ) {
                    if (response.isSuccessful){
                        dataSummarize.postValue(response.body()?.AllSummarize)
                    }
                }

                override fun onFailure(call: Call<SummarizeResponse>, t: Throwable) {
                    Log.d("Failed", "Failed search data")

                }
            })
    }

    fun getListCategory(): LiveData<ArrayList<ListCategory>> {
        return dataSummarize
    }

}