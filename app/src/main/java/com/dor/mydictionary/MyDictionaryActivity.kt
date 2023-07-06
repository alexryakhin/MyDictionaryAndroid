package com.dor.mydictionary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyDictionaryActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyDictionaryNavGraph()
        }
    }
}
