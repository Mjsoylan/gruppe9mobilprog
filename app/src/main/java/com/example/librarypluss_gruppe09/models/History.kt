package com.example.librarypluss_gruppe09.models

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.IgnoreExtraProperties


//todo add date for when added to history
@IgnoreExtraProperties
data class History(@DocumentId val mediaId: String = "", val tittle: String = "", val creator : String = "", val type : String = "", val tag : String = "", val imageUrl: String = "")
