package com.example.infogames.ViewModelsSupaBase

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.infogames.Constant
import com.example.infogames.Constant.supabase
import com.example.infogames.State.GamesDataClass
import com.example.infogames.State.ResultDataClass
import com.example.infogames.Tables.Categories
import com.example.infogames.Tables.Games
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MoreDetailedView (id: String) : ViewModel() {
    val _id = id

    private val _resultDataClassSelectGames = MutableStateFlow<ResultDataClass>(ResultDataClass.Loading)
    val resultDataClassSelectGames: StateFlow<ResultDataClass> = _resultDataClassSelectGames.asStateFlow()

    lateinit var games: Games

    private val _gameDataClass = mutableStateOf(GamesDataClass())
    val gameDataClass: GamesDataClass get() = _gameDataClass.value

    private val _userId = mutableStateOf<String?>(null)
    val userId:String? get() = _userId.value

    private val _categories = MutableLiveData<List<Categories>>()
    val categories: LiveData<List<Categories>> get() = _categories

    init {
        selectGames()
        loadCategoriesGames()
    }

    fun updateGames(newGames: GamesDataClass){
        _gameDataClass.value = newGames
    }
    fun selectGames(){
        _resultDataClassSelectGames.value = ResultDataClass.Loading
        viewModelScope.launch {
            try{
                games = supabase.postgrest.from("Games").select(){
                    filter {
                        eq("id", _id)
                    }
                }.decodeSingle<Games>()
                _gameDataClass.value = GamesDataClass(
                    name = games.name,
                    description = games.description,
                    categoryId = games.categoryId,
                    image = games.image,
                    developer = games.developer,
                    creator = games.creator
                )
                _userId.value = Constant.supabase.auth.currentUserOrNull()?.id
                _resultDataClassSelectGames.value = ResultDataClass.Success("Все отлично")
            } catch(ex: Exception){
                _resultDataClassSelectGames.value = ResultDataClass.Error("Все плохо")
            }
        }
    }

    fun DeleteGames(){
        viewModelScope.launch {
            try{
                supabase.postgrest.from("Games").delete(){
                    filter {
                        eq("id", _id)
                    }
                }
            }
            catch(ex: Exception){
            }
        }
    }

    fun UpdateGames(){
        viewModelScope.launch {
            try{
                supabase.postgrest.from("Games").update(
                    {
                        set("name", _gameDataClass.value.name)
                        set("description", _gameDataClass.value.description)
                        set("categoryId", _gameDataClass.value.categoryId)
                        set("developer", _gameDataClass.value.developer)
                    }
                ){
                    filter {
                        eq("id", _id)
                    }
                }
            }
            catch (ex: Exception){
            }
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