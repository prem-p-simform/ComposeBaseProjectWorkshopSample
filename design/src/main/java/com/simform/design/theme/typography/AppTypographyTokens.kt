package com.simform.design.theme.typography

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.simform.design.R

private val poppins = FontFamily(
    Font(resId = R.font.poppins_regular, weight = FontWeight.Normal), // Regular
    Font(resId = R.font.poppins_bold, weight = FontWeight.Bold),
    Font(resId = R.font.poppins_medium, weight = FontWeight.Medium),
    Font(resId = R.font.poppins_semi_bold, weight = FontWeight.SemiBold),
    Font(resId = R.font.poppins_extra_bold, weight = FontWeight.ExtraBold),
)

internal object AppTypographyTokens {

    // region h1

    val h1Normal = defaultTextStyle.copy(
        fontSize = 96.sp,
        fontWeight = FontWeight.Normal
    )

    val h1Bold = defaultTextStyle.copy(
        fontSize = 96.sp,
        fontWeight = FontWeight.Bold
    )

    val h1Medium = defaultTextStyle.copy(
        fontSize = 96.sp,
        fontWeight = FontWeight.Medium
    )

    val h1SemiBold = defaultTextStyle.copy(
        fontSize = 96.sp,
        fontWeight = FontWeight.SemiBold
    )

    val h1ExtraBold = defaultTextStyle.copy(
        fontSize = 96.sp,
        fontWeight = FontWeight.ExtraBold
    )

    // endregion

    // region h2

    val h2Normal = defaultTextStyle.copy(
        fontSize = 60.sp,
        fontWeight = FontWeight.Normal
    )

    val h2Bold = defaultTextStyle.copy(
        fontSize = 60.sp,
        fontWeight = FontWeight.Bold
    )

    val h2Medium = defaultTextStyle.copy(
        fontSize = 60.sp,
        fontWeight = FontWeight.Medium
    )

    val h2SemiBold = defaultTextStyle.copy(
        fontSize = 60.sp,
        fontWeight = FontWeight.SemiBold
    )

    val h2ExtraBold = defaultTextStyle.copy(
        fontSize = 60.sp,
        fontWeight = FontWeight.ExtraBold
    )

    // endregion

    // region h3

    val h3Normal = defaultTextStyle.copy(
        fontSize = 48.sp,
        fontWeight = FontWeight.Normal
    )

    val h3Bold = defaultTextStyle.copy(
        fontSize = 48.sp,
        fontWeight = FontWeight.Bold
    )

    val h3Medium = defaultTextStyle.copy(
        fontSize = 48.sp,
        fontWeight = FontWeight.Medium
    )

    val h3SemiBold = defaultTextStyle.copy(
        fontSize = 48.sp,
        fontWeight = FontWeight.SemiBold
    )

    val h3ExtraBold = defaultTextStyle.copy(
        fontSize = 48.sp,
        fontWeight = FontWeight.ExtraBold
    )

    // endregion

    // region h4

    val h4Normal = defaultTextStyle.copy(
        fontSize = 34.sp,
        fontWeight = FontWeight.Normal
    )

    val h4Bold = defaultTextStyle.copy(
        fontSize = 34.sp,
        fontWeight = FontWeight.Bold
    )

    val h4Medium = defaultTextStyle.copy(
        fontSize = 34.sp,
        fontWeight = FontWeight.Medium
    )

    val h4SemiBold = defaultTextStyle.copy(
        fontSize = 34.sp,
        fontWeight = FontWeight.SemiBold
    )

    val h4ExtraBold = defaultTextStyle.copy(
        fontSize = 34.sp,
        fontWeight = FontWeight.ExtraBold
    )

    // endregion

    // region h5

    val h5Normal = defaultTextStyle.copy(
        fontSize = 24.sp,
        fontWeight = FontWeight.Normal
    )

    val h5Bold = defaultTextStyle.copy(
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold
    )

    val h5Medium = defaultTextStyle.copy(
        fontSize = 24.sp,
        fontWeight = FontWeight.Medium
    )

    val h5SemiBold = defaultTextStyle.copy(
        fontSize = 24.sp,
        fontWeight = FontWeight.SemiBold
    )

    val h5ExtraBold = defaultTextStyle.copy(
        fontSize = 24.sp,
        fontWeight = FontWeight.ExtraBold
    )

    // endregion

    // region h6

    val h6Normal = defaultTextStyle.copy(
        fontSize = 20.sp,
        fontWeight = FontWeight.Normal
    )

    val h6Bold = defaultTextStyle.copy(
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold
    )

    val h6Medium = defaultTextStyle.copy(
        fontSize = 20.sp,
        fontWeight = FontWeight.Medium
    )

    val h6SemiBold = defaultTextStyle.copy(
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold
    )

    val h6ExtraBold = defaultTextStyle.copy(
        fontSize = 20.sp,
        fontWeight = FontWeight.ExtraBold
    )

    // endregion

    // region body1

    val body1Normal = defaultTextStyle.copy(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal
    )

    val body1Bold = defaultTextStyle.copy(
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold
    )

    val body1Medium = defaultTextStyle.copy(
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium
    )

    val body1SemiBold = defaultTextStyle.copy(
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold
    )

    val body1ExtraBold = defaultTextStyle.copy(
        fontSize = 16.sp,
        fontWeight = FontWeight.ExtraBold
    )

    // endregion

    // region body2

    val body2Normal = defaultTextStyle.copy(
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal
    )

    val body2Bold = defaultTextStyle.copy(
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold
    )

    val body2Medium = defaultTextStyle.copy(
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium
    )

    val body2SemiBold = defaultTextStyle.copy(
        fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold
    )

    val body2ExtraBold = defaultTextStyle.copy(
        fontSize = 14.sp,
        fontWeight = FontWeight.ExtraBold
    )

    // endregion
}

internal val defaultTextStyle = TextStyle.Default.copy(
    fontFamily = poppins,
    platformStyle = defaultPlatformTextStyle()
)

internal val LocalTextStyle =
    compositionLocalOf(structuralEqualityPolicy()) { defaultTextStyle }
