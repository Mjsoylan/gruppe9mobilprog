package com.example.librarypluss_gruppe09.models

import com.google.firebase.firestore.DocumentId

data class Media(@DocumentId val mediaId: String = "", val tittle: String = "", val creator : String = "", val type : String = "", val tag : String = "")
