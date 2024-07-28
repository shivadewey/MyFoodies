package com.shiva.myfoodies.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.shiva.myfoodies.ui.feature.categories.FoodCategoriesScreen
import com.shiva.myfoodies.ui.feature.categories.FoodCategoriesViewModel
import com.shiva.myfoodies.ui.feature.categories_details.FoodCategoryDetailViewModel
import com.shiva.myfoodies.ui.feature.categories_details.FoodCategoryDetailsScreen
import kotlinx.coroutines.flow.receiveAsFlow

@Composable
fun AppScreen() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = NavigationKeys.Route.FOOD_CATEGORIES) {
        composable(route = NavigationKeys.Route.FOOD_CATEGORIES) {
            FoodCategoriesDestination(navController)
        }
        composable(
            route = NavigationKeys.Route.FOOD_CATEGORY_DETAILS,
            arguments = listOf(navArgument(NavigationKeys.Arg.FOOD_CATEGORY_ID) {
                type = NavType.StringType
            })
        ) {
            FoodCategoryDetailsDestination()
        }
    }
}

@Composable
fun FoodCategoriesDestination(navController: NavHostController) {
    val viewModel: FoodCategoriesViewModel = hiltViewModel()
    FoodCategoriesScreen(
        state = viewModel.state,
        effectFlow = viewModel.effects.receiveAsFlow(),
        onNavigationRequested = { itemId ->
            navController.navigate("${NavigationKeys.Route.FOOD_CATEGORIES}/${itemId}")
        }
    )
}

@Composable
fun FoodCategoryDetailsDestination() {
    val viewModel: FoodCategoryDetailViewModel = hiltViewModel()
    FoodCategoryDetailsScreen(state = viewModel.state)
}

