package com.example.demologin.presentation.ui.screens

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.DrawerState
import androidx.compose.material.DrawerValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
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
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.demologin.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
@Composable
fun LoginScreen() {
    val keyboardController = LocalSoftwareKeyboardController.current
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var Recoveryemail by remember { mutableStateOf(TextFieldValue("")) }
    var RecoveryPassword by remember { mutableStateOf(TextFieldValue("")) }
    var RecoveryConfirmPassword by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )
    val activeDialog = remember {
        mutableStateOf("1")
    }
    val scope = rememberCoroutineScope()
    val openDialog = remember { mutableStateOf(false) }
    var enabled by remember { mutableStateOf(false)}
    var text by remember { mutableStateOf("") }
    val context = LocalContext.current
//    if(openDialog.value){
//        dialogBox(openDialog = openDialog)
//    }
//    if(drawerState.isOpen){
//        ModalDrawerSample(drawerState = drawerState)
//    }

//    if(showDialog.value){
//        alert(msg = "Forgot Password",
//            showDialog = showDialog.value,
//        onDismiss = {showDialog.value = false})
//    }

    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetShape = RoundedCornerShape(20.dp),
        modifier = Modifier.imePadding(),
        sheetContent = {
            when(activeDialog.value){
                "1" -> {
                    Column(verticalArrangement = Arrangement.SpaceBetween, modifier = Modifier.padding(15.dp)) {
                        Row(modifier = Modifier
                            .padding(15.dp)
                            .fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(130.dp)) {
                            Text(
                                text = "Forgot Password",
                                fontWeight = FontWeight.Medium,
                                fontSize = 22.sp
                            )
                            Image(
                                painter = painterResource(id = R.drawable.baseline_close_24),
                                contentDescription = "",
                                modifier = Modifier.clickable {
                                    Recoveryemail = TextFieldValue("")
                                    scope.launch {
                                        sheetState.hide()
                                    }

                                }
                            )
                        }
                        TextField(
                            value = Recoveryemail,
                            onValueChange = {
                                Recoveryemail = it
                            },
                            modifier = Modifier.padding(10.dp,30.dp,0.dp,70.dp),
                            label = { Text(text = "Email") },
                            placeholder = { Text(text = "your@email.domain") },
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                            keyboardActions = KeyboardActions(
                                onDone = { keyboardController?.hide() }),
                            colors = TextFieldDefaults.textFieldColors(backgroundColor = White)
                        )
                        Button(
                            onClick = {
                                if(Recoveryemail.text.isEmpty()){
                                    Toast.makeText(context,"Please Enter Email !!",Toast.LENGTH_LONG).show()
                                }else if(!Patterns.EMAIL_ADDRESS.matcher(Recoveryemail.text).matches()){
                                    Toast.makeText(context,"Please Enter Valid Email !!",Toast.LENGTH_LONG).show()
                                }
                                else{
                                    Recoveryemail = TextFieldValue("")
                                    scope.launch {
                                        //sheetState.hide()
                                        activeDialog.value = "2"

                                        Log.d("sheetState value",sheetState.currentValue.name)

                                    }
                                }
                            },
                            shape = RoundedCornerShape(20.dp),
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .width(350.dp)
                                .height(50.dp)
                        ) {
                            Text(text = "Proceed", fontSize = 17.sp)
                        }

                    }
                }
                "2" -> {
                    Column(verticalArrangement = Arrangement.SpaceBetween, modifier = Modifier.padding(15.dp)) {
                        Row(modifier = Modifier
                            .padding(15.dp)
                            .fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(130.dp)) {
                            Text(
                                text = "Reset Password",
                                fontWeight = FontWeight.Medium,
                                fontSize = 22.sp
                            )
                            Image(
                                painter = painterResource(id = R.drawable.baseline_close_24),
                                contentDescription = "",
                                modifier = Modifier.clickable {
                                    RecoveryPassword = TextFieldValue("")
                                    RecoveryConfirmPassword = TextFieldValue("")
                                    scope.launch {
                                        sheetState.hide()
                                    }

                                }
                            )
                        }
                        TextField(
                            value = RecoveryPassword,
                            onValueChange = {
                                RecoveryPassword = it
                            },
                            modifier = Modifier.padding(10.dp,30.dp,0.dp,20.dp),
                            label = { Text(text = "Password") },
                            placeholder = { Text(text = "******") },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.NumberPassword
                            ),
                            colors = TextFieldDefaults.textFieldColors(backgroundColor = White)
                        )

                        TextField(
                            value = RecoveryConfirmPassword,
                            onValueChange = {
                                RecoveryConfirmPassword = it
                            },
                            modifier = Modifier.padding(10.dp,30.dp,0.dp,70.dp),
                            label = { Text(text = "Confirm Password") },
                            placeholder = { Text(text = "******") },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.NumberPassword
                            ),
                            colors = TextFieldDefaults.textFieldColors(backgroundColor = White)
                        )

                        Button(
                            onClick = {
                                if(RecoveryPassword.text.isEmpty() && RecoveryConfirmPassword.text.isEmpty()){
                                    Toast.makeText(context,"Please Enter Password and Confirm Password !!",Toast.LENGTH_LONG).show()
                                }else if(RecoveryPassword.text == RecoveryConfirmPassword.text){
                                    RecoveryPassword = TextFieldValue("")
                                    RecoveryConfirmPassword = TextFieldValue("")
                                    scope.launch {
//                                        activeDialog.value = "2"
                                        sheetState.hide()
                                        Toast.makeText(context,"Password has been reset successfully !!",Toast.LENGTH_LONG).show()
                                        Log.d("sheetState value",sheetState.currentValue.name)

                                    }

                                }else{
                                    Toast.makeText(context,"Password and Confirm Password should be same !!",Toast.LENGTH_LONG).show()
                                }
                            },
                            shape = RoundedCornerShape(20.dp),
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .width(350.dp)
                                .height(50.dp)
                        ) {
                            Text(text = "Proceed", fontSize = 17.sp)
                        }

                    }
                }
                else -> {

                }
            }



        },
        content = {
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
//                    Text(text = "Forgot Password?",color = Gray, fontSize = 16.sp)
//                    ClickableText(
//                        text = AnnotatedString(text) ,
//                        onClick = {
//                            if (enabled) {
//                                enabled = false
//                                text = "Forgot Password?"
//                            }
//
//                    })

                            Button(
//                        onClick = { openDialog.value = true },
                                onClick = {
                                    scope.launch {
                                        activeDialog.value = "1"
                                        sheetState.show()
                                        Log.d("sheetState value",sheetState.currentValue.name)
                                    }
                                },
                                colors = ButtonDefaults.buttonColors(backgroundColor = White)
//                    .padding(20.dp)
                            ) {
                                Text(text = "Forgot Password?")
                            }
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
                    val context = LocalContext.current
                    Button(
                        onClick = {
                            if (email.text.isNotEmpty() && password.text.isNotEmpty()){
                                enabled = true
                                Toast.makeText(context,"Login is succesfull",Toast.LENGTH_LONG).show()
                            }else{

                                Toast.makeText(context,"Please Enter Email & Password",Toast.LENGTH_LONG).show()
                            }
                        },
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
    )




}
















