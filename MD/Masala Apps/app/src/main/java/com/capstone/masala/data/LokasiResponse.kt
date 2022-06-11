package com.capstone.masala.data
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class LokasiResponse (
    var status : Boolean? = null,
    var message : String? = null,
    var kategori : String? =null,
    var summarizeByCategory : ArrayList<ListLocation>
)

data class ListLocation(
    val location : String? = null,
    val summarizeByLocation : ArrayList<ListTweet>
)




