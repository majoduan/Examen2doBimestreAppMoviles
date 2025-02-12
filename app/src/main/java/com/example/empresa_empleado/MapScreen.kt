package com.example.empresa_empleado

import android.Manifest
import android.content.pm.PackageManager
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import androidx.compose.material3.MaterialTheme
import com.example.empresa_empleado.model.EmpresaEntity
import com.google.android.gms.maps.CameraUpdateFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(
    empresa: EmpresaEntity?,
    onBackClick: () -> Unit
) {
    val context = LocalContext.current

    // Si no hay empresa, mostrar un mensaje
    if (empresa == null) {
        Scaffold(
            topBar = {
                SmallTopAppBar(
                    title = { Text("Mapa") },
                    navigationIcon = {
                        IconButton(onClick = onBackClick) {
                            Text("←")
                        }
                    }
                )
            }
        ) { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = androidx.compose.ui.Alignment.Center
            ) {
                Text("No se encontró la empresa")
            }
        }
        return
    }

    // Crear la posición de la empresa
    val empresaLocation = LatLng(empresa.latitud, empresa.longitud)

    // Configurar la posición inicial de la cámara
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(empresaLocation, 15f)
    }

    // Efecto para actualizar la posición de la cámara cuando cambia la ubicación
    LaunchedEffect(empresaLocation) {
        cameraPositionState.animate(
            update = CameraUpdateFactory.newLatLngZoom(
                empresaLocation,
                15f
            ),
            durationMs = 1000
        )
    }

    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text(empresa.nombre) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Text("←")
                    }
                }
            )
        }
    ) { padding ->
        GoogleMap(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            cameraPositionState = cameraPositionState,
            properties = MapProperties(
                isMyLocationEnabled = false // Cambiado a false ya que nos enfocamos en la ubicación de la empresa
            )
        ) {
            // Marcador de la empresa
            Marker(
                state = MarkerState(position = empresaLocation),
                title = empresa.nombre,
                snippet = empresa.direccion
            )
        }
    }
}