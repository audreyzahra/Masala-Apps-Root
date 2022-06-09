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

data class ListTweet (
    val username : String? = null,
    val tweet : String? = null,
    val summarize : String? = null,
    val typeSummarize : String? = null,
    val time : String
)



