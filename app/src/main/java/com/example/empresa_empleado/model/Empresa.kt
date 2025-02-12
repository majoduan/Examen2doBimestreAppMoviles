package com.example.empresa_empleado.model

data class Empresa(
    val id: Int,
    var nombre: String,
    var direccion: String,
    var fechaFundacion: String,
    val ingresoAnual: Double,
    var esMultinacional: Boolean,
    var latitud: Double = 0.0,
    var longitud: Double = 0.0,
    val empleados: MutableList<Empleado> = mutableListOf()
)
