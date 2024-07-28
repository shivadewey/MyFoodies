package com.shiva.myfoodies.ui

object NavigationKeys {
    object Arg {
        const val FOOD_CATEGORY_ID = "foodCategoryName"
    }

    object Route {
        const val FOOD_CATEGORIES = "foodCategories"
        const val FOOD_CATEGORY_DETAILS = "$FOOD_CATEGORIES/{${Arg.FOOD_CATEGORY_ID}}"
    }
}