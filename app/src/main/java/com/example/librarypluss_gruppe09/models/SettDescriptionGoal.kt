package com.example.librarypluss_gruppe09.models

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.IgnoreExtraProperties

//Todo Marius add a fild if you want to, eks where to find/watsh media
@IgnoreExtraProperties
data class SettDescriptionGoal(@DocumentId val goalId: String = "", val description: String = "")
