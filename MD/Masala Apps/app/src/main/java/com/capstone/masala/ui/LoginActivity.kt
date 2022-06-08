package com.capstone.masala.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.capstone.masala.R
import com.capstone.masala.customview.EmailEditText
import com.capstone.masala.customview.PasswordEditText
import com.capstone.masala.data.LoginResponse
import com.capstone.masala.databinding.ActivityLoginBinding
import com.capstone.masala.helper.Constant
import com.capstone.masala.helper.PreferenceHelper
import com.capstone.masala.viewmodel.MainViewModel
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var sharedPreferences: PreferenceHelper
    private lateinit var EdtPassword : PasswordEditText
    private lateinit var EdtEmail : EmailEditText
    private lateinit var btnLogin : Button
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        sharedPreferences = PreferenceHelper(this)
        mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[MainViewModel::class.java]

        EdtPassword = binding.edtPassword
        EdtEmail = binding.edtEmail
        btnLogin = binding.btnLogin

        binding.btnLogin.setOnClickListener {
            login()
        }

        EdtEmail.addTextChangedListener(object  : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                setLoginButton()
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
        EdtPassword.addTextChangedListener(object  : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                setLoginButton()
            }
            override fun afterTextChanged(p0: Editable?) {
            }
        })


    }

    private fun login() {
        showLoading(true)
        val email = binding.edtEmail.text.toString()
        val password = binding.edtPassword.text.toString()
        val result = mainViewModel.login(email, password)
        result.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful){
                    response.body()?.let { sharedPreferences.put(it.token.toString())}
                    sharedPreferences.putLogin(Constant.IS_LOGIN, true)
                    val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                    startActivity(intent)
                    showLoading(false)
                    finish()
                    Toast.makeText(applicationContext, "Login Berhasil", Toast.LENGTH_SHORT).show()
                }else{
                    val jObjError = JSONObject(response.errorBody()!!.string())
                    Toast.makeText(applicationContext, jObjError.getString("message"), Toast.LENGTH_SHORT).show()
                    Log.e("Failed", "Gagal Register")
                    showLoading(false)
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.d("Failed", "Retrofit Gagal")
            }
        })
    }

    private fun setLoginButton() {
        val email = EdtEmail.text.toString()
        if(EdtPassword.length() >= 6 &&  Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            btnLogin.isEnabled = true
        }
    }

    private fun showLoading(state: Boolean){
        if (state){
            binding.progressBar.visibility = View.VISIBLE
        }else{
            binding.progressBar.visibility = View.GONE
        }
    }
}