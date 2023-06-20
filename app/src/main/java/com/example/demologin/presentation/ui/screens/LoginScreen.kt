package com.example.demologin.presentation.ui.screens

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.DrawerState
import androidx.compose.material.DrawerValue
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalDrawer
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.primarySurface
import androidx.compose.material.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.demologin.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun LoginScreen() {
    val openDialog = remember { mutableStateOf(false) }
    val text by remember { mutableStateOf("") }
    if(openDialog.value){
        dialogBox(openDialog = openDialog)
    }
    val showDialog = remember { mutableStateOf(false) }
//    if(showDialog.value){
//        ModalDrawerSample()
//    }
//    if(showDialog.value){
//        alert(msg = "Forgot Password",
//            showDialog = showDialog.value,
//        onDismiss = {showDialog.value = false})
//    }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
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
                    .background(White)
                    .weight(1f)
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ){
                Column(verticalArrangement = Arrangement.SpaceBetween) {
                    Text(text = "Register",modifier = Modifier
                        .align(CenterHorizontally)
                        .padding(0.dp, 40.dp, 0.dp, 40.dp), color = Blue, fontSize = 16.sp)
                    Text(text = "Forgot Password?",color = Gray, fontSize = 16.sp)
                }
            }
        }
        Box(modifier = Modifier, contentAlignment = Alignment.BottomCenter) {
            Surface(
                color = White ,
                shape = RoundedCornerShape(20.dp),
                elevation = 8.dp)
            {
                Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
                    var email by remember { mutableStateOf(TextFieldValue("")) }
                    var password by remember { mutableStateOf(TextFieldValue("")) }
                    TextField(
                        value = email,
                        onValueChange = {
                            email = it
                        },
                        modifier = Modifier.padding(20.dp,40.dp,20.dp,20.dp),
                        label = { Text(text = "Email") },
                        placeholder = { Text(text = "your@email.domain") },
                        colors = TextFieldDefaults.textFieldColors(backgroundColor = White)
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
                        colors = TextFieldDefaults.textFieldColors(backgroundColor = White)

                    )
                }
            }
            Button(
                onClick = { openDialog.value = true },
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .width(150.dp)
                    .height(60.dp)
//                    .padding(20.dp)
                    ) {
                Text(text = "Login")
            }
        }
    }
}

@Composable
fun alert(msg: String, showDialog: Boolean, onDismiss: () -> Unit) {
    if(showDialog){
        AlertDialog(
            title = {
                Text(text = msg)
            },
            onDismissRequest = onDismiss,
            confirmButton = {
                TextButton(onClick = onDismiss) {
                    Text(text = "Dismiss")
                }
            },
          dismissButton = {}
        )
    }
}

@Composable
fun dialogBox(openDialog: MutableState<Boolean>){
    var text by remember { mutableStateOf("") }

    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            title = {
                Text(text = "Title")
            },
            text = {
                Column() {
                    TextField(
                        value = text,
                        onValueChange = { text = it }
                    )
                    Text("Custom Text")
                    Checkbox(checked = false, onCheckedChange = {})
                }
            },
            buttons = {
                Row(
                    modifier = Modifier.padding(all = 8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { openDialog.value = false }
                    ) {
                        Text("Dismiss")
                    }
                }
            }
        )
    }

}

@Composable
fun ModalDrawerSample() {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalDrawer(
        drawerState = drawerState,
        drawerContent = {
            Column {
                Text("Text in Drawer")
                Button(onClick = {
                    scope.launch {
                        drawerState.close()
                    }
                }) {
                    Text("Close Drawer")
                }
            }
        },
        content = {
            Column {
                Text("Text in Bodycontext")
                Button(onClick = {

                    scope.launch {
                        drawerState.open()
                    }

                }) {
                    Text("Click to open")
                }
            }
        }
    )
}

