package com.shiva.myfoodies.ui.feature.categories

import com.shiva.myfoodies.data.model.FoodItem

class FoodCategoriesContract {
    data class State(
        val categories: List<FoodItem> = listOf(),
        val isLoading: Boolean = false
    )

    sealed class Effect {
        object DataWasLoaded : Effect()
    }
}