package com.example.shoppinglistapp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import java.nio.file.WatchEvent

data class ShoppingList (var id: Int,
                         var name: String,
                         var quantity: Int,
                         var isEditing: Boolean = false)

@Composable
fun ShoppingListApp(innerPadding:PaddingValues) {
    var sItems by remember { mutableStateOf( listOf<ShoppingList>())}
    var showDialog by remember { mutableStateOf(false)}
    var itemName by remember { mutableStateOf("")}
    var itemQuantity by remember { mutableStateOf("")}

    Column(modifier = Modifier
        .padding(innerPadding)
        .fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {

        Button(onClick = {
            showDialog = true
        },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ){
            Text(text = "Add item")
        }

        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)) {

            items(sItems){
                item ->
                ShoppingListItem(item, onEditing = {}, onDeleting = { sItems = sItems.filterNot { it.id == item.id } })
            }
        }
    }

    if (showDialog) {
        Dialog(
            onDismissRequest = { showDialog = false },
            properties = DialogProperties(),
            content = {

                Box (modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.background,
                        shape = RoundedCornerShape(32.dp)
                    )
                    .border(
                        shape = RoundedCornerShape(32.dp),
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.background
                    )
                    .padding(18.dp)
                    .width(280.dp)
                    .height(300.dp)
                ){
                    Column (modifier = Modifier.
                        fillMaxSize(),
                    ){
                        Text("Add Shopping Item",
                            modifier = Modifier.align(Alignment.Start),
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.primary
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        OutlinedTextField(
                            onValueChange = { itemName = it },
                            value = itemName,
                            singleLine = true,
                            label = { Text("Item")},
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                            )

                        OutlinedTextField(
                            onValueChange = {itemQuantity = it},
                            value = itemQuantity,
                            singleLine = true,
                            label = { Text("Quantity")},
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp))

                        Spacer(modifier = Modifier.height(32.dp))

                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween){
                            Button(onClick = {
                                if (itemName.isNotBlank()) {
                                    val newItem = ShoppingList(id = sItems.size + 1, name = itemName, quantity = itemQuantity.toInt())
                                    sItems = sItems + newItem
                                    itemName = ""
                                    itemQuantity = ""
                                    showDialog = false
                                }

                            }) {
                                Text("Add")
                            }

                            Button(onClick = { showDialog = false }) {
                                Text("Cancel")
                            }
                        }
                    }
                }
            }
        )
    }
}

@Composable
fun ShoppingListItem(
    item: ShoppingList,
    onEditing: () -> Unit,
    onDeleting: () -> Unit
){
    Row (modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .border(
            shape = RoundedCornerShape(percent = 20),
            border = BorderStroke(
                2.dp,
                color = MaterialTheme.colorScheme.primary
            )
        ),
        verticalAlignment = Alignment.CenterVertically
    )
    {
        Text(text = item.name, modifier = Modifier.padding(8.dp).width(128.dp))

        Spacer(Modifier.width(64.dp))

        Text(text = "Qty: ${item.quantity}", modifier = Modifier.padding(8.dp))

        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Button(onClick = {
                onEditing()
            }
            ) {
                Icon(Icons.Default.Edit, contentDescription = "Edit")
            }

            Button(onClick = {
                onDeleting()
            }
            ) {
                Icon(Icons.Default.Delete, contentDescription = "Delete")
            }

        }
    }
}


