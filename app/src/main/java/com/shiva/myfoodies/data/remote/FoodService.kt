package com.shiva.myfoodies.data.remote

import com.shiva.myfoodies.data.model.FoodCategoriesResponse
import com.shiva.myfoodies.data.model.MealsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodService {
    @GET("categories.php")
    suspend fun getFoodCategories(): FoodCategoriesResponse

    @GET("filter.php")
    suspend fun getFoodByCategory(@Query("c") category: String): MealsResponse
}