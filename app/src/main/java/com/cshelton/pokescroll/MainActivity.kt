package com.cshelton.pokescroll

import com.cshelton.pokescroll.ui.PokeListScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import com.cshelton.pokescroll.ui.theme.PokeScrollTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PokeScrollTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Poke Scroll") }
                        )
                    },
                    modifier = Modifier.fillMaxSize(


                )) { innerPadding ->
                    PokeListScreen(modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize())
                }
            }
        }
    }
}
