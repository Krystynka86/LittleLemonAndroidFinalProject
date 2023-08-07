package com.example.littlelemonandroidfinalproject.compsables

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.edit
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.littlelemonandroidfinalproject.R
import com.example.littlelemonandroidfinalproject.ui.theme.LittleLemonColor
import com.example.littlelemonandroidfinalproject.ui.theme.LittleLemonColor.green
import com.example.littlelemonandroidfinalproject.ui.theme.button
import com.example.littlelemonandroidfinalproject.ui.theme.caption
import com.example.littlelemonandroidfinalproject.ui.theme.customShapes


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun Onboarding (navController: NavHostController){

    val keyboardController = LocalSoftwareKeyboardController.current
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    val context = LocalContext.current


    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
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

            Row(
                modifier = Modifier
                    .background(color = green)
                    .fillMaxWidth()
                    .height(100.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.welcome_text),
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White
                )
            }

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

            // Collect user input
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
                        onValueChange = { firstName = it },
                        label = { Text("First name") },
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
                        onValueChange = { lastName = it },
                        label = { Text("Last name") },
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
                        onValueChange = { email = it },
                        label = { Text("Email") },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(onDone = {
                        // Hide the keyboard
                            keyboardController?.hide()

                            }),
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }

                Spacer(modifier = Modifier.height(90.dp))

                // Register button

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                ) {
                    Button(
                        onClick = { // Input validation logic
                            if (firstName.isBlank() || lastName.isBlank() || email.isBlank()) {
                                // If any of the fields is empty, show an error message
                                showToast(
                                    context,
                                    "Registration unsuccessful. Please enter all data."
                                )
                            } else {
                                // If all fields are filled, save the user data to SharedPreferences
                                val sharedPreferences =
                                    context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
                                val editor = sharedPreferences.edit()
                                editor.putString("firstName", firstName)
                                editor.putString("lastName", lastName)
                                editor.putString("email", email)
                                editor.apply()

                                // Show a success message and navigate to the Home screen
                                saveUserData(context, firstName, lastName, email)
                                showToast(context, "Registration successful!")

                                // Navigate to the Home screen
                                navController.navigate("Home")
                            }
                        },
                        //border = BorderStroke(1.dp, SolidColor(Color(0xFFFFA500))),
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
                            stringResource(R.string.button_register),
                            style = button

                            )
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun OnboardingPreview(){
    val navController = rememberNavController()
    Onboarding(navController = navController)
}
private fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

private fun saveUserData(context: Context, firstName: String, lastName: String, email: String) {
    val sharedPref = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
    sharedPref.edit {
        putString("firstName", firstName)
        putString("lastName", lastName)
        putString("email", email)
        putBoolean("userRegistered", true)
    }
}


