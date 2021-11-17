package com.example.cleanarchitecturedictionaryapp.feature_dictionary.di

import android.app.Application
import androidx.room.Room
import com.example.cleanarchitecturedictionaryapp.feature_dictionary.data.local.Converters
import com.example.cleanarchitecturedictionaryapp.feature_dictionary.data.local.WordInfoDao
import com.example.cleanarchitecturedictionaryapp.feature_dictionary.data.local.WordInfoDatabase
import com.example.cleanarchitecturedictionaryapp.feature_dictionary.data.remote.DictionaryApi
import com.example.cleanarchitecturedictionaryapp.feature_dictionary.data.repository.WordRepositoryImpl
import com.example.cleanarchitecturedictionaryapp.feature_dictionary.data.util.GsonParser
import com.example.cleanarchitecturedictionaryapp.feature_dictionary.domain.repository.WordRepository
import com.example.cleanarchitecturedictionaryapp.feature_dictionary.domain.use_cases.GetWordInfo
import com.example.cleanarchitecturedictionaryapp.feature_dictionary.representation.WordInfoViewModel
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WordInfoModule {
    @Provides
    @Singleton
    fun provideGetWordInfoViewModel(wordInfoUseCase:GetWordInfo): WordInfoViewModel {
        return WordInfoViewModel(wordInfoUseCase)
    }

    @Provides
    @Singleton
    fun provideGetWordInfoUseCase(repository: WordRepository): GetWordInfo {
        return GetWordInfo(repository)
    }

    @Provides
    @Singleton
    fun providesWordRepository(api: DictionaryApi, db: WordInfoDatabase): WordRepository {
        return WordRepositoryImpl(api, db.dao)
    }


    @Provides
    @Singleton
    fun providesWordInfoDatabase(app: Application): WordInfoDatabase {
        return Room.databaseBuilder(
            app, WordInfoDatabase::class.java, "word_db"
        ).addTypeConverter(Converters(GsonParser(Gson())))
            .build()
    }


    @Provides
    @Singleton
    fun providesDictionaryApi(): DictionaryApi {
        return Retrofit.Builder()
            .baseUrl(DictionaryApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()

    }
}