package com.capstone.masala.ui

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.capstone.masala.R
import com.capstone.masala.databinding.ActivityHomeBinding
import com.capstone.masala.helper.PreferenceHelper
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var sharedPreferences: PreferenceHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = PreferenceHelper(this)
        supportActionBar?.title = "Welcome to Masala"
        supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#FFFFFF")))


        val imageList = ArrayList<SlideModel>()
        imageList.add(SlideModel(R.drawable.sosial_media, "Bangkit Academy Led by Google.", ScaleTypes.CENTER_CROP))
        imageList.add(SlideModel(R.drawable.tweet_board, "Kampus Merdeka", ScaleTypes.CENTER_CROP))
        imageList.add(SlideModel(R.drawable.bg_tweet, "And people do that.", ScaleTypes.CENTER_CROP))

        val imageSlider = binding.imageSlider
        imageSlider.setImageList(imageList)


        binding.cv1.setOnClickListener {
            startActivity(Intent(this, KategoriActivity::class.java))
        }

        binding.cvSearch.setOnClickListener {
            startActivity(Intent(this, SearchActivity::class.java))
        }

        binding.cvAbout.setOnClickListener {
            startActivity(Intent(this, AboutActivity::class.java))
        }

        binding.btnLogout.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Are you sure?")
                .setMessage("Do you want to Logout")
                .setPositiveButton("Ya") { _, _ ->
                    logout()
                    Toast.makeText(
                        applicationContext,
                        "Log out Berhasil",
                        Toast.LENGTH_LONG
                    ).show()
                }
                .setNegativeButton("No") { _, _ ->
                }
                .show()
        }
    }

    private fun logout() {
        sharedPreferences.clear()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}