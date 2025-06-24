package org.utl.ejemplo2composeidgs901

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun SumaDosNumeros(navController: NavHostController) {
    var num1 by remember { mutableStateOf("") }
    var num2 by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("") }
    var operacion by remember { mutableStateOf("Sumar") } // Operación por defecto

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = num1,
            onValueChange = { num1 = it },
            label = { Text("Número 1") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(0.8f)
        )
        TextField(
            value = num2,
            onValueChange = { num2 = it },
            label = { Text("Número 2") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(0.8f)
        )

        // Grupo de Radio Buttons en disposición vertical
        Text("Seleccionar operación", fontSize = 16.sp)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .selectableGroup()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            listOf("Sumar", "Restar", "Multiplicación", "División").forEach { op ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = (operacion == op),
                            onClick = { operacion = op }
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (operacion == op),
                        onClick = { operacion = op }
                    )
                    Text(" $op")
                }
            }
        }

        Button(onClick = {
            val n1 = num1.toDoubleOrNull()
            val n2 = num2.toDoubleOrNull()
            resultado = if (n1 != null && n2 != null) {
                when (operacion) {
                    "Sumar" -> "Resultado: ${n1 + n2}"
                    "Restar" -> "Resultado: ${n1 - n2}"
                    "Multiplicación" -> "Resultado: ${n1 * n2}"
                    "División" -> if (n2 != 0.0) "Resultado: ${n1 / n2}" else "Error: División por cero"
                    else -> "Operación no válida"
                }
            } else {
                "Ingrese números válidos"
            }
        }) {
            Text("Calcular")
        }
        Text(text = resultado, fontSize = 18.sp)

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate("inicio") }) {
            Text("Volver")
        }
    }
}