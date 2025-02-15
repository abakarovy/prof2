package com.example.myapplication

import android.util.Log
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.OAuthProvider
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns
import io.github.jan.supabase.postgrest.query.filter.PostgrestFilterBuilder
import io.github.jan.supabase.postgrest.query.request.SelectRequestBuilder
import io.github.jan.supabase.postgrest.result.PostgrestResult
import io.github.jan.supabase.serializer.KotlinXSerializer
import io.ktor.serialization.kotlinx.json.DefaultJson

object SupabaseClient {
    var url: String = "https://qscgpgvomvxhqdrbglgi.supabase.co"
    var apiKey: String = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InFzY2dwZ3ZvbXZ4aHFkcmJnbGdpIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MzkzNTUwODMsImV4cCI6MjA1NDkzMTA4M30._j_UaHMpJQ2oHFT9oxkGFdJm9RU3dB3TZ7YhUd4hs7M"
    var client = createSupabaseClient(url, apiKey) {
            defaultSerializer = KotlinXSerializer(DefaultJson)
            install(Auth)
            install(Postgrest)
    }

    suspend fun signIn(em: String, pw: String): Boolean {
        try {
            val res = client.auth.signInWith(Email) {
                email = em
                password = pw
            }
            println(res)
            Log.i("SKIIIIII", "success")
            return true
        } catch (e: Exception) {
            return false
            e.printStackTrace()
        }
    }

    suspend fun signOut() {
        try {
            client.auth.signOut()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun getShoes(): List<Shoe> {
        try {
            var result = client.postgrest.from("shoes").select(columns = Columns.list("name", "price")).decodeList<Shoe>()
            return result
        } catch (e: Exception) {
            e.printStackTrace()
        }
        throw RuntimeException("couldn't fetch data fr")
    }

    suspend fun getShoesByString(str: String): List<Shoe> {
        try {
            var result = client.postgrest.from("shoes").select(columns = Columns.list("name", "price")) {
                filter {
                    Shoe::name ilike ("%${str}%")
                }
            }.decodeList<Shoe>()
            return result
        } catch (e: Exception) {
            e.printStackTrace()
        }
        throw RuntimeException("couldn't fetch data fr")
    }

    
}