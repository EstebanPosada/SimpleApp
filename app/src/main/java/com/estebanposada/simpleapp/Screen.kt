package com.estebanposada.simpleapp

sealed class Screen(val route: String){
    object BookListScreen: Screen("BookList")
    object BookDetailScreen: Screen("BookDetail")
}