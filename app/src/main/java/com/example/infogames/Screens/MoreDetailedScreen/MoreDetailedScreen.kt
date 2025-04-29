package com.example.infogames.Screens.MoreDetailedScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.infogames.Navigate.Screens
import com.example.infogames.State.ResultDataClass
import com.example.infogames.ViewModelsSupaBase.MoreDetailedView
import com.example.supabasesimpleproject.R


@Composable
fun MoreDetailedScreen(navController: NavController, id:String,
                       viewModel: MoreDetailedView = viewModel {MoreDetailedView(id)},
) {
    val resultSelect by viewModel.resultDataClassSelectGames.collectAsState()

    val categories = viewModel.categories.observeAsState(emptyList())
    val selectedCategory = remember { mutableStateOf("") }

    val userId = viewModel.userId
    val gameDataClass = viewModel.gameDataClass

    when(resultSelect){
        is ResultDataClass.Error -> {
            Text(text = "ОШИБКА")
        }
        is ResultDataClass.Loading -> {
            Box(
                modifier = Modifier.size(100.dp)
            ) {
                LinearProgressIndicator(color = Color(0xff9b2d30))
            }
        }
        is ResultDataClass.Initialized -> {}
        is ResultDataClass.Success -> {
            LazyColumn {
                item{

                    if(userId == gameDataClass.creator){
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
                                AsyncImage(
                                    model = gameDataClass.image,
                                    contentDescription = "",
                                    modifier = Modifier.fillMaxWidth(),
                                    error = painterResource(R.drawable.logotype),
                                    contentScale = ContentScale.Crop,
                                )
                                TextFieldUpdate(text = gameDataClass.name, label = "Название игры",
                                    onValueChanged = {viewModel.updateGames(gameDataClass.copy(name = it))})
                                TextFieldUpdate(text = gameDataClass.developer, label = "Разработчик",
                                    onValueChanged = {viewModel.updateGames(gameDataClass.copy(developer = it))})
                                TextFieldUpdate(text = gameDataClass.description, label = "Описание",
                                    onValueChanged = {viewModel.updateGames(gameDataClass.copy(description = it))})
                                LazyRow {
                                    items(categories.value.indices.toList()) { index ->
                                        CatregoriesButton(
                                            typeCategories = categories.value[index].copy(),
                                            onClick = {selectedCategory.value = categories.value[index].id
                                                viewModel.updateGames(gameDataClass.copy(categoryId = selectedCategory.value))}
                                        )
                                    }
                                }
                                TextFieldCategoriesUpdate(text = selectedCategory.value)
                                Row {
                                    MoreDetailedButtonDelUpd(text = "Удалить", onClick = {viewModel.DeleteGames()
                                        navController.navigate(Screens.Main)})
                                    MoreDetailedButtonDelUpd(text = "Обновить", onClick = {viewModel.UpdateGames()})
                                }
                            }
                        }

                    }
                    else {
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
                                AsyncImage(
                                    model = gameDataClass.image,
                                    contentDescription = "",
                                    modifier = Modifier.fillMaxWidth(),
                                    error = painterResource(R.drawable.logotype),
                                    contentScale = ContentScale.Crop,
                                )
                                Text(
                                    text = "Название игры: " + gameDataClass.name,
                                    fontSize = 15.sp,
                                    color = Color(0xff9b2d30),
                                    textAlign = TextAlign.Justify
                                )
                                Text(
                                    text = "Разработчик: " + gameDataClass.developer,
                                    fontSize = 15.sp,
                                    color = Color(0xff9b2d30),
                                    textAlign = TextAlign.Justify
                                )
                                Text(
                                    text = "Описание: " + gameDataClass.description,
                                    fontSize = 15.sp,
                                    color = Color(0xff9b2d30),
                                    textAlign = TextAlign.Justify,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}