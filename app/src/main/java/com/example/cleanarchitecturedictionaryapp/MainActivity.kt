package com.example.cleanarchitecturedictionaryapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.cleanarchitecturedictionaryapp.feature_dictionary.representation.WordInfoViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModel:WordInfoViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.onSearch("bank")

        viewModel.wordInfo.observe(this,{
            Log.e("TAG", "onCreate: "+it.wordInfoList )
        })
    }
}