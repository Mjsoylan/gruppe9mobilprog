package com.example.librarypluss_gruppe09.models

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class Media(
    @DocumentId val mediaId: String = "",
    var tittle: String = "",
    val creator : String = "",
    val type : String = "",
    val tag : String = "",
    val imageUrl: String = "")


