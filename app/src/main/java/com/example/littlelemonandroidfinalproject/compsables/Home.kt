package com.example.littlelemonandroidfinalproject.compsables

import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.littlelemonandroidfinalproject.MenuItemRoom
import com.example.littlelemonandroidfinalproject.MenuViewModel
import com.example.littlelemonandroidfinalproject.R
import com.example.littlelemonandroidfinalproject.ui.theme.LittleLemonColor
import com.example.littlelemonandroidfinalproject.ui.theme.customShapes
import com.example.littlelemonandroidfinalproject.ui.theme.h1


@Composable
fun Home(navController: NavController){
    HomePage(navController = navController)
}

@Composable
fun HomePage(navController: NavController) {
    Log.d("HomePage", "Composable start")

    val context = LocalContext.current
    val viewModel: MenuViewModel = viewModel()
    val databaseMenuItems = viewModel.getAllDatabaseMenuItems().observeAsState(emptyList()).value
    val searchPhrase = remember {
        mutableStateOf("")
    }

    LaunchedEffect(Unit) {
        viewModel.fetchMenuDataIfNeeded()
    }


    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Log.d("HomePage", "Inside Row")
                Image(
                    painter = painterResource(R.drawable.logo),
                    contentDescription = "Logo Image",
                    modifier = Modifier
                        .size(40.dp)
                        .weight(1f)
                        .align(Alignment.CenterVertically)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Image(
                    painter = painterResource(R.drawable.profile),
                    contentDescription = "Profile Image",
                    modifier = Modifier
                        .size(80.dp)
                        .clickable {
                            navController.navigate("Profile")
                        }
                )
            }

            // Hero section
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .background(color = LittleLemonColor.green)
                    .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Column {
                        Text(
                            text = stringResource(R.string.title),
                            style = h1,
                            color = LittleLemonColor.yellow
                        )
                        Column(modifier = Modifier.fillMaxWidth(0.5f)) {
                            Text(
                                text = stringResource(R.string.location),
                                style = MaterialTheme.typography.titleLarge,
                                color = LittleLemonColor.cloud,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = stringResource(R.string.description),
                                style = MaterialTheme.typography.bodyLarge,
                                color = LittleLemonColor.cloud
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                        }

                    }
                    Column(verticalArrangement = Arrangement.Center) {
                        Image(
                            painter = painterResource(R.drawable.hero_image),
                            contentDescription = "Hero Image",
                            modifier = Modifier
                                .fillMaxWidth(0.6f)
                                .clip(shape = customShapes.medium),

                        )
                        Spacer(modifier = Modifier.width(16.dp))
                    }

                }

                // Search TextField
                TextField(
                    value = searchPhrase.value,
                    onValueChange = { searchPhrase.value = it },
                    placeholder = { Text("Enter search phrase") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = ""
                        )
                    }
                )

            }
            Spacer(modifier = Modifier.width(16.dp))
            // Filtered MenuItems
            val filteredMenuItems = if  (searchPhrase.value.isBlank()) {
                databaseMenuItems
            } else {
               databaseMenuItems.filter { menuItem ->
                   menuItem.title.contains(searchPhrase.value, ignoreCase = true)
               }
            }

            MenuItems(menuItems = filteredMenuItems, context)

        }

    }
    Log.d("HomePage", "Composable end")
}

@Composable
fun MenuItems(menuItems: List<MenuItemRoom>, context: Context) {
    Spacer(modifier = Modifier
        .width(20.dp)
        .padding(top = 10.dp, bottom = 10.dp))
    Column {
        for (menuItem in menuItems) {
            MenuItem(item = menuItem, context)
        }
    }
}

@Composable
fun MenuItem(item: MenuItemRoom, context: Context) {
    Spacer(modifier = Modifier.width(10.dp))
    Divider(color = Color.Gray, thickness = 1.dp)
    Spacer(modifier = Modifier.width(10.dp))
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 8.dp), horizontalArrangement = Arrangement.SpaceEvenly) {
        Column(modifier = Modifier.fillMaxWidth(0.5f)) {
            Text(text = item.title, fontWeight = FontWeight.Bold)
            Text(text = "$  ${item.price}")
            Text(text = item.description)
        }
        Column {
            Spacer(modifier = Modifier.width(10.dp))
            AsyncImage(
                model = item.image,
                contentDescription = null,
                modifier = Modifier.size(100.dp, 100.dp),
                contentScale = ContentScale.Crop,
            )
            Spacer(modifier = Modifier.width(10.dp))
        }

    }
}


@Preview(showBackground = true)
@Composable
fun HomePreview() {
    Home(rememberNavController())
}