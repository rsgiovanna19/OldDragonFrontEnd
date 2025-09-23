package com.example.myapplication.view

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.controller.PersonagemController
import com.example.myapplication.model.Classe
import com.example.myapplication.model.Raca
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue

@Composable
fun PersonagemScreen(controller: PersonagemController) {
    val personagem by controller.personagem
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Criação de Personagem", fontSize = 28.sp, modifier = Modifier.padding(bottom = 16.dp))
        OutlinedTextField(
            value = personagem.nome,
            onValueChange = { controller.atualizarNome(it) },
            label = { Text("Nome do Personagem") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = if (personagem.idade == 0) "" else personagem.idade.toString(),
            onValueChange = { controller.atualizarIdade(it) },
            label = { Text("Idade do Personagem") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        )
        Text(text = "Escolha o estilo de atributos:", fontSize = 20.sp)
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        ) {
            Button(onClick = { controller.gerarAtributos(1) }) { Text("Clássico") }
            Button(onClick = { controller.gerarAtributos(2) }) { Text("Aventureiro") }
            Button(onClick = { controller.gerarAtributos(3) }) { Text("Heróico") }
        }
        Divider(modifier = Modifier.padding(vertical = 16.dp))
        Text(text = "Seus Atributos:", fontSize = 20.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Column {
                Text("Força: ${personagem.atributos.forca}", fontSize = 16.sp)
                Text("Destreza: ${personagem.atributos.destreza}", fontSize = 16.sp)
                Text("Constituição: ${personagem.atributos.constituicao}", fontSize = 16.sp)
            }
            Column {
                Text("Inteligência: ${personagem.atributos.inteligencia}", fontSize = 16.sp)
                Text("Sabedoria: ${personagem.atributos.sabedoria}", fontSize = 16.sp)
                Text("Carisma: ${personagem.atributos.carisma}", fontSize = 16.sp)
            }
        }
        Divider(modifier = Modifier.padding(vertical = 16.dp))
        Text(text = "Escolha sua Raça:", fontSize = 20.sp)
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        ) {
            Button(onClick = { controller.atualizarRaca(Raca.HUMANO) }) { Text("Humano") }
            Button(onClick = { controller.atualizarRaca(Raca.ELFO) }) { Text("Elfo") }
            Button(onClick = { controller.atualizarRaca(Raca.ANAO) }) { Text("Anão") }
        }
        Text(text = "Escolha sua Classe:", fontSize = 20.sp)
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        ) {
            Button(onClick = { controller.atualizarClasse(Classe.GUERREIRO) }) { Text("Guerreiro") }
            Button(onClick = { controller.atualizarClasse(Classe.LADRAO) }) { Text("Ladrão") }
            Button(onClick = { controller.atualizarClasse(Classe.MAGO) }) { Text("Mago") }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PersonagemScreenPreview() {
    val controller = PersonagemController()
    PersonagemScreen(controller = controller)
}
