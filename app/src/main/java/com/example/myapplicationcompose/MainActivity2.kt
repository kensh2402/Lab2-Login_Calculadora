package com.example.myapplicationcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplicationcompose.ui.theme.MyApplicationTheme

class MainActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val nombre = intent.getStringExtra("username") ?: "Invitado"
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFFA5D6A7) // Green background
                )
                 {
                    Calculadora(nombre)
                }
            }
        }
    }
}

@Composable
fun Calculadora(nombre: String) {
    val context = LocalContext.current
    var num1 by remember { mutableStateOf("") }
    var num2 by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.systemBars.asPaddingValues())
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Calculadora",
            style = MaterialTheme.typography.headlineLarge,
            color = Color(0xFF284B63)
        )

        Text(
            text = "Bienvenido: $nombre",
            style = MaterialTheme.typography.bodyMedium,
            color = Color(0xFF353535)
        )

        /*Numero 1*/
        TextField(
            value = num1,
            onValueChange = { num1 = it },
            label = { Text("Primer número") },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFD9D9D9),
                unfocusedContainerColor = Color(0xFFD9D9D9),
                disabledContainerColor = Color(0xFFD9D9D9),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            )
        )
        /*Numero 2*/
        TextField(
            value = num2,
            onValueChange = { num2 = it },
            label = { Text("Segundo número") },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFD9D9D9),
                unfocusedContainerColor = Color(0xFFD9D9D9),
                disabledContainerColor = Color(0xFFD9D9D9),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            )
        )

        // Botones de operaciones fila 1
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {
                    val n1 = num1.toFloatOrNull()
                    val n2 = num2.toFloatOrNull()
                    if (n1 != null && n2 != null) {
                        resultado = (n1 + n2).toString()
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF284B63)),
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp)
            ) {
                Text(text = "+", color = Color.White, style = MaterialTheme.typography.bodyLarge)
            }

            Button(
                onClick = {
                    val n1 = num1.toFloatOrNull()
                    val n2 = num2.toFloatOrNull()
                    if (n1 != null && n2 != null) {
                        resultado = (n1 - n2).toString()
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF284B63)),
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp)
            ) {
                Text(text = "-", color = Color.White, style = MaterialTheme.typography.bodyLarge)
            }
        }

        // Botones de operaciones fila 2
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {
                    val n1 = num1.toFloatOrNull()
                    val n2 = num2.toFloatOrNull()
                    if (n1 != null && n2 != null) {
                        resultado = (n1 * n2).toString()
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF388E3C)),
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp)
            ) {
                Text(text = "*", color = Color.White, style = MaterialTheme.typography.bodyLarge)
            }

            Button(
                onClick = {
                    val n1 = num1.toFloatOrNull()
                    val n2 = num2.toFloatOrNull()
                    if (n1 != null && n2 != null && n2 != 0f) {
                        resultado = (n1 / n2).toString()
                    } else {
                        resultado = "División inválida"
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF388E3C)),
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp)
            ) {
                Text(text = "/", color = Color.White, style = MaterialTheme.typography.bodyLarge)
            }
        }

        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Resultado",
            style = MaterialTheme.typography.bodyLarge,
            color = Color(0xFF353535)
        )

        TextField(
            value = resultado,
            onValueChange = {},
            readOnly = true,
            label = { Text("Resultado") },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFD9D9D9),
                unfocusedContainerColor = Color(0xFFD9D9D9),
                disabledContainerColor = Color(0xFFD9D9D9),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            )
        )

        // Botón salir
        Row(
            horizontalArrangement = Arrangement.Center
        ){
            Button(
                onClick = {
                    (context as? ComponentActivity)?.finish()
                          },

                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF388E3C)),
            ) {
                Text("Salir", color = Color.White)
            }
        }

    }
}



@Preview(showBackground = true)
@Composable
fun CalculadoraPreview() {
    MyApplicationTheme {
        Calculadora("Usuario")
    }
}
