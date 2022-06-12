package com.capstone.masala.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.anychart.AnyChart
import com.anychart.AnyChartView
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.charts.Pie
import com.capstone.masala.R
import com.capstone.masala.data.ListCategory
import com.capstone.masala.data.ListTweet
import com.capstone.masala.data.SummarizeResponse
import com.capstone.masala.databinding.ActivityKategoriBinding
import com.capstone.masala.helper.Constant
import com.capstone.masala.helper.ListCategoryAdapter
import com.capstone.masala.helper.PreferenceHelper
import com.capstone.masala.viewmodel.MainViewModel

class KategoriActivity : AppCompatActivity() {
    private lateinit var binding : ActivityKategoriBinding
    private lateinit var sharedPreferences: PreferenceHelper
    private lateinit var mainViewModel: MainViewModel
    private lateinit var adapter: ListCategoryAdapter
    private lateinit var chart: AnyChartView
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKategoriBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#FFFFFF")))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        adapter = ListCategoryAdapter()
        adapter.notifyDataSetChanged()
        sharedPreferences = PreferenceHelper(this)

        chart = binding.chartView

        mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[MainViewModel::class.java]

        adapter.setOnItemClickCallback(object : ListCategoryAdapter.OnItemClickCallback {
            override fun onItemClicked(data: ListCategory) {
                Intent(this@KategoriActivity, TweetActivity::class.java).also {
                    it.putExtra(Constant.CATEGORY, data.category)
                    startActivity(it)
                }
            }

        })
        getAllSummarize()
    }
    private fun getAllSummarize() {
        showLoading(true)
        binding.rvCategory.layoutManager = LinearLayoutManager(this)
        binding.rvCategory.setHasFixedSize(true)
        binding.rvCategory.adapter = adapter
        val token: String = sharedPreferences.getToken(Constant.TOKEN).toString()
        mainViewModel.getSummarize(token)

        val pie: Pie = AnyChart.pie()
        val dataPieChart: MutableList<DataEntry> = mutableListOf()

        mainViewModel.getListCategory().observe(this){
            if (it != null){
                adapter.setList(it)
                val datas = it

                for (data in datas){
                    dataPieChart.add(ValueDataEntry(data.category, data.SummarizeByCategory.count()))
                }
                pie.data(dataPieChart)
                pie.title("Data by Category")
                chart.setChart(pie)
                showLoading(false)
            }

            if (it.isEmpty()){
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