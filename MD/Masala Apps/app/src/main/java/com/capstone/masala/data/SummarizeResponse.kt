package com.capstone.masala.data

data class SummarizeResponse(
    var status : String? = null,
    var message : String? = null,
    var AllSummarize : ArrayList<ListCategory>
)

data class ListCategory(
    val category : String? = null,
    val SummarizeByCategory : ArrayList<ListTweet>
)

