package com.example.myapplicationcompose

import android.os.Bundle
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplicationcompose.ui.theme.MyApplicationTheme
import com.example.myapplicationcompose.R
import android.widget.Toast
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarDuration
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.launch



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFFA5D6A7) // Green background
                )  {
                    Login()
                }
            }
        }
    }
}

@Composable
fun Login() {
    /*almacenar credenciales*/
    val credenciales = mapOf(
        "kensh2402" to "Ozymandias_1",
        "paco01" to "Lunallena_0"
    )
    val context = LocalContext.current
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
                .size(150.dp)
                .padding(bottom = 32.dp)
        )
        Text(
            text = "Login",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Usuario") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))
        val snackbarHostState = SnackbarHostState()
        val coroutineScope = rememberCoroutineScope()

        Button(
            onClick = {
                /*Validación de Credenciales*/
                val Verificacion = credenciales[username] == password
                if(Verificacion){
                    val intent = Intent(context, MainActivity2::class.java).apply{
                        putExtra("username",username)
                    }
                    context.startActivity(intent)
                }
                else{

                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(
                            message = "Una contraseña válida debe cumplir los siguientes requisitos: Tener más de 8 caracteres, Contener una letra mayúscula, Contener una letra minúscula, Contener un número, Contener un guión bajo",
                            duration = SnackbarDuration.Long
                        )
                    }

                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF4CAF50),
                contentColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth()
        )

        {
            SnackbarHost(hostState = snackbarHostState)

            Text("Iniciar Sesión")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    MyApplicationTheme {
        Login()
    }
}
