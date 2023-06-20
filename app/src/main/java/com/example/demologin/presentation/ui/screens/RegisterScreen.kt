package com.example.demologin.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.demologin.R

@Composable
fun RegisterScreen(){
    Box() {
        Column() {
            Column(
                modifier = Modifier
                    .background(Color(0xFF1C83F3))
                    .size(400.dp)

            ) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_beenhere_24),
                    contentDescription = "",
                    modifier = Modifier
                        .padding(150.dp, 70.dp)
                        .size(80.dp)
                        .clip(CircleShape)
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .weight(1f)
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ){
                Column(verticalArrangement = Arrangement.SpaceBetween) {
                    Text(text = "Login",modifier = Modifier.align(Alignment.CenterHorizontally).padding(0.dp,120.dp,0.dp,40.dp), color = Color.Blue, fontSize = 16.sp)
                }
            }
        }
        Box(modifier = Modifier
            .align(Alignment.Center)
            .background(Color.White)) {
            Surface(
                modifier = Modifier.background(Color.White),
                shape = RoundedCornerShape(20.dp,20.dp,20.dp,20.dp),
                elevation = 8.dp)
            {
                Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
                    var name by remember { mutableStateOf(TextFieldValue("")) }
                    var email by remember { mutableStateOf(TextFieldValue("")) }
                    var password by remember { mutableStateOf(TextFieldValue("")) }
                    TextField(
                        value = name,
                        onValueChange = {
                            name = it
                        },
                        modifier = Modifier.padding(20.dp,40.dp,20.dp,0.dp),
                        label = { Text(text = "Name") },
                        placeholder = { Text(text = "your name") },
                        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White)
                    )

                    TextField(
                        value = email,
                        onValueChange = {
                            email = it
                        },
                        modifier = Modifier.padding(20.dp,20.dp,20.dp,20.dp),
                        label = { Text(text = "Email") },
                        placeholder = { Text(text = "your@email.domain") },
                        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White)
                    )

                    TextField(
                        value = password,
                        onValueChange = {
                            password = it
                        },
                        modifier = Modifier.padding(20.dp,0.dp,0.dp,100.dp),
                        label = { Text(text = "Password") },
                        placeholder = { Text(text = "******") },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.NumberPassword
                        ),
                        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White)

                    )
                }
            }
            Button(
                onClick = {},
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .width(150.dp)
                    .height(60.dp)
//                    .padding(20.dp)
            ) {
                Text(text = "Register")
            }
        }
    }
}