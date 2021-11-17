package com.example.cleanarchitecturedictionaryapp.feature_dictionary.domain.model

import com.example.cleanarchitecturedictionaryapp.feature_dictionary.data.remote.dto.MeaningDto
import com.example.cleanarchitecturedictionaryapp.feature_dictionary.data.remote.dto.PhoneticDto

class WordInfo(
    val meanings: List<Meaning>,
    val origin: String,
    val phonetic: String,
    val word: String
) {
}