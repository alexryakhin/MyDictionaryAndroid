package com.dor.mydictionary.words

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FolderOpen
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dor.mydictionary.R
import com.dor.mydictionary.data.Word
import com.dor.mydictionary.util.WordsTopAppBar


@Composable
fun WordsScreen(
    @StringRes userMessage: Int,
    onAddWord: () -> Unit,
    onWordClick: (Word) -> Unit,
    onUserMessageDisplayed: () -> Unit,
    openDrawer: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: WordsViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            WordsTopAppBar(
                openDrawer = openDrawer,
                onFilterAllWords = {  },
                onFilterFavoriteWords = {  }
            )
        },
        modifier = modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(onClick = onAddWord) {
                Icon(Icons.Filled.Add, stringResource(id = R.string.add_word))
            }
        }
    ) { paddingValues ->
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    
        WordsList(
            loading = uiState.isLoading,
            words = uiState.items,
            onWordClick = onWordClick,
            modifier = Modifier
                .padding(paddingValues)
        )

        // Check if there's a userMessage to show to the user
        val currentOnUserMessageDisplayed by rememberUpdatedState(onUserMessageDisplayed)
        LaunchedEffect(userMessage) { }
    }
}

@Composable
private fun WordsList(
    loading: Boolean,
    words: List<Word>,
    onWordClick: (Word) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(words) { word ->
            WordItem(
                word = word,
                onWordClick = onWordClick,
            )
        }
    }
}

@Composable
private fun WordItem(
    word: Word,
    onWordClick: (Word) -> Unit
) {
    Column {
        Row(
            modifier = Modifier
                .clickable { onWordClick(word) }
                .padding(all = 12.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = word.value,
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (word.isFavorite) {
                    Icon(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = "Favorite",
                        tint = MaterialTheme.colors.secondaryVariant,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                }
                Text(text = word.partOfSpeech, color = Color.DarkGray)
                Icon(
                    imageVector = Icons.Default.ChevronRight,
                    contentDescription = "ChevronRight",
                    tint = Color.LightGray
                )
            }
        }
        Divider(modifier = Modifier.height(0.66.dp))
    }
}

@Composable
private fun WordsEmptyContent(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            imageVector = Icons.Default.FolderOpen,
            contentDescription = stringResource(R.string.no_words_image_content_description),
            modifier = Modifier.size(96.dp)
        )
        Text(stringResource(id = R.string.no_words_all))
    }
}

@Preview
@Composable
private fun TasksContentPreview() {
    MaterialTheme {
        Surface {
            WordsList(
                loading = false,
                words = listOf(
                    Word(
                        value = "Word",
                        definition = "Description",
                        partOfSpeech = "noun",
                        isFavorite = true
                    ),
                    Word(
                        value = "Word2",
                        definition = "Description2",
                        partOfSpeech = "noun",
                        isFavorite = true
                    ),
                    Word(
                        value = "Word3",
                        definition = "Description",
                        partOfSpeech = "noun"
                    ),
                    Word(
                        value = "Word4",
                        definition = "Description",
                        partOfSpeech = "noun"
                    ),
                    Word(
                        value = "Word5",
                        definition = "Description",
                        partOfSpeech = "noun"
                    ),
                ),
                onWordClick = { },
            )
        }
    }
}

@Preview
@Composable
private fun TasksContentEmptyPreview() {
    MaterialTheme {
        Surface {
            WordsList(
                loading = false,
                words = emptyList(),
                onWordClick = {}
            )
        }
    }
}

@Preview
@Composable
private fun TasksEmptyContentPreview() {
    MaterialTheme {
        Surface {
            WordsEmptyContent()
        }
    }
}

@Preview
@Composable
private fun WordItemPreview() {
    MaterialTheme {
        Surface {
            WordItem(
                word = Word(
                    value = "Word",
                    definition = "Description",
                    partOfSpeech = "noun"
                ),
                onWordClick = {}
            )
        }
    }
}

@Preview
@Composable
private fun WordItemFavoritePreview() {
    MaterialTheme {
        Surface {
            WordItem(
                word = Word(
                    value = "Word",
                    definition = "Description",
                    partOfSpeech = "noun",
                    isFavorite = true
                ),
                onWordClick = {}
            )
        }
    }
}
