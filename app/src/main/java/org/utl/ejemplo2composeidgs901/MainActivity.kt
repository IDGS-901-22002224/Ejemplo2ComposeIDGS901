package org.utl.ejemplo2composeidgs901

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "suma") {
                composable("inicio") { PantallaInicio(navController) }
                composable("detalle/{nombre}") { backStackEntry ->
                    val nombre = backStackEntry.arguments?.getString("nombre") ?: "Invitado"
                    PantallaDetalle(navController, nombre)
                }
                composable("suma") { SumaDosNumeros(navController) }
            }
        }
    }
}

@Composable
fun PantallaInicio(navController: NavHostController) {
    var nombre by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Pantalla de inicio")
        Spacer(modifier = Modifier.height(24.dp))

        TextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Ingrese su nombre") },
            modifier = Modifier.fillMaxWidth(0.8f)
        )

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            val nombreStr = if (nombre.isBlank()) "Invitado" else nombre
            navController.navigate("detalle/$nombreStr")
        }) {
            Text("Ir a Detalle con nombre")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate("suma") }) {
            Text("Ir a Suma de Números")
        }
    }
}

@Composable
fun PantallaDetalle(navController: NavHostController, nombre: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Hola, $nombre")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate("inicio") }) {
            Text("Volver")
        }
    }
}