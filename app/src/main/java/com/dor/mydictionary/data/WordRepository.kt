package com.dor.mydictionary.data

import kotlinx.coroutines.flow.Flow

interface WordRepository {
    fun getWordsStream(): Flow<List<Word>>

    suspend fun getWords(forceUpdate: Boolean = false): List<Word>

    suspend fun refresh()

    fun getWordsStream(wordId: String): Flow<Word?>

    suspend fun getWord(wordId: String, forceUpdate: Boolean = false): Word?

    suspend fun refreshWord(wordId: String)

    suspend fun createWord(title: String, description: String): String

    suspend fun updateWord(wordId: String, title: String, description: String)

    suspend fun markWordAsFavorite(wordId: String)

    suspend fun activateWord(wordId: String)

    suspend fun clearCompletedWords()

    suspend fun deleteAllWords()

    suspend fun deleteWord(wordId: String)
}