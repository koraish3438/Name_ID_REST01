package com.example.name_id_rest01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.name_id_rest01.ui.screens.ProductScreen
import com.example.name_id_rest01.viewmodel.ProductViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        setContent {
            val productViewModel: ProductViewModel = viewModel()
            ProductScreen(productViewModel)
        }
    }
}