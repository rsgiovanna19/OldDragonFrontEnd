package com.example.myapplication.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.controller.PersonagemController
import com.example.myapplication.model.Classe
import com.example.myapplication.model.Raca

// ----------------------- Tela Inicial -----------------------
@Composable
fun TelaInicial(onStartJourney: () -> Unit) {
    val borderBrush = Brush.linearGradient(
        colors = listOf(Color.Red, Color(0xFFFFA500), Color.Yellow)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .border(width = 8.dp, brush = borderBrush, shape = RoundedCornerShape(16.dp))
            .background(Color.Black)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "OLD DRAGON",
                fontSize = 36.sp,
                color = Color.Red,
                fontFamily = FontFamily.Serif,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            Button(
                onClick = onStartJourney,
                modifier = Modifier.padding(top = 32.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
            ) {
                Text(
                    text = "EMBARQUE NESTA JORNADA",
                    color = Color.White,
                    fontSize = 18.sp
                )
            }
        }
    }
}

// ----------------------- Tela Nome e Idade -----------------------
@Composable
fun TelaNomeIdade(controller: PersonagemController, onNext: () -> Unit) {
    val personagem by controller.personagem
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = personagem.nome,
            onValueChange = { controller.atualizarNome(it) },
            label = { Text("Nome do Personagem") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = if (personagem.idade == 0) "" else personagem.idade.toString(),
            onValueChange = { controller.atualizarIdade(it) },
            label = { Text("Idade do Personagem") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
        )

        val mensagem = if ((personagem.nome.length + personagem.idade) > 99)
            "Seja bem vindo a este mundo... novamente"
        else
            "Seja bem vindo a este mundo"

        Text(
            text = mensagem,
            modifier = Modifier.padding(top = 16.dp),
            fontSize = 18.sp,
            color = Color.Red
        )

        Button(onClick = onNext, modifier = Modifier.padding(top = 24.dp)) {
            Text("Próximo")
        }
    }
}

// ----------------------- Tela Raça -----------------------
@Composable
fun TelaRaca(controller: PersonagemController, onNext: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            "Escolha sua Raça",
            fontSize = 20.sp,
            modifier = Modifier.padding(16.dp),
            color = Color.Red
        )
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = { controller.atualizarRaca(Raca.HUMANO); onNext() }) { Text("Humano") }
            Button(onClick = { controller.atualizarRaca(Raca.ELFO); onNext() }) { Text("Elfo") }
            Button(onClick = { controller.atualizarRaca(Raca.ANAO); onNext() }) { Text("Anão") }
        }
    }
}

// ----------------------- Tela Classe -----------------------
@Composable
fun TelaClasse(controller: PersonagemController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            "Escolha sua Classe",
            fontSize = 20.sp,
            modifier = Modifier.padding(16.dp),
            color = Color.Red
        )
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = { controller.atualizarClasse(Classe.GUERREIRO) }) { Text("Guerreiro") }
            Button(onClick = { controller.atualizarClasse(Classe.LADRAO) }) { Text("Ladrão") }
            Button(onClick = { controller.atualizarClasse(Classe.MAGO) }) { Text("Mago") }
        }
    }
}

// ----------------------- Fluxo de Navegação 4 Etapas -----------------------
@Composable
fun PersonagemFlow(controller: PersonagemController) {
    var etapa by remember { mutableStateOf(1) }

    when (etapa) {
        1 -> TelaInicial { etapa = 2 }
        2 -> TelaNomeIdade(controller) { etapa = 3 }
        3 -> TelaRaca(controller) { etapa = 4 }
        4 -> TelaClasse(controller)
    }
}

// ----------------------- Previews -----------------------
@Preview(showBackground = true)
@Composable
fun PreviewTelaInicial() {
    TelaInicial(onStartJourney = {})
}

@Preview(showBackground = true)
@Composable
fun PreviewTelaNomeIdade() {
    val controller = PersonagemController()
    TelaNomeIdade(controller = controller, onNext = {})
}

@Preview(showBackground = true)
@Composable
fun PreviewTelaRaca() {
    val controller = PersonagemController()
    TelaRaca(controller = controller, onNext = {})
}

@Preview(showBackground = true)
@Composable
fun PreviewTelaClasse() {
    val controller = PersonagemController()
    TelaClasse(controller = controller)
}

@Preview(showBackground = true)
@Composable
fun PreviewPersonagemFlow() {
    val controller = PersonagemController()
    PersonagemFlow(controller = controller)
}
