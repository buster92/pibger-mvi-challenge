package pinger.challenge

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import pinger.challenge.theme.MviSampleTheme

@Composable
fun PingerApp() {
    val viewModel: MainViewModel = viewModel()
    MviSampleTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            MainScreen(viewModel = viewModel)
        }
    }
}

