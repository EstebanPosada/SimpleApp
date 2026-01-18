package com.estebanposada.simpleapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.estebanposada.simpleapp.presentation.book_detail.BookDetailScreen
import com.estebanposada.simpleapp.presentation.book_list.BookListScreen
import com.estebanposada.simpleapp.presentation.ui.theme.PurpleGrey80
import com.estebanposada.simpleapp.presentation.ui.theme.SimpleAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SimpleAppTheme {
                val navController = rememberNavController()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = PurpleGrey80
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Screen.BookListScreen.route,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(route = Screen.BookListScreen.route) {
                            BookListScreen { navController.navigate(Screen.BookDetailScreen.route + "/$it") }
                        }
                        composable(route = Screen.BookDetailScreen.route + "/{bookId}") {
                            BookDetailScreen { navController.popBackStack() }
                        }
                    }
                }
            }
        }
    }
}
