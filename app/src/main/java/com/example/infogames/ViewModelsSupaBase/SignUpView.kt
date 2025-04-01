package com.example.infogames.ViewModelsSupaBase


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.infogames.Constant
import com.example.infogames.Constant.supabase
import com.example.infogames.Tables.Profile
import com.example.infogames.State.ResultDataClass
import com.example.infogames.State.SignUpDataClass
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.exception.AuthRestException
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SignUpView : ViewModel(){
    private val _uiDataClass = mutableStateOf(SignUpDataClass())
    val uiDataClass: SignUpDataClass get() = _uiDataClass.value

    private val _resultDataClass = MutableStateFlow<ResultDataClass>(ResultDataClass.Initialized)
    val resultState: StateFlow<ResultDataClass> = _resultDataClass.asStateFlow()

    fun updateDataClass(newState: SignUpDataClass){
        _uiDataClass.value = newState
        _resultDataClass.value = ResultDataClass.Initialized
    }

    fun signUp(){
        _resultDataClass.value = ResultDataClass.Loading

        viewModelScope.launch {
            if (_uiDataClass.value.password == _uiDataClass.value.confirm_password){
                if (_uiDataClass.value.password.count() >= 6){
                    try {
                        Constant.supabase.auth.signUpWith(Email){
                            email = _uiDataClass.value.email
                            password = _uiDataClass.value.password
                        }
                        val id_user = Constant.supabase.auth.currentUserOrNull()
                        if (id_user != null){
                            val _profile = Profile(id_user.id,_uiDataClass.value.username, _uiDataClass.value.surname)
                            supabase.from(schema = "public", table = "user_profile").insert(_profile)
                        }
                        _resultDataClass.value = ResultDataClass.Success("Нет ошибок")
                    }
                    catch (ex : AuthRestException){
                        _resultDataClass.value = ResultDataClass.Error("Ошибка получения данных")
                    } }
                else{
                    _resultDataClass.value = ResultDataClass.Error("В пароле должно быть не менее 6 символов")
                }
            }
            else {
                _resultDataClass.value = ResultDataClass.Error("Пароли не совпадают")
            }
        }
    }
}