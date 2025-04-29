package com.example.infogames.Screens.CreateScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.infogames.Navigate.Screens
import com.example.infogames.Screens.MoreDetailedScreen.TextFieldCategoriesUpdate
import com.example.infogames.Screens.MoreDetailedScreen.TextFieldUpdate
import com.example.infogames.ViewModelsSupaBase.CreateView
import com.example.infogames.ViewModelsSupaBase.MainView

@Composable
fun CreateScreen(navController: NavController, viewModel: CreateView = viewModel()){

    val categories = viewModel.categories.observeAsState(emptyList())
    val selectedCategory = remember { mutableStateOf("") }

    val gameDataClass = viewModel.gameDataClass

    Box(

    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                10.dp,
                alignment = Alignment.CenterVertically
            ),
            modifier = Modifier.padding(
                end = 10.dp,
                start = 10.dp,
                bottom = 15.dp,
                top = 50.dp
            )
        )
        {
            TextFieldAdd(text = gameDataClass.name, label = "Название игры",
                onValueChanged = { viewModel.updateGames(gameDataClass.copy(name = it)) })
            TextFieldAdd(text = gameDataClass.developer, label = "Разработчик",
                onValueChanged = { viewModel.updateGames(gameDataClass.copy(developer = it)) })
            TextFieldAdd(text = gameDataClass.description, label = "Описание",
                onValueChanged = { viewModel.updateGames(gameDataClass.copy(description = it)) })
            LazyRow {
                items(categories.value.indices.toList()) { index ->
                    com.example.infogames.Screens.MoreDetailedScreen.CatregoriesButton(
                        typeCategories = categories.value[index].copy(),
                        onClick = {
                            selectedCategory.value = categories.value[index].id
                            viewModel.updateGames(gameDataClass.copy(categoryId = selectedCategory.value))
                        }
                    )
                }
            }
            TextFieldCategoriesAdd(text = selectedCategory.value)
            MoreDetailedButtonAdd(text = "Добавить", onClick = {viewModel.addGames()
            navController.navigate(Screens.Main)})
        }
    }
}