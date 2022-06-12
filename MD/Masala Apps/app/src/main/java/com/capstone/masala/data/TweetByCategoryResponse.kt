package com.capstone.masala.data

data class TweetByCategoryResponse (
    var status : Boolean? = null,
    var message : String? = null,
    var getSummarize : ArrayList<ListTweet>
)