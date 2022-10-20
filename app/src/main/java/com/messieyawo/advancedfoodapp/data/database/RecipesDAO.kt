package com.messieyawo.advancedfoodapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.messieyawo.advancedfoodapp.data.entities.RecipesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipesDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipes(recipesEntity: RecipesEntity)

    @Query("SELECT * FROM recipes_table ORDER BY id ASC")
    fun readRecipes(): Flow<List<RecipesEntity>>
}