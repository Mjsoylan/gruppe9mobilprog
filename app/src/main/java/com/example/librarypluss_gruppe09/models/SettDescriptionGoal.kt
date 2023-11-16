package com.example.librarypluss_gruppe09.models

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class SettDescriptionGoal(@DocumentId val goalId: String = "", val description: String = "")
