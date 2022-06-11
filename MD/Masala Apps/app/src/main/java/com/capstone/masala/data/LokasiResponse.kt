package com.capstone.masala.data

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




