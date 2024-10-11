package com.simform.design.theme.typography

import androidx.compose.ui.text.PlatformTextStyle

private const val DEFAULT_INCLUDE_FONT_PADDING = false

private val defaultPlatformTextStyle = PlatformTextStyle(
    includeFontPadding = DEFAULT_INCLUDE_FONT_PADDING
)
internal fun defaultPlatformTextStyle(): PlatformTextStyle = defaultPlatformTextStyle
