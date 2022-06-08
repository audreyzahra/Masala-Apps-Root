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
import com.capstone.masala.data.RegisterRequest
import com.capstone.masala.data.RegisterResponse
import com.capstone.masala.databinding.ActivityRegisterBinding
import com.capstone.masala.viewmodel.MainViewModel
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var edtPassword : PasswordEditText
    private lateinit var edtEmail : EmailEditText
    private lateinit var btnRegister : Button
    private lateinit var mainViewModel : MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        edtEmail = binding.edtEmail
        edtPassword = binding.edtPassword
        btnRegister = binding.btnRegister

        mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[MainViewModel::class.java]

        edtEmail.addTextChangedListener(object  : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                setLoginButton()
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

        edtPassword.addTextChangedListener(object  : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                setLoginButton()
            }
            override fun afterTextChanged(p0: Editable?) {
            }
        })

        binding.btnRegister.setOnClickListener {
            register()
        }
        binding.tvGoLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private fun setLoginButton() {
        val email = edtEmail.text.toString()
        if(edtPassword.length() >= 6 &&  Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            btnRegister.isEnabled = true
        }
    }

    private fun register() {
        val name = binding.edtName.text.toString()
        val email = binding.edtEmail.text.toString()
        val password = binding.edtPassword.text.toString()

        val result = mainViewModel.register(name, email, password)
        result.enqueue(object  : Callback<RegisterResponse>{
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                if (response.isSuccessful){
                    showLoading(false)
                    Toast.makeText(applicationContext, "Berhasil membuat akun", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }else{
                    Toast.makeText(this@RegisterActivity, "Error silahkan coba lagi", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                Log.d("Failed", "Retrofit Gagal")
            }

        })
    }

    private fun showLoading(state: Boolean){
        if (state){
            binding.progressBar.visibility = View.VISIBLE
        }else{
            binding.progressBar.visibility = View.GONE
        }
    }
}