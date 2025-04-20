package com.example.shoppinglistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.shoppinglistapp.ui.theme.ShoppingListAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShoppingListAppTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize(),
                ) {
                    innerPadding ->

                    var sItems by remember { mutableStateOf( listOf<ShoppingList>())}

                    Column(modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize(),
                        verticalArrangement = Arrangement.Top
                    ) {

                        Button(onClick = { }, modifier = Modifier.align(Alignment.CenterHorizontally)) {
                            Text(text = "Add item")
                        }

                        LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {

                            item {Text("Hello lazy comlumn", modifier = Modifier.align(Alignment.CenterHorizontally))}
                            item {Text("Hello lazy comlumn", modifier = Modifier.align(Alignment.CenterHorizontally))}
                        }
                    }
                }
            }
        }
    }
}

data class ShoppingList (var id: Int,
                         var name: String,
                         var quantity: Int,
                         var isEditing: Boolean = false)
