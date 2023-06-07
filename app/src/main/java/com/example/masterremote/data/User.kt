package com.example.masterremote.data

data class User(
    val username: String,
    val password: String,
)

val defaultUser = User("admin", "admin")