package com.dor.mydictionary.data

import java.util.UUID

data class Word(
    var value: String,
    var definition: String,
    var examples: List<String> = emptyList(),
    var isFavorite: Boolean = false,
    var partOfSpeech: String,
    var phonetic: String? = null,
    val timestamp: Long = System.currentTimeMillis(),
    var id: String = UUID.randomUUID().toString()
)
