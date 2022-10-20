package com.messieyawo.advancedfoodapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.messieyawo.advancedfoodapp.data.entities.RecipesEntity


@Database(
    entities = [RecipesEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(RecipesTypeConverter::class)
abstract class RecipesDatabase:RoomDatabase() {
    abstract fun recipesDao(): RecipesDAO
}