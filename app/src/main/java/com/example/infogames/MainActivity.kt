package com.example.infogames

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.infogames.Navigate.Navigate
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest

object Constant {
    val supabase = createSupabaseClient(
        supabaseUrl = "https://oruuiavqoegutrgpvugh.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Im9ydXVpYXZxb2VndXRyZ3B2dWdoIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MjUzNTcyMjYsImV4cCI6MjA0MDkzMzIyNn0.567sJ3LdvvVw9HSkPCQugd2rYU9o8o1ZI1cr3J2zgf4"
    ) {
        install(Postgrest)
        install(Auth)
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Navigate()
        }
    }
}