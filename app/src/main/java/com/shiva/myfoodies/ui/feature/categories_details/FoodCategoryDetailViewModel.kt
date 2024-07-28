package com.shiva.myfoodies.ui.feature.categories_details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shiva.myfoodies.repository.FoodCategoryRepository
import com.shiva.myfoodies.ui.NavigationKeys
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodCategoryDetailViewModel @Inject constructor(
    private val stateHandle: SavedStateHandle,
    private val foodCategoryRepository: FoodCategoryRepository
) : ViewModel() {
    var state by mutableStateOf(
        FoodCategoryDetailContract.State(
            null, listOf()
        )
    )
        private set

    init {
        viewModelScope.launch {
            val categoryId = stateHandle.get<String>(NavigationKeys.Arg.FOOD_CATEGORY_ID)
                ?: throw IllegalStateException("No categoryId was passed to destination.")
            val categories = foodCategoryRepository.getFoodCategories()
            val category = categories.first() { it.id == categoryId }
            state = state.copy(category = category)
            val foodItems = foodCategoryRepository.getMealsByCategory(categoryId)
            state = state.copy(categories = foodItems)
        }
    }
}


