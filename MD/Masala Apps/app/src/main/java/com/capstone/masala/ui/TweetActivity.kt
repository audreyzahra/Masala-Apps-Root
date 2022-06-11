package com.capstone.masala.ui

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.masala.R
import com.capstone.masala.data.ListTweet
import com.capstone.masala.databinding.ActivityTweetBinding
import com.capstone.masala.helper.Constant
import com.capstone.masala.helper.ListCategoryAdapter
import com.capstone.masala.helper.ListTweetAdapter
import java.util.ArrayList

class TweetActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTweetBinding
    private lateinit var adapter: ListTweetAdapter

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTweetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#FFFFFF")))

        adapter = ListTweetAdapter()
        adapter.notifyDataSetChanged()

        val category = intent.getStringExtra(Constant.CATEGORY)
//        val tweet = intent.getStringArrayListExtra(Constant.DATA_TWEET)

    }
}