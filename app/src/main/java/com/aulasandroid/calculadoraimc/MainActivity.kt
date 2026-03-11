package com.aulasandroid.calculadoraimc

import android.R.attr.fontWeight
import android.R.attr.label
import android.R.attr.onClick
import android.R.attr.text
import android.R.attr.y
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation.Companion.keyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.F
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aulasandroid.calculadoraimc.ui.theme.CalculadoraIMCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculadoraIMCTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CalculadoraIMCScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun CalculadoraIMCScreen(modifier: Modifier = Modifier) {

    var altura by remember {
        mutableStateOf("")
    }

    var peso by remember {
        mutableStateOf("")
    }

    var imc by remember {
        mutableStateOf(0.0)
    }

    fun calcularImc(altura: Double, peso: Double): Double{
        var alturaMetros = altura / 100
        var resultado = peso / (alturaMetros * alturaMetros)

        return resultado
    }

    var classificacao = when {
        imc in 0.0..18.4 -> "Abaixo do peso"
        imc in 18.5..24.9 -> "peso ideial"
        imc in 25.0..29.9 -> "Sobre peso"
        imc in 30.0..34.9 -> "Obesidade I"
        imc in 35.0..39.9 -> "Obesidade II"
        else -> "Obesidade III"
    }


//    var calcularImc = altura * altura / peso


    Column(modifier = modifier.fillMaxSize()) {
        //      --header
        Column(
            modifier = Modifier.fillMaxWidth()
                .height(160.dp)
                .background(color = colorResource(R.color.cor_app)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.bmi),
                contentDescription = "IMC logo",
                modifier = Modifier.size(80.dp)
                    .padding(vertical = 16.dp)
            )

            Text(
                text = "Calculadora IMC",
                fontSize = 24.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 32.dp)
        ) {
            // -- form
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .size(250.dp)
                    .offset(y = (-30).dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFF9F6F6)
                ),
                elevation = CardDefaults.cardElevation(4.dp),

                ) {

                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        modifier = Modifier
                            .offset(y = (20).dp),
                        text = "Seus dados",
                        color = colorResource(R.color.cor_app),
                        textAlign = TextAlign.Center,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(40.dp))

                    OutlinedTextField(
                        value = altura,
                        onValueChange = { novoValor ->
                            altura = novoValor
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        label = {
                            Text(text = "Altura")
                        },
                        shape = RoundedCornerShape(20.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedBorderColor = colorResource(R.color.cor_app),
                            focusedBorderColor = Color.Blue
                        )
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    OutlinedTextField(
                        value = peso,
                        onValueChange = { novoValor ->
                            peso = novoValor
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        label = {
                            Text(text = "Peso")
                        },
                        shape = RoundedCornerShape(20.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedBorderColor = colorResource(R.color.cor_app),
                            focusedBorderColor = Color.Blue
                        )
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    Button(
                        modifier = Modifier
                            .width(280.dp)
                            .height(50.dp),
                        onClick = {
                            imc = calcularImc(altura.toDouble(), peso.toDouble())
                        },
                        colors = ButtonDefaults.buttonColors(
                            colorResource(R.color.cor_app)
                        ),
                        shape = RoundedCornerShape(30.dp)
                    ) {
                        Text(
                            text = "CALCULAR",
                            fontSize = 16.sp
                        )
                    }
                }
            }

//            Spacer(modifier = Modifier.height(20.dp))
//

//            Card(
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                Row(
//
//                ) { }
//            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .width(400.dp)
                        .height(80.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .background(Color(62, 148, 96, 255))
                ) {

                     Text(text= "%.1f".format(imc))
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = classificacao,
                        textAlign = TextAlign.Center,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }


//                Button(
//                    modifier = Modifier
//                        .width(400.dp)
//                        .height(80.dp),
//                    onClick = {},
//                    colors = ButtonDefaults.buttonColors(
//                        containerColor = Color(62, 148, 96, 255),
//                    ),
//                    shape = RoundedCornerShape(14.dp)
//                ) {
//                    Text(
//                        text = "Peso ideal",
//                        fontSize = 24.sp,
//                        fontWeight = FontWeight.Bold
//                    )
//                }
            }


            //-- result
        }
    }
}

