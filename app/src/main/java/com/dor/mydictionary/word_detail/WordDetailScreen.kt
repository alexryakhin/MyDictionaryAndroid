package com.dor.mydictionary.word_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dor.mydictionary.data.Word
import com.dor.mydictionary.util.WordDetailTopAppBar

@Composable
fun WordDetailScreen(
    onBack: () -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier,
//    viewModel: WordDetailViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {
    Scaffold(
        scaffoldState = scaffoldState,
        modifier = modifier.fillMaxSize(),
        topBar = {
            WordDetailTopAppBar(
                onBack = onBack,
                onDelete = { }
            )
        }
    ) { paddingValues ->
//        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
//
        val word = Word(value = "Revelation", definition = "The act of revealing", partOfSpeech = "noun")
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    word.value,
                    style = MaterialTheme.typography.h4,
                    fontWeight = FontWeight.Bold
                )
            }

        }
//        EditTaskContent(
//            loading = uiState.isLoading,
//            empty = uiState.task == null && !uiState.isLoading,
//            task = uiState.task,
//            onRefresh = viewModel::refresh,
//            onTaskCheck = viewModel::setCompleted,
//            modifier = Modifier.padding(paddingValues)
//        )
//
//        // Check for user messages to display on the screen
//        uiState.userMessage?.let { userMessage ->
//            val snackbarText = stringResource(userMessage)
//            LaunchedEffect(scaffoldState, viewModel, userMessage, snackbarText) {
//                scaffoldState.snackbarHostState.showSnackbar(snackbarText)
//                viewModel.snackbarMessageShown()
//            }
//        }
//
//        // Check if the task is deleted and call onDeleteTask
//        LaunchedEffect(uiState.isTaskDeleted) {
//            if (uiState.isTaskDeleted) {
//                onDeleteTask()
//            }
//        }
    }
}

@Preview
@Composable
fun WordDetailPreview() {
    WordDetailScreen(onBack = { /*TODO*/ }, onDelete = { /*TODO*/ })
}