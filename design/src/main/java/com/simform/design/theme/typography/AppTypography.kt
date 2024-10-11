package com.simform.design.theme.typography

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import com.simform.design.theme.AppTheme

/**
 * A typography holds all the named typography parameters for a [AppTheme].
 *
 * @property h1Normal The h1 text style for normal text.
 * @property h1Bold The h1 text style for bold text.
 * @property h1Medium The h1 text style for medium text.
 * @property h1SemiBold The h1 text style for semi-bold text.
 * @property h1ExtraBold The h1 text style for extra-bold text.
 * @property h2Normal The h2 text style for normal text.
 * @property h2Bold The h2 text style for bold text.
 * @property h2Medium The h2 text style for medium text.
 * @property h2SemiBold The h2 text style for semi-bold text.
 * @property h2ExtraBold The h2 text style for extra-bold text.
 * @property h3Normal The h3 text style for normal text.
 * @property h3Bold The h3 text style for bold text.
 * @property h3Medium The h3 text style for medium text.
 * @property h3SemiBold The h3 text style for semi-bold text.
 * @property h3ExtraBold The h3 text style for extra-bold text.
 * @property h4Normal The h4 text style for normal text.
 * @property h4Bold The h4 text style for bold text.
 * @property h4Medium The h4 text style for medium text.
 * @property h4SemiBold The h4 text style for semi-bold text.
 * @property h4ExtraBold The h4 text style for extra-bold text.
 * @property h5Normal The h5 text style for normal text.
 * @property h5Bold The h5 text style for bold text.
 * @property h5Medium The h5 text style for medium text.
 * @property h5SemiBold The h5 text style for semi-bold text.
 * @property h5ExtraBold The h5 text style for extra-bold text.
 * @property h6Normal The h6 text style for normal text.
 * @property h6Bold The h6 text style for bold text.
 * @property h6Medium The h6 text style for medium text.
 * @property h6SemiBold The h6 text style for semi-bold text.
 * @property h6ExtraBold The h6 text style for extra-bold text.
 * @property body1Normal The body1 text style for normal text.
 * @property body1Bold The body1 text style for bold text.
 * @property body1Medium The body1 text style for medium text.
 * @property body1SemiBold The body1 text style for semi-bold text.
 * @property body1ExtraBold The body1 text style for extra-bold text.
 * @property body2Normal The body2 text style for normal text.
 * @property body2Bold The body2 text style for bold text.
 * @property body2Medium The body2 text style for medium text.
 * @property body2SemiBold The body2 text style for semi-bold text.
 * @property body2ExtraBold The body2 text style for extra-bold text.
 */
data class AppTypography(
    val h1Normal: TextStyle = AppTypographyTokens.h1Normal,
    val h1Bold: TextStyle = AppTypographyTokens.h1Bold,
    val h1Medium: TextStyle = AppTypographyTokens.h1Medium,
    val h1SemiBold: TextStyle = AppTypographyTokens.h1SemiBold,
    val h1ExtraBold: TextStyle = AppTypographyTokens.h1ExtraBold,
    val h2Normal: TextStyle = AppTypographyTokens.h2Normal,
    val h2Bold: TextStyle = AppTypographyTokens.h2Bold,
    val h2Medium: TextStyle = AppTypographyTokens.h2Medium,
    val h2SemiBold: TextStyle = AppTypographyTokens.h2SemiBold,
    val h2ExtraBold: TextStyle = AppTypographyTokens.h2ExtraBold,
    val h3Normal: TextStyle = AppTypographyTokens.h3Normal,
    val h3Bold: TextStyle = AppTypographyTokens.h3Bold,
    val h3Medium: TextStyle = AppTypographyTokens.h3Medium,
    val h3SemiBold: TextStyle = AppTypographyTokens.h3SemiBold,
    val h3ExtraBold: TextStyle = AppTypographyTokens.h3ExtraBold,
    val h4Normal: TextStyle = AppTypographyTokens.h4Normal,
    val h4Bold: TextStyle = AppTypographyTokens.h4Bold,
    val h4Medium: TextStyle = AppTypographyTokens.h4Medium,
    val h4SemiBold: TextStyle = AppTypographyTokens.h4SemiBold,
    val h4ExtraBold: TextStyle = AppTypographyTokens.h4ExtraBold,
    val h5Normal: TextStyle = AppTypographyTokens.h5Normal,
    val h5Bold: TextStyle = AppTypographyTokens.h5Bold,
    val h5Medium: TextStyle = AppTypographyTokens.h5Medium,
    val h5SemiBold: TextStyle = AppTypographyTokens.h5SemiBold,
    val h5ExtraBold: TextStyle = AppTypographyTokens.h5ExtraBold,
    val h6Normal: TextStyle = AppTypographyTokens.h6Normal,
    val h6Bold: TextStyle = AppTypographyTokens.h6Bold,
    val h6Medium: TextStyle = AppTypographyTokens.h6Medium,
    val h6SemiBold: TextStyle = AppTypographyTokens.h6SemiBold,
    val h6ExtraBold: TextStyle = AppTypographyTokens.h6ExtraBold,
    val body1Normal: TextStyle = AppTypographyTokens.body1Normal,
    val body1Bold: TextStyle = AppTypographyTokens.body1Bold,
    val body1Medium: TextStyle = AppTypographyTokens.body1Medium,
    val body1SemiBold: TextStyle = AppTypographyTokens.body1SemiBold,
    val body1ExtraBold: TextStyle = AppTypographyTokens.body1ExtraBold,
    val body2Normal: TextStyle = AppTypographyTokens.body2Normal,
    val body2Bold: TextStyle = AppTypographyTokens.body2Bold,
    val body2Medium: TextStyle = AppTypographyTokens.body2Medium,
    val body2SemiBold: TextStyle = AppTypographyTokens.body2SemiBold,
    val body2ExtraBold: TextStyle = AppTypographyTokens.body2ExtraBold
)

/**
 * CompositionLocal used to pass [AppTypography] down the tree.
 *
 * Setting the value here is typically done as part of [AppTheme].
 * To retrieve the current value of this CompositionLocal, use
 * [AppTheme.appTypography].
 */
internal val LocalAppTypography = staticCompositionLocalOf { AppTypography() }

