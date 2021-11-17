package com.example.cleanarchitecturedictionaryapp.feature_dictionary.data.remote.dto

import com.example.cleanarchitecturedictionaryapp.feature_dictionary.domain.model.Phonetic

data class PhoneticDto(
    val audio: String,
    val text: String
){
    fun toPhonetic():Phonetic{
        return Phonetic(
            audio =audio,
            text=text
        )
    }
}