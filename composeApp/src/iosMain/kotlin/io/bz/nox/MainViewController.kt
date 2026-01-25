package io.bz.nox

import androidx.compose.ui.window.ComposeUIViewController
import io.bz.nox.di.initModules

fun MainViewController() = ComposeUIViewController { App() }

fun InitModule() = initModules()