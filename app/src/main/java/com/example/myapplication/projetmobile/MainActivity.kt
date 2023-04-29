package com.example.myapplication.projetmobile

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.myapplication.ui.theme.MyApplicationTheme


class MainActivity :ComponentActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MyPreferences.init(this)
        Graph.provide(this)
        if (isFirstTime()){
            setContent {
                MyApplicationTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background,
                    ) {
                       DefaultNavHost()
                    }
                }
            }
        }else{
            setContent {
                MyApplicationTheme {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background,
                    ) {
                        NavHost()
                    }
                }
            }
        }
    }


    private fun isFirstTime(): Boolean {
        val preferences = getSharedPreferences("my_app_prefs", Context.MODE_PRIVATE)
        val isFirstTime = preferences.getBoolean("isFirstTime", false)
        val hasCompletedPresentation = MyPreferences.hasCompletedWelcome
        if (!isFirstTime && !hasCompletedPresentation) {
            preferences.edit().putBoolean("hasCompletedPresentation", true).apply()
        }
        return isFirstTime  || !hasCompletedPresentation
    }
}

object MyPreferences {
    private const val PREFS_NAME = "my_app_prefs"
    private const val HAS_COMPLETED_WELCOME = "hasCompletedWelcome"

   private lateinit var preferences:SharedPreferences

   fun init(context: Context){
       preferences=context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE)

   }

    var hasCompletedWelcome: Boolean
        get() = preferences.getBoolean(HAS_COMPLETED_WELCOME, false)
        set(value) = preferences.edit().putBoolean(HAS_COMPLETED_WELCOME, value).apply()
}