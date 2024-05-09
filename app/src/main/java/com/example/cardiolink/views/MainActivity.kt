package com.example.cardiolink.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.cardiolink.ui.theme.AppTheme
import com.example.cardiolink.graphs.RootNavigationGraph
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                RootNavigationGraph()
            }
        }
    }
}