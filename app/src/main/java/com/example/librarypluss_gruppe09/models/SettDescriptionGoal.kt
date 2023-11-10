package com.example.librarypluss_gruppe09.models

import com.google.firebase.firestore.DocumentId

data class SettDescriptionGoal(@DocumentId val goalId: String = "", val description: String = "")
