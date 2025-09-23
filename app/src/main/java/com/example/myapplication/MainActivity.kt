// src/main/java/com.example.myapplication/MainActivity.kt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.myapplication.controller.PersonagemController
import com.example.myapplication.ui.theme.MyApplicationTheme // Altere para o nome do seu tema
import com.example.myapplication.view.PersonagemScreen
import com.example.myapplication.view.TelaInicial

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val controller = PersonagemController()

        setContent {
            MyApplicationTheme {
                // Estado para controlar qual tela está sendo exibida
                var showStartScreen by remember { mutableStateOf(true) }

                if (showStartScreen) {
                    // Exibe a tela inicial
                    TelaInicial(onStartJourney = {
                        // Ao clicar no botão, muda o estado para false
                        showStartScreen = false
                    })
                } else {
                    // Exibe a tela de criação de personagem
                    PersonagemScreen(controller = controller)
                }
            }
        }
    }
}