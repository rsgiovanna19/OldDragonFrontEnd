package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.myapplication.controller.PersonagemController
import com.example.myapplication.ui.theme.MyApplicationTheme // Importe seu tema
import com.example.myapplication.view.PersonagemScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Instancia o Controller. Ele contém a lógica do aplicativo
        val controller = PersonagemController()

        setContent {
            // Aplica o tema padrão do seu projeto (cores, tipografia, etc.)
            MyApplicationTheme {
                // Chama a sua tela principal e passa o Controller para ela
                PersonagemScreen(controller = controller)
            }
        }
    }
}