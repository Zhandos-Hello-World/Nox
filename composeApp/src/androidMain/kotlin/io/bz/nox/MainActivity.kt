package io.bz.nox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import io.bz.nox.features.CommonScreenFlowsContainer
import io.bz.nox.theme.NoxTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            var isSplashScreen by remember { mutableStateOf(true) }
            installSplashScreen().setKeepOnScreenCondition { isSplashScreen }
            NoxTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CommonScreenFlowsContainer(
                        modifier = Modifier.padding(innerPadding),
                        isSplashScreen = { isSplashScreen = it },
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}