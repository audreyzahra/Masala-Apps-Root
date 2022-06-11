package com.capstone.masala.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.masala.R
import com.capstone.masala.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAboutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}