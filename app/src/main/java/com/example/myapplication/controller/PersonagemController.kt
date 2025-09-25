package com.example.myapplication.controller

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.myapplication.model.*
import kotlin.random.Random

class PersonagemController {
    private val _personagem = mutableStateOf(Personagem(nome = "Novo Herói", idade = 0))
    val personagem: State<Personagem> get() = _personagem

    var estiloAventura by mutableStateOf(1) // 1 = Clássico, 2 = Aventureiro, 3 = Heróico

    fun gerarAtributos() {
        val novosAtributos = when (estiloAventura) {
            1, 2 -> Atributos(
                forca = rolar3d6(),
                destreza = rolar3d6(),
                constituicao = rolar3d6(),
                inteligencia = rolar3d6(),
                sabedoria = rolar3d6(),
                carisma = rolar3d6()
            )
            3 -> Atributos(
                forca = rolar4d6DropLowest(),
                destreza = rolar4d6DropLowest(),
                constituicao = rolar4d6DropLowest(),
                inteligencia = rolar4d6DropLowest(),
                sabedoria = rolar4d6DropLowest(),
                carisma = rolar4d6DropLowest()
            )
            else -> Atributos()
        }
        _personagem.value = _personagem.value.copy(atributos = novosAtributos)
    }

    fun atualizarNome(novoNome: String) {
        _personagem.value = _personagem.value.copy(nome = novoNome)
    }

    fun atualizarIdade(novaIdade: String) {
        _personagem.value = _personagem.value.copy(idade = novaIdade.toIntOrNull() ?: 0)
    }

    fun atualizarRaca(novaRaca: Raca) {
        _personagem.value = _personagem.value.copy(raca = novaRaca)
    }

    fun atualizarClasse(novaClasse: Classe) {
        _personagem.value = _personagem.value.copy(classe = novaClasse)
    }

    private fun rolarD6() = Random.nextInt(1, 7)
    private fun rolar3d6() = rolarD6() + rolarD6() + rolarD6()
    private fun rolar4d6DropLowest(): Int {
        val dados = List(4) { rolarD6() }
        return dados.sortedDescending().take(3).sum()
    }
}
