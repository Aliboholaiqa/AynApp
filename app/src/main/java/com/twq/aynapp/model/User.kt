package com.twq.aynapp.model

import java.io.Serializable

data class User(
    val avatar: String,
    val bio: String,
    val email: String,
    val fb_id: String,
    val header: String,
    val id: String,
    val password: String,
    val username: String
): Serializable