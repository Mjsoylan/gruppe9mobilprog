package com.example.librarypluss_gruppe09.models

import com.google.firebase.firestore.DocumentId

data class Media(@DocumentId
                 val mediaId: String = "",
                 val tittle: String = "",  //tittle !
                 val creator : String = "",  //lager
                 val type : String = "", // changer
                 val tag : String = "") // type (movies books games) !
{
    val imageUrl: String = ""
}

