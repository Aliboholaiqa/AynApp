package com.twq.aynapp.model

import java.io.Serializable

data class Project(
    val createdAt: String,
    val description: String,
    val id: String,
    val image: String,
    val projectTitle: String
): Serializable