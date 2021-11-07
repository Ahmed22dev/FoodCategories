package com.elkhami.foodcategories.view.categorieslist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.elkhami.foodcategories.data.repository.TestFoodCategoryRepository
import com.elkhami.foodcategories.data.stub.FoodCategoriesStub
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

/**
 * Created by A.Elkhami on 07,November,2021
 */
class CategoriesListViewModelTest {

    @get:Rule
    val testRule: TestRule = InstantTaskExecutorRule()

    lateinit var viewModel: CategoriesListViewModel
    lateinit var repository: TestFoodCategoryRepository

    @Before
    fun setUp() {
        repository = TestFoodCategoryRepository()
        viewModel = CategoriesListViewModel(repository)
    }

    @Test
    fun `get food data from repository, returns the data`() {

        viewModel.getFoodCategories()

        assertThat(viewModel.uiStateFlow.value.foodCategories).isEqualTo(FoodCategoriesStub.foodCategories)
    }

    @Test
    fun `get from data from repository, returns unknown error`(){
        repository.setReturnError(true)

        viewModel.getFoodCategories()

        assertThat(viewModel.uiStateFlow.value.message).isEqualTo("error")
    }
}