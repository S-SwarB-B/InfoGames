package com.example.infogames.ViewModelsSupaBase

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.infogames.Constant
import com.example.infogames.Constant.supabase
import com.example.infogames.State.GamesDataClass
import com.example.infogames.Tables.Categories
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

class CreateView : ViewModel() {

    private val _gameDataClass = mutableStateOf(GamesDataClass())
    val gameDataClass: GamesDataClass get() = _gameDataClass.value

    private val _userId = mutableStateOf<String?>("")
    val userId:String? get() = _userId.value

    private val _categories = MutableLiveData<List<Categories>>()
    val categories: LiveData<List<Categories>> get() = _categories

    @Serializable
    data class newGames(
        val name: String,
        val description: String,
        val categoryId: String,
        val developer: String,
        val creator: String,

    )

    init{
        selectUser()
        loadCategoriesGames()
    }

    fun updateGames(newGames: GamesDataClass){
        _gameDataClass.value = newGames
    }
    fun selectUser(){
        viewModelScope.launch {
            try{
                _userId.value = Constant.supabase.auth.currentUserOrNull()?.id
            }
            catch (ex: Exception){ }
        }
    }
    fun addGames(){
        viewModelScope.launch {
            try {
                supabase.postgrest.from("Games").insert(
                    newGames(
                        name = _gameDataClass.value.name,
                        description = _gameDataClass.value.description,
                        categoryId = _gameDataClass.value.categoryId,
                        creator = _userId.value ?: "",
                        developer = _gameDataClass.value.developer,
                    )
                )
            }
            catch (ex: Exception){
                Log.e("AddGame", "Full error: ", ex)
                println("Detailed error: ${ex.stackTraceToString()}")}
        }
    }
    private fun loadCategoriesGames(){
        viewModelScope.launch {
            try{
                _categories.value = supabase.postgrest.from("GenresOfGames").select().decodeList<Categories>()
            }
            catch (ex: Exception){
            }
        }
    }
}