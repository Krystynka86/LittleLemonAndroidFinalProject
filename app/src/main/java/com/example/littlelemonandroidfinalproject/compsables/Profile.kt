package com.example.littlelemonandroidfinalproject.compsables

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.littlelemonandroidfinalproject.R
import com.example.littlelemonandroidfinalproject.ui.theme.LittleLemonColor
import com.example.littlelemonandroidfinalproject.ui.theme.button
import com.example.littlelemonandroidfinalproject.ui.theme.caption
import com.example.littlelemonandroidfinalproject.ui.theme.customShapes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Profile(navController: NavHostController) {

    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)

    val firstName = sharedPreferences.getString("firstName", "") ?: "Krystyna"
    val lastName = sharedPreferences.getString("lastName", "") ?: "Sajak"
    val email = sharedPreferences.getString("email", "") ?: "krysia.sajak@interia.pl"

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Header()

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 40.dp, horizontal = 16.dp),
                horizontalArrangement = Arrangement.Start

            ) {
                Text(
                    text = stringResource(R.string.personal_info),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }

            // Collected user input
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {

                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(R.string.caption_first_name),
                        style = caption
                    )

                    OutlinedTextField(
                        value = firstName,
                        onValueChange = { /* static text */ },
                        readOnly = true,
                        enabled = true,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }

                Spacer(modifier = Modifier.height(30.dp))

                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(R.string.caption_last_name),
                        style = caption
                    )

                    OutlinedTextField(
                        value = lastName,
                        onValueChange = { /* static text */ },
                        readOnly = true,
                        enabled = true,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }

                Spacer(modifier = Modifier.height(30.dp))


                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(R.string.caption_email),
                        style = caption
                    )

                    OutlinedTextField(
                        value = email,
                        onValueChange = { /* static text */ },
                        readOnly = true,
                        enabled = true,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }

                Spacer(modifier = Modifier.height(120.dp))

                // Logout button
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                ) {
                    Button(
                        onClick = {
                            // Clear shared preferences
                            val editor = sharedPreferences.edit()
                            editor.clear()
                            editor.apply()

                            // Navigate to Onboarding screen
                            navController.navigate("Onboarding")
                        },
                        border = BorderStroke(1.dp, SolidColor(LittleLemonColor.stroke)),
                        shape = customShapes.medium,
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = LittleLemonColor.charcoal,
                            containerColor = LittleLemonColor.yellow
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                        ) {
                        Text(
                            stringResource(R.string.button_logout),
                            style = button
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Header() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = "Little Lemon Logo",
            modifier = Modifier
                .fillMaxWidth()
                .size(80.dp)
                .padding(20.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    Profile(rememberNavController())
}