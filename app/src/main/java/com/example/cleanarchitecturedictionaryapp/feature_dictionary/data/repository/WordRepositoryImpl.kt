package com.example.cleanarchitecturedictionaryapp.feature_dictionary.data.repository

import com.example.cleanarchitecturedictionaryapp.core.util.Resource
import com.example.cleanarchitecturedictionaryapp.feature_dictionary.data.local.WordInfoDao
import com.example.cleanarchitecturedictionaryapp.feature_dictionary.data.remote.DictionaryApi
import com.example.cleanarchitecturedictionaryapp.feature_dictionary.domain.model.WordInfo
import com.example.cleanarchitecturedictionaryapp.feature_dictionary.domain.repository.WordRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class WordRepositoryImpl(
    private val dictionaryApi: DictionaryApi,
    private val dao:WordInfoDao
): WordRepository {
    override fun getWordInfo(wordString: String): Flow<Resource<List<WordInfo>>> = flow{
        emit(Resource.Loading())
        val wordInfoList = dao.getWordInfoList(wordString).map { it.toWordInfo() }
        emit(Resource.Loading(wordInfoList))
        try {
            val remoteWordInfo = dictionaryApi.getWordInfo(wordString)
            dao.deleteWordInFoList(remoteWordInfo.map { it.word })
            dao.insertWordInfoList(remoteWordInfo.map { it.toWordInfoEntity() })
        }catch (e:HttpException){
            emit(Resource.Error(
                message = "Something went wrong",
                data = wordInfoList
            ))
        }catch (e:IOException){
            emit(Resource.Error(
                message = "Couldn't reach server, check your internet connection",
                data = wordInfoList
            ))
        }
        val newWordInfo = dao.getWordInfoList(wordString).map {it.toWordInfo() }

        emit(Resource.Success(newWordInfo))
    }

}