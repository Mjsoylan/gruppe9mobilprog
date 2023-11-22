package com.example.librarypluss_gruppe09.models

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.IgnoreExtraProperties


@IgnoreExtraProperties
data class UserAccunt(
    @DocumentId val documentid: String = "",
    val userid: String="",
    val username: String=""
)