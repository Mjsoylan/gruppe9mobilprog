package com.example.librarypluss_gruppe09.models

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.IgnoreExtraProperties


//todo add date for when added to history
@IgnoreExtraProperties
data class History(@DocumentId val historyId: String = "", val previousvalue: String = "", val date : String = "")
