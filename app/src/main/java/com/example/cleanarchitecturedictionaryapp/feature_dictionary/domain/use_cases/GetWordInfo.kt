package com.example.cleanarchitecturedictionaryapp.feature_dictionary.domain.use_cases

import com.example.cleanarchitecturedictionaryapp.core.util.Resource
import com.example.cleanarchitecturedictionaryapp.feature_dictionary.domain.model.WordInfo
import com.example.cleanarchitecturedictionaryapp.feature_dictionary.domain.repository.WordRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetWordInfo(private val repository: WordRepository) {

    operator fun invoke(word:String): Flow<Resource<List<WordInfo>>>{
        if(word.isBlank()){
            return flow {  }
        }
        return repository.getWordInfo(word)
    }
}