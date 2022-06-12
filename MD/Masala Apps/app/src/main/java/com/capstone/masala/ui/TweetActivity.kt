package com.capstone.masala.ui

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.masala.databinding.ActivityTweetBinding
import com.capstone.masala.helper.Constant
import com.capstone.masala.helper.ListTweetAdapter
import com.capstone.masala.helper.PreferenceHelper
import com.capstone.masala.viewmodel.MainViewModel

class TweetActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTweetBinding
    private lateinit var adapter: ListTweetAdapter
    private lateinit var sharedPreferences: PreferenceHelper
    private lateinit var mainViewModel: MainViewModel

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTweetBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val category = intent.getStringExtra(Constant.CATEGORY)

        supportActionBar?.title = category

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#FFFFFF")))
        sharedPreferences = PreferenceHelper(this)
        mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[MainViewModel::class.java]

        adapter = ListTweetAdapter()
        adapter.notifyDataSetChanged()
        getTweet()
    }

    private fun getTweet() {
        showLoading(true)
        binding.rvTweet.layoutManager = LinearLayoutManager(this)
        binding.rvTweet.setHasFixedSize(true)
        binding.rvTweet.adapter = adapter
        val token: String = sharedPreferences.getToken(Constant.TOKEN).toString()
        val category = intent.getStringExtra(Constant.CATEGORY)
        if (category != null) {
            mainViewModel.getTweetList(token, category)
        }

        mainViewModel.getListTweet().observe(this){
            if (it !=null){
                adapter.setList(it)
            }else{
                showLoading(false)
                Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show()
            }
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