@Composable
fun ModalDrawerSample(drawerState: DrawerState) {

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
            //LoginScreen()
//            Column {
//                Text("Text in Bodycontext")
//                Button(onClick = {
//
//                    scope.launch {
//                        drawerState.open()
//                    }
//
//                }) {
//                    Text("Click to open")
//                }
//            }
        }
    )
}



//@Composable
//fun dialogBox(openDialog: MutableState<Boolean>){
//    var email by remember { mutableStateOf("") }
//
//    if (openDialog.value) {
//        AlertDialog(
//            onDismissRequest = {
//                openDialog.value = false
//            },
////            title = {
////                Text(text = "Forgot Password",modifier = Modifier.padding(10.dp,20.dp,10.dp,80.dp))
////            },
//            text = {
//                Column() {
//                    Text(text = "Forgot Password",modifier = Modifier.padding(10.dp,20.dp,10.dp,20.dp), fontSize = 17.sp)
//                    TextField(
//                        value = email,
//                        onValueChange = { email = it },
//                        label = { Text(text = "Email") },
//                        placeholder = { Text(text = "your@email.domain") },
//                        colors = TextFieldDefaults.textFieldColors(backgroundColor = White),
//                    )
//
//                }
//            },
//            buttons = {
//                Row(
//                    modifier = Modifier.padding(all = 8.dp),
//                    horizontalArrangement = Arrangement.Center
//                ) {
//                    Button(
//                        modifier = Modifier.fillMaxWidth(),
//                        onClick = { openDialog.value = false }
//                    ) {
//                        Text("Proceed")
//                    }
//                }
//            }
//        )
//    }
//
//}
//@Composable
//fun alert(msg: String, showDialog: Boolean, onDismiss: () -> Unit) {
//    if(showDialog){
//        AlertDialog(
//            title = {
//                Text(text = msg)
//            },
//            onDismissRequest = onDismiss,
//            confirmButton = {
//                TextButton(onClick = onDismiss) {
//                    Text(text = "Dismiss")
//                }
//            },
//            dismissButton = {}
//        )
//    }
//}
