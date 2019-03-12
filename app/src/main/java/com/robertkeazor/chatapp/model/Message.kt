package com.robertkeazor.chatapp.model

data class Message(
    val userName: String,
    val message: String,
    val userId: String,
    val isUser: Boolean) {
    constructor() : this("", "", "", false)
}
