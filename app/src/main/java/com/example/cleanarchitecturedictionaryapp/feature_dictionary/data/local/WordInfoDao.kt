package com.example.cleanarchitecturedictionaryapp.feature_dictionary.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.cleanarchitecturedictionaryapp.feature_dictionary.data.local.entity.WordInfoEntity
import com.example.cleanarchitecturedictionaryapp.feature_dictionary.domain.model.WordInfo

@Dao
interface WordInfoDao {
    @Insert(onConflict = REPLACE)
    suspend fun insertWordInfoList(infoList:List<WordInfoEntity>)

    @Query("DELETE FROM wordinfoentity WHERE word IN(:words)")
    suspend fun deleteWordInFoList(words:List<String>)

    @Query("SELECT * FROM wordinfoentity WHERE word LIKE '%' || :word ||'%' ")
    suspend fun getWordInfoList(word:String):List<WordInfoEntity>
}