package com.dor.mydictionary.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

val primaryDarkColor: Color = Color(0xFF263238)

@Composable
fun LoadingContent(
    loading: Boolean,
    empty: Boolean,
    emptyContent: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    if (empty) {
        emptyContent()
    } else {
//        SwipeRefresh(
//            state = rememberSwipeRefreshState(loading),
//            onRefresh = onRefresh,
//            modifier = modifier,
//            content = content,
//        )
        emptyContent()
    }
}
