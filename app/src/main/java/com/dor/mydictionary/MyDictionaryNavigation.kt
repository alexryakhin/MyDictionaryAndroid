package com.dor.mydictionary

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.dor.mydictionary.MyDictionaryDestinationsArgs.WORD_ID_ARG
import com.dor.mydictionary.MyDictionaryScreens.WORDS_SCREEN
import com.dor.mydictionary.MyDictionaryScreens.WORD_DETAIL_SCREEN
import com.dor.mydictionary.MyDictionaryScreens.ADD_WORD_SCREEN

private object MyDictionaryScreens {
    const val WORDS_SCREEN = "words"
    const val WORD_DETAIL_SCREEN = "word"
    const val ADD_WORD_SCREEN = "addWord"
}

object MyDictionaryDestinationsArgs {
    const val WORD_ID_ARG = "wordId"
}

object MyDictionaryDestinations {
    const val WORDS_ROUTE = WORDS_SCREEN
    const val WORD_DETAIL_ROUTE = "$WORD_DETAIL_SCREEN/{$WORD_ID_ARG}"
    const val ADD_WORD_ROUTE = ADD_WORD_SCREEN
}

class MyDictionaryNavigationActions(private val navController: NavHostController) {

    fun navigateToWords() {
        navController.navigate(MyDictionaryDestinations.WORDS_ROUTE) {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when
            // reselecting the same item
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = true
        }
    }

    fun navigateToTaskDetail(taskId: String) {
        navController.navigate("$WORD_DETAIL_SCREEN/$taskId")
    }

    fun navigateToAddWord() {
        navController.navigate(MyDictionaryDestinations.ADD_WORD_ROUTE)
    }
}
