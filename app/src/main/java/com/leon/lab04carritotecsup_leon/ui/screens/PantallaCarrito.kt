package com.leon.lab04carritotecsup_leon.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.leon.lab04carritotecsup_leon.data.Producto
import com.leon.lab04carritotecsup_leon.ui.components.BarraSuperior
import com.leon.lab04carritotecsup_leon.ui.components.FormularioProducto
import com.leon.lab04carritotecsup_leon.ui.theme.Lab04CarritoTecsupLeonTheme

@Composable
fun PantallaCarrito() {
    // Estados del formulario (Lab 03)
    var nombre by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("") }
    var cantidad by remember { mutableStateOf("") }

    // Estado NUEVO: lista observable
    val productos = remember { mutableStateListOf<Producto>() }

    Scaffold(
        topBar = { BarraSuperior(titulo = "Mi Carrito TECSUP") },
        containerColor = Color.White,
        contentWindowInsets = WindowInsets(0, 0, 0, 0)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(12.dp))
            FormularioProducto(
                nombre = nombre,
                onNombreChange = { nombre = it },
                precio = precio,
                onPrecioChange = { precio = it },
                cantidad = cantidad,
                onCantidadChange = { cantidad = it },
                onAgregar = {
                    val precioNum = precio.toDoubleOrNull() ?: 0.0
                    val cantidadNum = cantidad.toIntOrNull() ?: 0
                    if (nombre.isNotBlank() && precioNum > 0 && cantidadNum > 0) {
                        productos.add(Producto(nombre.trim(), precioNum, cantidadNum))
                        nombre = ""
                        precio = ""
                        cantidad = ""
                    }
                }
            )
            Spacer(modifier = Modifier.height(12.dp))
            HorizontalDivider(color = MaterialTheme.colorScheme.outline)
            Spacer(modifier = Modifier.height(12.dp))

            // Prueba temporal: el número debe aumentar al agregar
            Text(text = "Productos: ${productos.size}")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PantallaCarritoPreview() {
    Lab04CarritoTecsupLeonTheme {
        PantallaCarrito()
    }
}
