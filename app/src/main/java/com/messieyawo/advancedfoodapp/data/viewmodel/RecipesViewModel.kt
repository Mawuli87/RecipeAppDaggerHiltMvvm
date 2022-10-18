package com.messieyawo.advancedfoodapp.data.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.messieyawo.advancedfoodapp.utils.Constants
import com.messieyawo.advancedfoodapp.utils.Constants.Companion.QUERY_ADD_RECIPE_INFORMATION
import com.messieyawo.advancedfoodapp.utils.Constants.Companion.QUERY_API_KEY
import com.messieyawo.advancedfoodapp.utils.Constants.Companion.QUERY_DIET
import com.messieyawo.advancedfoodapp.utils.Constants.Companion.QUERY_FILL_INGREDIENTS
import com.messieyawo.advancedfoodapp.utils.Constants.Companion.QUERY_NUMBER
import com.messieyawo.advancedfoodapp.utils.Constants.Companion.QUERY_TYPE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    application: Application
) : AndroidViewModel(application) {


   fun applyQueries():HashMap<String,String>{
        val queries: HashMap<String,String> = HashMap()

        queries[QUERY_NUMBER] = ""
        queries[QUERY_API_KEY] = Constants.API_KEY
        queries[QUERY_TYPE] = "snack"
        queries[QUERY_DIET] = "vegan"
        queries[QUERY_ADD_RECIPE_INFORMATION] = "true"
        queries[QUERY_FILL_INGREDIENTS] = "true"

        return queries
    }


}