package com.example.infogames.ViewModelsSupaBase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.infogames.Constant.supabase
import com.example.infogames.State.ResultDataClass
import com.example.infogames.Tables.Categories
import com.example.infogames.Tables.Games
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainView : ViewModel(){
    private val _resultDataClass = MutableStateFlow<ResultDataClass>(ResultDataClass.Loading)
    val resultDataClass: StateFlow<ResultDataClass> = _resultDataClass.asStateFlow()

    private val _games = MutableLiveData<List<Games>>()
    val games: LiveData<List<Games>> get() = _games

    private val _categories = MutableLiveData<List<Categories>>()
    val categories: LiveData<List<Categories>> get() = _categories

    private var filterGames: List<Games> = listOf()

    init {
        loadGames()
        loadCategoriesGames()
    }

    private fun loadGames(){
        _resultDataClass.value = ResultDataClass.Loading
        viewModelScope.launch {
            try{
                filterGames = supabase.postgrest.from("Games").select().decodeList<Games>()
                _games.value = filterGames
                _resultDataClass.value = ResultDataClass.Success("Все прошло отлично")
            }
            catch (ex: Exception){
                _resultDataClass.value = ResultDataClass.Error(ex.message + "Произошла ошибка")
            }
        }
    }

    private fun loadCategoriesGames(){
        viewModelScope.launch {
            try{
                _categories.value = supabase.postgrest.from("GenresOfGames").select().decodeList<Categories>()
            }
            catch (ex: Exception){
                _resultDataClass.value = ResultDataClass.Error(ex.message + "Произошла ошибка")
            }
        }
    }

    fun filterListGames(strFilt: String, categoryId: String?){
        val filteredGamesAll = filterGames.filter { games ->
            games.name.contains(strFilt) && games.categoryId == categoryId
        }
        val filteredGames = filterGames.filter { games ->
            games.name.contains(strFilt)
        }
        val filtredGamesCategories = filterGames.filter{ games ->
            games.categoryId == categoryId
        }
        if (categoryId != "" && strFilt != ""){
            _games.value = filteredGamesAll
        }
        else if (categoryId != "" && strFilt == "") {
            _games.value = filtredGamesCategories
        }
        else if (categoryId == "" && strFilt != "") {
            _games.value = filteredGames
        }
    }
}