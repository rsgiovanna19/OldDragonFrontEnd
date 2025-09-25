package com.example.myapplication.view

import androidx.compose.foundation.background
import com.example.myapplication.R
import androidx.compose.foundation.border
import com.example.myapplication.model.Atributos
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.ui.text.TextStyle
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.Image
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll


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
            .background(Color.Red)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Título
            Text(
                text = "OLD DRAGON",
                fontSize = 36.sp,
                color = Color.Yellow,
                fontFamily = FontFamily.Serif,
                modifier = Modifier.padding(bottom = 32.dp)
            )


            Image(
                painter = painterResource(id = R.drawable.dragoon),
                contentDescription = "Logo do Jogo",
                modifier = Modifier
                    .size(200.dp) // tamanho da imagem
                    .padding(16.dp)
            )

            // Botão
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

// ----------------------- Tela Estilo -----------------------
@Composable
fun TelaEstilo(controller: PersonagemController, onNext: () -> Unit) {
    val personagem by controller.personagem

    val fireBrush = Brush.verticalGradient(
        colors = listOf(Color(0xFF8B0000), Color.Red, Color(0xFFFFA500), Color.Yellow)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(fireBrush)
            .padding(24.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                "Escolha o estilo da aventura",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Yellow,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            // Botões de estilo
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = { controller.estiloAventura = 1; controller.gerarAtributos() },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red, contentColor = Color.Yellow)
                ) { Text("Clássico", fontSize = 20.sp) }

                Button(
                    onClick = { controller.estiloAventura = 2; controller.gerarAtributos() },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red, contentColor = Color.Yellow)
                ) { Text("Aventureiro", fontSize = 20.sp) }

                Button(
                    onClick = { controller.estiloAventura = 3; controller.gerarAtributos() },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red, contentColor = Color.Yellow)
                ) { Text("Heróico", fontSize = 20.sp) }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Exibir atributos rolados dentro de Boxes
            if (personagem.atributos != null) {
                val atributosMap = mapOf(
                    "FORÇA" to personagem.atributos.forca,
                    "DESTREZA" to personagem.atributos.destreza,
                    "CONSTITUIÇÃO" to personagem.atributos.constituicao,
                    "INTELIGÊNCIA" to personagem.atributos.inteligencia,
                    "SABEDORIA" to personagem.atributos.sabedoria,
                    "CARISMA" to personagem.atributos.carisma
                )

                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    atributosMap.forEach { (tipo, valor) ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White.copy(alpha = 0.7f), shape = RoundedCornerShape(8.dp))
                                .padding(vertical = 12.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "$tipo: $valor",
                                fontSize = 18.sp,
                                color = Color.Black,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Botão “Próximo”
                Button(
                    onClick = onNext,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Yellow, contentColor = Color.Red)
                ) {
                    Text("Próximo", fontSize = 22.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}



// ----------------------- Tela Nome e Idade -----------------------

@Composable
fun TelaNomeIdade(controller: PersonagemController, onNext: () -> Unit) {
    val personagem by controller.personagem

    // 🔥 Gradiente de fundo estilo fogo
    val fireBrush = Brush.verticalGradient(
        colors = listOf(Color(0xFF8B0000), Color.Red, Color(0xFFFFA500), Color.Yellow)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(fireBrush)
            .padding(24.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {

            OutlinedTextField(
                value = personagem.nome,
                onValueChange = { controller.atualizarNome(it) },
                label = {
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Text("Nome do Personagem", fontSize = 20.sp, color = Color.Black, textAlign = TextAlign.Center)
                    }
                },
                placeholder = {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Text("Digite o nome", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center, fontSize = 18.sp, color = Color(0x80000000))
                    }
                },
                textStyle = TextStyle(fontSize = 20.sp, textAlign = TextAlign.Center),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Yellow,
                    unfocusedBorderColor = Color.Red,
                    focusedLabelColor = Color.Yellow,
                    unfocusedLabelColor = Color.Black,
                    cursorColor = Color.Yellow
                ),
                singleLine = true
            )

            // Campo IDADE com label e placeholder centralizados
            OutlinedTextField(
                value = if (personagem.idade == 0) "" else personagem.idade.toString(),
                onValueChange = { controller.atualizarIdade(it) },
                label = {
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Text("Idade do Personagem", fontSize = 20.sp, color = Color.Black, textAlign = TextAlign.Center)
                    }
                },
                placeholder = {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Text("Ex.: 25", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center, fontSize = 18.sp, color = Color(0x80000000))
                    }
                },
                textStyle = TextStyle(fontSize = 20.sp, textAlign = TextAlign.Center),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Yellow,
                    unfocusedBorderColor = Color.Red,
                    focusedLabelColor = Color.Yellow,
                    unfocusedLabelColor = Color.Black,
                    cursorColor = Color.Yellow
                ),
                singleLine = true
            )

            // Mensagem dinâmica (centralizada)
            val mensagem = if ((personagem.nome.length + personagem.idade) > 99)
                "Seja bem vindo a este mundo... novamente"
            else
                "Seja bem vindo a este mundo"

            Text(
                text = mensagem,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 28.dp),
                fontSize = 22.sp,
                color = Color.Yellow,
                textAlign = TextAlign.Center
            )

            // Botão com texto centralizado
            Button(
                onClick = onNext,
                modifier = Modifier
                    .padding(top = 36.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red,
                    contentColor = Color.Yellow
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    "Próximo",
                    fontSize = 22.sp,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun TelaAtributos(controller: PersonagemController) {
    val personagem by controller.personagem

    val fireBrush = Brush.verticalGradient(
        colors = listOf(Color(0xFF8B0000), Color.Red, Color(0xFFFFA500), Color.Yellow)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(fireBrush)
            .padding(24.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "Resumo de Atributos",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Yellow,
                modifier = Modifier.padding(bottom = 32.dp),
                textAlign = TextAlign.Center
            )

            val atributos = personagem.atributos
            val atributosMap = mapOf(
                "Força" to atributos.forca,
                "Destreza" to atributos.destreza,
                "Constituição" to atributos.constituicao,
                "Inteligência" to atributos.inteligencia,
                "Sabedoria" to atributos.sabedoria,
                "Carisma" to atributos.carisma
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                atributosMap.forEach { (tipo, valor) ->
                    Text(
                        text = "$tipo: $valor (${Atributos.descreverAtributo(valor, tipo)})",
                        fontSize = 20.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                }
            }

            Button(
                onClick = { /* voltar ao menu principal ou avançar */ },
                modifier = Modifier
                    .padding(top = 32.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red, contentColor = Color.Yellow),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    "Continuar",
                    fontSize = 22.sp,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}



// ----------------------- Tela Raça -----------------------
@Composable
fun TelaRaca(controller: PersonagemController, onNext: () -> Unit) {
    // 🔥 Gradiente de fundo estilo fogo
    val fireBrush = Brush.verticalGradient(
        colors = listOf(Color(0xFF8B0000), Color.Red, Color(0xFFFFA500), Color.Yellow)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(fireBrush)
            .padding(24.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            // 🔥 Título
            Text(
                text = "Escolha sua Raça",
                fontSize = 28.sp,
                color = Color.Yellow,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            // 🔥 Botões de raça
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = { controller.atualizarRaca(Raca.HUMANO); onNext() },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red,
                        contentColor = Color.Yellow
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Humano", fontSize = 20.sp)
                }

                Button(
                    onClick = { controller.atualizarRaca(Raca.ELFO); onNext() },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red,
                        contentColor = Color.Yellow
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Elfo", fontSize = 20.sp)
                }

                Button(
                    onClick = { controller.atualizarRaca(Raca.ANAO); onNext() },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red,
                        contentColor = Color.Yellow
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Anão", fontSize = 20.sp)
                }
            }
        }
    }
}


// ----------------------- Tela Classe -----------------------
@Composable
fun TelaClasse(controller: PersonagemController, onNext: () -> Unit) {
    val fireBrush = Brush.verticalGradient(
        colors = listOf(Color(0xFF8B0000), Color.Red, Color(0xFFFFA500), Color.Yellow)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(fireBrush)
            .padding(24.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "Escolha sua Classe",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Yellow,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            // Botões de escolha de classe
            Column(
                verticalArrangement = Arrangement.spacedBy(20.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = { controller.atualizarClasse(Classe.GUERREIRO) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red, contentColor = Color.Yellow),
                    shape = RoundedCornerShape(12.dp)
                ) { Text("Guerreiro", fontSize = 20.sp, fontWeight = FontWeight.Bold) }

                Button(
                    onClick = { controller.atualizarClasse(Classe.LADRAO) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red, contentColor = Color.Yellow),
                    shape = RoundedCornerShape(12.dp)
                ) { Text("Ladrão", fontSize = 20.sp, fontWeight = FontWeight.Bold) }

                Button(
                    onClick = { controller.atualizarClasse(Classe.MAGO) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red, contentColor = Color.Yellow),
                    shape = RoundedCornerShape(12.dp)
                ) { Text("Mago", fontSize = 20.sp, fontWeight = FontWeight.Bold) }
            }

            // Botão “Próximo” para avançar no fluxo
            Button(
                onClick = onNext,
                modifier = Modifier
                    .padding(top = 32.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Yellow, contentColor = Color.Red),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    "Próximo",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun TelaResumo(controller: PersonagemController) {
    val personagem by controller.personagem

    val fireBrush = Brush.verticalGradient(
        colors = listOf(Color(0xFF8B0000), Color.Red, Color(0xFFFFA500), Color.Yellow)
    )

    // Mapa com informações do usuário, seguro para nulos
    val infoMap = mapOf(
        "Nome" to personagem.nome,
        "Idade" to personagem.idade.toString(),
        "Estilo" to when (personagem.estiloAventura) {
            1 -> "Clássico"
            2 -> "Aventureiro"
            3 -> "Heróico"
            else -> "Não selecionado"
        },
        "Raça" to (personagem.raca?.toString()?.lowercase()?.replaceFirstChar { it.uppercaseChar() } ?: "Não selecionada"),
        "Classe" to (personagem.classe?.toString()?.lowercase()?.replaceFirstChar { it.uppercaseChar() } ?: "Não selecionada")
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(fireBrush)
            .padding(24.dp)
    ) {
        // Scroll caso a tela seja pequena
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            // Box de saudação
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Yellow.copy(alpha = 0.8f), shape = RoundedCornerShape(12.dp))
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Caro ${personagem.nome}, aqui está o resumo da sua aventura!",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Boxes com informações do usuário
            infoMap.forEach { (chave, valor) ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White.copy(alpha = 0.7f), shape = RoundedCornerShape(8.dp))
                        .padding(vertical = 12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "$chave: $valor",
                        fontSize = 18.sp,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Botão final (pode ser para reiniciar ou sair)
            Button(
                onClick = { /* ação de reiniciar ou finalizar */ },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red, contentColor = Color.Yellow),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Finalizar",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

// ----------------------- Fluxo de Navegação 4 Etapas -----------------------
@Composable
fun PersonagemFlow(controller: PersonagemController) {
    var etapa by remember { mutableIntStateOf(1) }

    when (etapa) {
        1 -> TelaInicial { etapa = 2 }
        2 -> TelaNomeIdade(controller) { etapa = 3 }
        3 -> TelaEstilo(controller) { etapa = 4 }
        4 -> TelaRaca(controller) { etapa = 5 }
        5 -> TelaClasse(controller) { etapa = 6 }
        6 -> TelaResumo(controller)
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
fun PreviewTelaEstilo() {
    val controller = PersonagemController()
    TelaEstilo(controller = controller, onNext = {})
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
    TelaClasse(controller = controller, onNext = {})
}

@Preview(showBackground = true)
@Composable
fun PreviewTelaResumo() {
    val controller = PersonagemController();
    TelaResumo(controller)
}