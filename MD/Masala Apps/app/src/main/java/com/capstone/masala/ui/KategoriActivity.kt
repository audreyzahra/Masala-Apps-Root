package com.capstone.masala.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.masala.R
import com.capstone.masala.databinding.ActivityKategoriBinding

class KategoriActivity : AppCompatActivity() {
    private lateinit var binding : ActivityKategoriBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKategoriBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}