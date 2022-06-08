package com.capstone.masala.data

data class RegisterResponse(
    var status : Boolean? = null,
    var message : String? = null,
    val newUser : NewUser? = null
)

data class NewUser(
    val userID : String,
    val name : String,
    val email : String,
    val password : String
)

