package com.messieyawo.advancedfoodapp.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.messieyawo.advancedfoodapp.model.FoodRecipe

class RecipesTypeConverter {
    var gson = Gson()

    @TypeConverter
    fun foodRecipesToString(foodRecipe: FoodRecipe): String {
        return gson.toJson(foodRecipe)
    }

    @TypeConverter
    fun stringToFoodRecipes(data: String): FoodRecipe {
        var listType = object : TypeToken<FoodRecipe>() {}.type
        return gson.fromJson(data, listType)
    }
}