package com.shiva.myfoodies.repository

import com.shiva.myfoodies.data.model.FoodCategoriesResponse
import com.shiva.myfoodies.data.model.FoodItem
import com.shiva.myfoodies.data.model.MealsResponse
import com.shiva.myfoodies.data.remote.FoodService
import javax.inject.Inject

class FoodCategoryRepository @Inject constructor(private val foodService: FoodService) {
    private var cachedCategories: List<FoodItem>? = null
    private fun FoodCategoriesResponse.mapCategoriesToItems(): List<FoodItem> {
        return this.categories.map {
            FoodItem(
                id = it.id,
                name = it.name,
                thumbnailUrl = it.thumbnail,
                description = it.description
            )
        }
    }

    private fun MealsResponse.mapMealsToItems(): List<FoodItem> {
        return this.meals.map {
            FoodItem(
                id = it.id,
                name = it.name,
                thumbnailUrl = it.thumbnailUrl
            )
        }
    }

    suspend fun getFoodCategories(): List<FoodItem> {
        var cacheCategories = cachedCategories
        if (cacheCategories == null) {
            cacheCategories = foodService.getFoodCategories().mapCategoriesToItems()
            this@FoodCategoryRepository.cachedCategories = cacheCategories
        }
        return cacheCategories
    }

    suspend fun getMealsByCategory(categoryId: String): List<FoodItem> {
        val categoryName = getFoodCategories().first { it.id == categoryId }.name
        return foodService.getFoodByCategory(categoryName).mapMealsToItems()
    }

}