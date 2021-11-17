package com.example.cleanarchitecturedictionaryapp.feature_dictionary.representation

import com.example.cleanarchitecturedictionaryapp.feature_dictionary.domain.model.WordInfo

data class WordInfoState(
    val wordInfoList: List<WordInfo> = emptyList(),
    val isLoading:Boolean = false
)