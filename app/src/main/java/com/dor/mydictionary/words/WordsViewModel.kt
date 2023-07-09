package com.dor.mydictionary.words

import androidx.compose.ui.text.font.FontLoadingStrategy.Companion.Async
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dor.mydictionary.R
import com.dor.mydictionary.data.Word
import com.dor.mydictionary.data.WordRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

data class WordsUiState(
    val items: List<Word> = emptyList(),
    val isLoading: Boolean = false,
    val filteringUiInfo: FilteringUiInfo = FilteringUiInfo(),
    val userMessage: Int? = null
)

@HiltViewModel
class WordsViewModel @Inject constructor(
//    private val wordRepository: WordRepository,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _savedFilterType =
        savedStateHandle.getStateFlow(WORDS_FILTER_SAVED_STATE_KEY, WordsFilterType.ALL_WORDS)

//    private val _filterUiInfo = _savedFilterType.map { getFilterUiInfo(it) }.distinctUntilChanged()
    private val _userMessage: MutableStateFlow<Int?> = MutableStateFlow(null)
    private val _isLoading = MutableStateFlow(false)
//    private val _filteredTasksAsync =
//        combine(taskRepository.getTasksStream(), _savedFilterType) { words, type ->
//            filterTasks(words, type)
//        }
//            .map { Async.Success(it) }
//            .catch<Async<List<Task>>> { emit(Async.Error(R.string.loading_tasks_error)) }

    val uiState: StateFlow<WordsUiState> = MutableStateFlow(value = WordsUiState(
        items = listOf(
            Word(value = "Word", definition = "Definition", partOfSpeech = "noun"),
            Word(value = "Word1", definition = "Definition", partOfSpeech = "noun", isFavorite = true),
            Word(value = "Word2", definition = "Definition", partOfSpeech = "noun", isFavorite = true),
            Word(value = "Word3", definition = "Definition", partOfSpeech = "noun"),
            Word(value = "Word4", definition = "Definition", partOfSpeech = "noun"),
            Word(value = "Word5", definition = "Definition", partOfSpeech = "noun", isFavorite = true),
            Word(value = "Word6", definition = "Definition", partOfSpeech = "noun"),
            Word(value = "Word7", definition = "Definition", partOfSpeech = "noun", isFavorite = true),
            Word(value = "Word8", definition = "Definition", partOfSpeech = "noun"),
            Word(value = "Word9", definition = "Definition", partOfSpeech = "noun"),
            Word(value = "Word10", definition = "Definition", partOfSpeech = "noun", isFavorite = true),
            Word(value = "Word11", definition = "Definition", partOfSpeech = "noun"),
            Word(value = "Word12", definition = "Definition", partOfSpeech = "noun"),
            Word(value = "Word13", definition = "Definition", partOfSpeech = "noun", isFavorite = true),
            Word(value = "Word14", definition = "Definition", partOfSpeech = "noun"),
            Word(value = "Word15", definition = "Definition", partOfSpeech = "noun", isFavorite = true),
            Word(value = "Word16", definition = "Definition", partOfSpeech = "noun"),
            Word(value = "Word17", definition = "Definition", partOfSpeech = "noun", isFavorite = true),
            Word(value = "Word18", definition = "Definition", partOfSpeech = "noun"),
            Word(value = "Word19", definition = "Definition", partOfSpeech = "noun"),
            Word(value = "Word20", definition = "Definition", partOfSpeech = "noun", isFavorite = true),
            Word(value = "Word21", definition = "Definition", partOfSpeech = "noun", isFavorite = true),
            Word(value = "Word22", definition = "Definition", partOfSpeech = "noun"),
            Word(value = "Word23", definition = "Definition", partOfSpeech = "noun")
        ),
        filteringUiInfo = FilteringUiInfo(
            currentFilteringLabel = R.string.label_all, noWordsLabel = R.string.no_words_all
        ),
        isLoading = false,
        userMessage = null
    )
    )

//    fun setFiltering(requestType: TasksFilterType) {
//        savedStateHandle[TASKS_FILTER_SAVED_STATE_KEY] = requestType
//    }

//    fun clearCompletedTasks() {
//        viewModelScope.launch {
//            taskRepository.clearCompletedTasks()
//            showSnackbarMessage(R.string.completed_tasks_cleared)
//            refresh()
//        }
//    }

//    fun completeTask(task: Task, completed: Boolean) = viewModelScope.launch {
//        if (completed) {
//            taskRepository.completeTask(task.id)
//            showSnackbarMessage(R.string.task_marked_complete)
//        } else {
//            taskRepository.activateTask(task.id)
//            showSnackbarMessage(R.string.task_marked_active)
//        }
//    }

//    fun showEditResultMessage(result: Int) {
//        when (result) {
//            EDIT_RESULT_OK -> showSnackbarMessage(R.string.successfully_saved_task_message)
//            ADD_EDIT_RESULT_OK -> showSnackbarMessage(R.string.successfully_added_task_message)
//            DELETE_RESULT_OK -> showSnackbarMessage(R.string.successfully_deleted_task_message)
//        }
//    }

    fun snackbarMessageShown() {
        _userMessage.value = null
    }

    private fun showSnackbarMessage(message: Int) {
        _userMessage.value = message
    }

//    fun refresh() {
//        _isLoading.value = true
//        viewModelScope.launch {
//            taskRepository.refresh()
//            _isLoading.value = false
//        }
//    }

//    private fun filterTasks(tasks: List<Task>, filteringType: TasksFilterType): List<Task> {
//        val tasksToShow = ArrayList<Task>()
//        // We filter the tasks based on the requestType
//        for (task in tasks) {
//            when (filteringType) {
//                ALL_TASKS -> tasksToShow.add(task)
//                ACTIVE_TASKS -> if (task.isActive) {
//                    tasksToShow.add(task)
//                }
//                COMPLETED_TASKS -> if (task.isCompleted) {
//                    tasksToShow.add(task)
//                }
//            }
//        }
//        return tasksToShow
//    }

//    private fun getFilterUiInfo(requestType: TasksFilterType): FilteringUiInfo =
//        when (requestType) {
//            ALL_TASKS -> {
//                FilteringUiInfo(
//                    R.string.label_all, R.string.no_tasks_all,
//                    R.drawable.logo_no_fill
//                )
//            }
//            ACTIVE_TASKS -> {
//                FilteringUiInfo(
//                    R.string.label_active, R.string.no_tasks_active,
//                    R.drawable.ic_check_circle_96dp
//                )
//            }
//            COMPLETED_TASKS -> {
//                FilteringUiInfo(
//                    R.string.label_completed, R.string.no_tasks_completed,
//                    R.drawable.ic_verified_user_96dp
//                )
//            }
//        }
}

// Used to save the current filtering in SavedStateHandle.
const val WORDS_FILTER_SAVED_STATE_KEY = "WORDS_FILTER_SAVED_STATE_KEY"

data class FilteringUiInfo(
    val currentFilteringLabel: Int = R.string.label_all,
    val noWordsLabel: Int = R.string.no_words_all,
)
