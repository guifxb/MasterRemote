package com.example.masterremote.domain

data class User(
    val username: String,
    val password: String,
)

val defaultUser = User("admin", "admin")