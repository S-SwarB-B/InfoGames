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

class SignUpView : ViewModel(){
    private val _uiDataClass = mutableStateOf(SignInDateClass())
    val uiDataClass: SignInDateClass get() = _uiDataClass.value

    private val _resultDataClass = MutableStateFlow<ResultDataClass>(ResultDataClass.Initialized)
    val resultState: StateFlow<ResultDataClass> = _resultDataClass.asStateFlow()

    fun updateDataClass(newState: SignInDateClass){
        _uiDataClass.value = newState
        _resultDataClass.value = ResultDataClass.Initialized
    }

    fun signUp(){
        _resultDataClass.value = ResultDataClass.Loading

        viewModelScope.launch {
            try {
                Constant.supabase.auth.signUpWith(Email){
                    email = _uiDataClass.value.email
                    password = _uiDataClass.value.password
                }
                _resultDataClass.value = ResultDataClass.Success("Ошибок нет")
            }
            catch (_ex : AuthRestException){
                _resultDataClass.value = ResultDataClass.Error("Ошибка получения данных")
            }
        }
    }
}