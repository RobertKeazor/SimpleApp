package com.robertkeazor.chatapp.model

data class Message(
    val userName: String,
    val message: String) {
    constructor() : this("", "")
}
