package com.example.librarypluss_gruppe09.screen.profile

import androidx.lifecycle.ViewModel
import com.example.librarypluss_gruppe09.models.Media
import javax.inject.Inject


class ProfileViewModel @Inject
constructor(private val media: Media) : ViewModel() {


    fun mediaListUpdate(){
        val names = mutableListOf<String>()
        names.add(media.tittle)

    }
}