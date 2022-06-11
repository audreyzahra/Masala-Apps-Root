package com.capstone.masala.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.masala.R
import com.capstone.masala.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}