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
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SigInView : ViewModel(){
    private val _uiDataClass = mutableStateOf(SignInDateClass())
    val uiDataClass: SignInDateClass get() = _uiDataClass.value

    private val _resultDataClass = MutableStateFlow<ResultDataClass>(ResultDataClass.Initialized)
    val resultState: StateFlow<ResultDataClass> = _resultDataClass.asStateFlow()

    fun updateDataClass(newState: SignInDateClass){
        _uiDataClass.value = newState
        _resultDataClass.value = ResultDataClass.Initialized
    }

    fun signIn(){
        _resultDataClass.value = ResultDataClass.Loading
        if(_uiDataClass.value.ErrorLogSIG){
            viewModelScope.launch {
                try {
                    Constant.supabase.auth.signInWith(Email){
                        email = _uiDataClass.value.Login
                        password = _uiDataClass.value.Password
                    }
                }
                catch (_ex : AuthRestException){
                    _resultDataClass.value = ResultDataClass.Error(_ex.errorDescription ?: "Ошибка получения данных")
                }
            }
        }
        else{
            _resultDataClass.value = ResultDataClass.Error("Ошибка ввода почты")
        }
    }
}