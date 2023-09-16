package com.wolfcodea.movieappcompose.navigation

import java.lang.IllegalArgumentException

enum class MoviesScreens {
    HomeScreen, DetailsScreen;
    companion object {
        fun fromRoute(route: String?) : MoviesScreens = when(route?.substringBefore("/")){
            HomeScreen.name -> HomeScreen
            DetailsScreen.name -> DetailsScreen
            null -> HomeScreen
            else -> throw IllegalArgumentException("Route $route is not recognize")
        }
    }
}