package com.example.infogames.Screens.MainScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.infogames.Navigate.Screens
import com.example.infogames.State.ResultDataClass
import com.example.infogames.ViewModelsSupaBase.MainView

@Composable
fun MainScreen(navController: NavController, mainView: MainView = viewModel()){
    val timeState by mainView.resultDataClass.collectAsState()
    val games = mainView.games.observeAsState(emptyList())
    val categories = mainView.categories.observeAsState(emptyList())
    val txtSearch = remember { mutableStateOf("") }
    val CategorySelect = remember { mutableStateOf("") }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(
            top = 40.dp
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TextFieldSearchMainScreen(txt = txtSearch.value, onValueChange = {newText ->
            txtSearch.value = newText
            mainView.filterListGames(newText, CategorySelect.value)
        },
            label = "Поиск")

    when(timeState) {
        is ResultDataClass.Error ->
            Text(text = (timeState as ResultDataClass.Error).message)

        ResultDataClass.Initialized -> TODO()
        is ResultDataClass.Loading -> {
            Box(
                modifier = Modifier.size(100.dp)
            ) {
                LinearProgressIndicator(color = Color(0xff9b2d30))
            }
        }

        is ResultDataClass.Success -> {
            LazyRow (modifier = Modifier.padding(top = 5.dp, bottom = 10.dp)){
                items(categories.value.indices.toList()) { index ->
                    CatregoriesButton(typeCategories = categories.value[index].copy(),
                        onClick = {
                            CategorySelect.value = categories.value[index].id
                            mainView.filterListGames(
                                txtSearch.value,
                                categoryId = CategorySelect.value
                            )
                        }
                    )
                }
            }
            CreateButton(onClick = {
                navController.navigate(Screens.CreateScreen)
            })
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp, alignment = Alignment.CenterVertically),
                modifier = Modifier.padding(bottom = 25.dp)
            ){
                items(games.value) { it ->
                    GameCard(games = it)
                    MoreDetailedButton(onClick = {
                        navController.navigate(Screens.MoreDetailed + "/" + it.id)
                    })
                }
            }
        }
    }
  }
}