package com.shiva.myfoodies.ui.feature.categories_details

import com.shiva.myfoodies.data.model.FoodItem

class FoodCategoryDetailContract {
    data class State(
        val category: FoodItem?,
        val categories: List<FoodItem>
    )
}