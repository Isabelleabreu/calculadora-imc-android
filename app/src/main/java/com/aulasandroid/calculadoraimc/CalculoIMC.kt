package com.aulasandroid.calculadoraimc

import androidx.compose.ui.graphics.Color


fun calcularImc(altura: Double, peso: Double): Double{
    var alturaMetros = altura / 100
    var resultado = peso / (alturaMetros * alturaMetros)

    return resultado
}

fun definirCategoria(imc: Double): String {

    return when {
        imc in 0.0..18.4 -> "Abaixo do peso"
        imc in 18.5..24.9 -> "peso ideial"
        imc in 25.0..29.9 -> "Sobre peso"
        imc in 30.0..34.9 -> "Obesidade I"
        imc in 35.0..39.9 -> "Obesidade II"
        else -> "Obesidade III"
    }
}

fun definirCorCategoria(imc: Double): Color {

    return when {
        imc in 0.0..18.4 -> Color.Red
        imc in 18.5..24.9 -> Color(62, 148, 96, 255)
        imc in 25.0..29.9 -> Color(255, 152, 0, 255)
        imc in 30.0..34.9 -> Color.Red
        imc in 35.0..39.9 -> Color.Red
        else -> Color.Red
    }
}