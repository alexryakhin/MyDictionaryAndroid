package com.dor.mydictionary

import android.app.Activity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.DrawerState
import androidx.compose.material.DrawerValue
import androidx.compose.material.Text
import androidx.compose.material.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dor.mydictionary.MyDictionaryDestinationsArgs.TITLE_ARG
import com.dor.mydictionary.MyDictionaryDestinationsArgs.USER_MESSAGE_ARG
import com.dor.mydictionary.MyDictionaryDestinationsArgs.WORD_ID_ARG
import com.dor.mydictionary.add_word.AddWordScreen
import com.dor.mydictionary.util.AppModalDrawer
import com.dor.mydictionary.word_detail.WordDetailScreen
import com.dor.mydictionary.words.WordsScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun MyDictionaryNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    startDestination: String = MyDictionaryDestinations.WORDS_ROUTE,
    navActions: MyDictionaryNavigationActions = remember(navController) {
        MyDictionaryNavigationActions(navController)
    }
) {
    val currentNavBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentNavBackStackEntry?.destination?.route ?: startDestination

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(
            MyDictionaryDestinations.WORDS_ROUTE,
            arguments = listOf(
                navArgument(USER_MESSAGE_ARG) { type = NavType.IntType; defaultValue = 0 }
            )
        ) { entry ->
            AppModalDrawer(drawerState, currentRoute, navActions) {
                WordsScreen(
                    userMessage = entry.arguments?.getInt(USER_MESSAGE_ARG)!!,
                    onUserMessageDisplayed = { entry.arguments?.putInt(USER_MESSAGE_ARG, 0) },
                    onAddWord = { navActions.navigateToAddWord() },
                    onWordClick = { word -> navActions.navigateToWordDetail(word.id) },
                    openDrawer = { coroutineScope.launch { drawerState.open() } }
                )
            }
        }
        composable(MyDictionaryDestinations.ADD_WORD_ROUTE) {
            AddWordScreen(
                onBack = { navController.popBackStack() },
                onSave = { navController.popBackStack() }
            )
        }
        composable(
            MyDictionaryDestinations.WORD_DETAIL_ROUTE, arguments = listOf(
                navArgument(WORD_ID_ARG) { type = NavType.StringType; nullable = false }
            ))
        {
            WordDetailScreen(
                onBack = { navController.popBackStack() },
                onDelete = { navController.popBackStack() }
            )
        }
    }
}

// Keys for navigation
const val ADD_RESULT_OK = Activity.RESULT_FIRST_USER + 1
const val DELETE_RESULT_OK = Activity.RESULT_FIRST_USER + 2
const val EDIT_RESULT_OK = Activity.RESULT_FIRST_USER + 3
