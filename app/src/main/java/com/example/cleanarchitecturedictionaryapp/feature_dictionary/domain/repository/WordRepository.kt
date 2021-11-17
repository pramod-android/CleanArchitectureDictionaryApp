package com.example.cleanarchitecturedictionaryapp.feature_dictionary.domain.repository

import com.example.cleanarchitecturedictionaryapp.core.util.Resource
import com.example.cleanarchitecturedictionaryapp.feature_dictionary.domain.model.WordInfo
import kotlinx.coroutines.flow.Flow

interface WordRepository {

    fun getWordInfo(wordString: String): Flow<Resource<List<WordInfo>>>
}