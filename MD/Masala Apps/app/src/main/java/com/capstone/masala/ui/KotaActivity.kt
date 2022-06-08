package com.capstone.masala.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.anychart.AnyChart
import com.anychart.AnyChartView
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.charts.Pie
import com.capstone.masala.R
import com.capstone.masala.databinding.ActivityKotaBinding

class KotaActivity : AppCompatActivity() {

    private val kota = listOf("jakarta","bandung","surabaya","semarang","yogyakarta")
    private val jumlah = listOf(200,300,150,234,312)
    private lateinit var chart: AnyChartView

    private lateinit var binding: ActivityKotaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKotaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        chart = binding.chartView
        startChart()

    }

    private fun startChart() {
        val pie:Pie = AnyChart.pie()

        val dataPieChart: MutableList<DataEntry> = mutableListOf()

        for (index in jumlah.indices){
            dataPieChart.add(ValueDataEntry(kota.elementAt(index), jumlah.elementAt(index)))
        }

        pie.data(dataPieChart)
        pie.title("Data Kota")
        chart.setChart(pie)
    }
}