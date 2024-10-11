package com.simform.design.theme.gradients

import androidx.compose.ui.graphics.Color

object AppGradientTokens {
    val primaryGradient = AppGradientToken(
        light = listOf(Color(0xFFE6E6E6), Color(0x88ECE7F6)),
        dark = listOf(Color(0xFF1C1C1C), Color(0x994F4D4D))
    )
    val secondaryGradient = AppGradientToken(
        light = listOf(Color(0xFF393863), Color(0xFFA290FF), Color(0xFF5833B0)),
        dark = listOf(Color(0xFF393863), Color(0xFFA290FF), Color(0xFF5833B0))
    )
}