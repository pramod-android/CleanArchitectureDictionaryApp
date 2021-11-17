package com.example.cleanarchitecturedictionaryapp.feature_dictionary.representation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchitecturedictionaryapp.core.util.Resource
import com.example.cleanarchitecturedictionaryapp.feature_dictionary.domain.use_cases.GetWordInfo
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class WordInfoViewModel @Inject constructor(
    private val getWordInfo: GetWordInfo
):ViewModel(){
    private val _searchQuery = MutableLiveData("")
    val searchQuery:LiveData<String> = _searchQuery

    private val _wordInfo = MutableLiveData(WordInfoState())
    val wordInfo:LiveData<WordInfoState> = _wordInfo

    private  var searchJob: Job? = null

    fun onSearch(query:String){
        _searchQuery.value = query
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500L)
            getWordInfo(query)
                .onEach { result ->
                    when(result){
                        is Resource.Success -> {
                            _wordInfo.value = wordInfo.value?.copy(
                                result.data ?: emptyList(),false
                            )
                        }
                        is Resource.Error -> {
                            _wordInfo.value = wordInfo.value?.copy(
                                result.data ?: emptyList(),false
                            )
                        }
                        is Resource.Loading -> {
                            _wordInfo.value = wordInfo.value?.copy(
                                result.data ?: emptyList(),true
                            )
                        }
                    }
                }.launchIn(this)
        }

    }


}