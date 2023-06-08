package com.example.masterremote.data

import com.example.masterremote.domain.User

// Criando a base para consumir API

class UserRepository {

    fun getUser(): User {
        return User("admin", "admin")
    }
}