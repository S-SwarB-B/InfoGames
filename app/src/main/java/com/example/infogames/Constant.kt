package com.example.infogames

import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest

object Constant { //Подключение SupaBase
    val supabase = createSupabaseClient(
        supabaseUrl = "https://usltlumxagvdotvlhyxz.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InVzbHRsdW14YWd2ZG90dmxoeXh6Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDE2Mzc1NzEsImV4cCI6MjA1NzIxMzU3MX0.GkB9wtkKP_cLsE1NkQGI1m6U9n5uIvo78Ddhho8xFag"
    ) {
        install(Postgrest) //PostgreSQL
        install(Auth) //Аунтификация
    }
}