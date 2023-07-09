package com.dor.mydictionary.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.MenuOpen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun WordsTopAppBar(
    openDrawer: () -> Unit,
    onFilterAllWords: () -> Unit,
    onFilterFavoriteWords: () -> Unit,
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(MaterialTheme.colors.primaryVariant)
    TopAppBar(
        title = { Text(text = "Words") },
        navigationIcon = {
            IconButton(onClick = openDrawer) {
                Icon(Icons.Filled.MenuOpen, "Open Menu")
            }
        },
        actions = {
            FilterTasksMenu(onFilterAllWords, onFilterFavoriteWords)
        },
        modifier = Modifier
            .fillMaxWidth(),
        backgroundColor = MaterialTheme.colors.primaryVariant
    )
}

@Composable
private fun FilterTasksMenu(
    onFilterAllTasks: () -> Unit,
    onFilterActiveTasks: () -> Unit
) {
    TopAppBarDropdownMenu(
        iconContent = {
            Icon(Icons.Filled.FilterList, "Filter")
        }
    ) { closeMenu ->
        DropdownMenuItem(onClick = { onFilterAllTasks(); closeMenu() }) {
            Text(text = "All")
        }
        DropdownMenuItem(onClick = { onFilterActiveTasks(); closeMenu() }) {
            Text(text = "Favorites")
        }
    }
}

@Composable
private fun TopAppBarDropdownMenu(
    iconContent: @Composable () -> Unit,
    content: @Composable ColumnScope.(() -> Unit) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier.wrapContentSize(Alignment.TopEnd)) {
        IconButton(onClick = { expanded = !expanded }) {
            iconContent()
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.wrapContentSize(Alignment.TopEnd)
        ) {
            content { expanded = !expanded }
        }
    }
}

@Composable
fun WordDetailTopAppBar(onBack: () -> Unit, onDelete: () -> Unit) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(MaterialTheme.colors.primaryVariant)
    TopAppBar(
        title = {
            Text(text = "Word details")
        },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(Icons.Filled.ArrowBack, "Back button")
            }
        },
        actions = {
            IconButton(onClick = onDelete) {
                Icon(Icons.Filled.Delete, "Delete word")
            }
        },
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = MaterialTheme.colors.primaryVariant
    )
}

@Composable
fun AddWordTopAppBar(onBack: () -> Unit) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(MaterialTheme.colors.primaryVariant)
    TopAppBar(
        title = { Text(text = "Add word") },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(Icons.Filled.ArrowBack, "Back button")
            }
        },
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = MaterialTheme.colors.primaryVariant
    )
}

@Preview
@Composable
private fun WordTopAppBarPreview() {
    MaterialTheme {
        Surface {
            WordsTopAppBar({}, {}, {})
        }
    }
}

@Preview
@Composable
private fun WordDetailTopAppBarPreview() {
    MaterialTheme {
        Surface {
            WordDetailTopAppBar({}, {})
        }
    }
}

@Preview
@Composable
private fun AddWordTopAppBarPreview() {
    MaterialTheme() {
        Surface {
            AddWordTopAppBar({})
        }
    }
}
