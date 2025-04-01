package com.example.infogames.ViewModelsSupaBase

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.infogames.Constant
import com.example.infogames.State.ResultDataClass
import com.example.infogames.State.SignInDateClass
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.exception.AuthRestException
import io.github.jan.supabase.auth.providers.builtin.Email
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SigInView : ViewModel(){
    private val _uiDataClass = mutableStateOf(SignInDateClass()) //Переменная которая принимает в себя поля, нужные для входа в приложения
    val uiDataClass: SignInDateClass get() = _uiDataClass.value //Присваивание переменной со специальным типом предыдущей переменной

    private val _resultDataClass = MutableStateFlow<ResultDataClass>(ResultDataClass.Initialized) //Переменная принимающая в себя состояния загрузки приложения
    val resultState: StateFlow<ResultDataClass> = _resultDataClass.asStateFlow() //Присваивание

    fun updateDataClass(newState: SignInDateClass){ //Функция обновления этих переменных
        _uiDataClass.value = newState
        _resultDataClass.value = ResultDataClass.Initialized
    }

    fun signIn(){ //Функция входа в систему
        _resultDataClass.value = ResultDataClass.Loading //Изменение состояния загрузки
            viewModelScope.launch {
                try {
                    Constant.supabase.auth.signInWith(Email){
                        email = _uiDataClass.value.email
                        password = _uiDataClass.value.password
                    }
                    _resultDataClass.value = ResultDataClass.Success("Ошибок нет")
                }
                catch (_ex : AuthRestException){ //Обработка исключения
                    _resultDataClass.value = ResultDataClass.Error("Неверный логин или пароль") //Изменение состояния загрузки
                }
            }
        }
    }