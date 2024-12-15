package org.padre_app.project

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        var entradaUsuario by remember { mutableStateOf("") }
        var tareasPendientes by remember { mutableStateOf(listOf<String>()) }
        var tareasCompletadas by remember { mutableStateOf(listOf<String>()) }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(10.dp))
            Text(
                "Introduce tus tareas:",
                modifier = Modifier.padding(end = 160.dp),
                fontFamily = FontFamily.Monospace,
                fontSize = 20.sp
            )

            Spacer(Modifier.height(10.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                TextField(
                    value = entradaUsuario,
                    onValueChange = { entradaUsuario = it },
                    label = { Text("Ingresa una tarea...") },
                    modifier = Modifier.padding(end = 10.dp)
                )

                Button(
                    onClick = {
                        if (entradaUsuario.isNotEmpty()) {
                            tareasPendientes = tareasPendientes + entradaUsuario
                            entradaUsuario = ""
                        }
                    }
                ) {
                    Text("Añadir tarea")
                }
            }

            Spacer(Modifier.height(20.dp))

            Text("Tareas pendientes:")
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp)
            ) {
                for (tarea in tareasPendientes) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(" $tarea", modifier = Modifier.padding(bottom = 8.dp))
                        Button(
                            onClick = {
                                tareasPendientes = tareasPendientes.filterNot { it == tarea }
                                tareasCompletadas = tareasCompletadas + tarea
                            },
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green)
                        ) {
                            Text("Completar", color = Color.Black)
                        }
                    }
                }
            }

            Spacer(Modifier.height(20.dp))

            Text("Tareas completadas:")
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp)
            ) {
                for (tarea in tareasCompletadas) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(" $tarea", modifier = Modifier.padding(bottom = 8.dp))
                        Button(
                            onClick = {
                                tareasCompletadas = tareasCompletadas.filterNot { it == tarea }
                            }
                        ) {
                            Text("Eliminar")
                        }
                    }
                }
            }
        }
    }
}
