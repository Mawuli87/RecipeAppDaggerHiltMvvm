package com.messieyawo.advancedfoodapp.data.local

import com.messieyawo.advancedfoodapp.data.database.RecipesDAO
import com.messieyawo.advancedfoodapp.data.entities.RecipesEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val recipesDAO: RecipesDAO
) {

    suspend fun insertRecipes(recipesEntity: RecipesEntity) {
        recipesDAO.insertRecipes(recipesEntity)
    }

    fun readRecipes(): Flow<List<RecipesEntity>> {
        return recipesDAO.readRecipes()
    }

}