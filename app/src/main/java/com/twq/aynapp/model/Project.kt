package com.twq.aynapp.model

import com.google.firebase.firestore.DocumentId
import java.io.Serializable

data class Project(
    @DocumentId
    val createdAt: String,
    val description: String,
    val id: String? = "",
    val image: String?,
    val projectTitle: String,
    val userId: String
):Serializable