package com.capstone.masala.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.capstone.masala.R
import com.capstone.masala.databinding.ActivityHomeBinding
import com.capstone.masala.helper.PreferenceHelper

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var sharedPreferences: PreferenceHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = PreferenceHelper(this)


        binding.cv1.setOnClickListener {
            startActivity(Intent(this, KotaActivity::class.java))
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