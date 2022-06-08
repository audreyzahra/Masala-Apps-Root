package com.capstone.masala.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.masala.R
import com.capstone.masala.databinding.ActivityTweetBinding

class TweetActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTweetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTweetBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}