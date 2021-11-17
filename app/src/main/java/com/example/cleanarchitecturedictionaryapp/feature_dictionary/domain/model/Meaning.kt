package com.example.cleanarchitecturedictionaryapp.feature_dictionary.domain.model

import com.example.cleanarchitecturedictionaryapp.feature_dictionary.data.remote.dto.DefinitionDto

class Meaning(
    val definitions: List<Definition>,
    val partOfSpeech: String
) {
}