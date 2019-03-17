package com.robertkeazor.chatapp.model

data class User(val userName: String, val userEmail: String, val userId: String) {
    constructor(): this(userName ="", userEmail = "", userId = "")
}
