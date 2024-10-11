package com.simform.app.ui.onboarding.login

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.simform.app.R
import com.simform.app.ui.components.ScreenTitle
import com.simform.design.button.AppButton
import com.simform.design.text.AppText
import com.simform.design.textfield.AppOutlinedTextField
import com.simform.design.theme.AppPreviewTheme
import com.simform.design.theme.AppTheme

/**
 * The login route to show login screen.
 *
 * @param modifier The [Modifier]
 * @param viewModel The [LoginViewModel]
 */
@Composable
fun LoginRoute(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LoginScreen(
        modifier = modifier,
        uiState = uiState,
        onPhoneNumberChange = viewModel::onPhoneNumberChange,
        onContinueClick = viewModel::onContinueClick
    )
}

/**
 * Standalone screen to show login screen.
 *
 * @param modifier The [Modifier]
 * @param uiState The [LoginUiState]
 * @param onPhoneNumberChange The callback to handle phone number change
 * @param onContinueClick The callback to handle continue click
 */
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    uiState: LoginUiState,
    onPhoneNumberChange: (String) -> Unit,
    onContinueClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .padding(dimensionResource(R.dimen.appSidePadding)),
        verticalArrangement = Arrangement.spacedBy(
            space = dimensionResource(R.dimen.default_vertical_padding),
            alignment = Alignment.Top
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.padding(dimensionResource(R.dimen.screen_title_spacing)))
        ScreenTitle(title = stringResource(R.string.title_login))
        Column(
            modifier = Modifier
                .weight(1F),
            verticalArrangement = Arrangement.spacedBy(
                space = dimensionResource(R.dimen.default_vertical_padding),
                alignment = Alignment.CenterVertically
            )
        ) {
            AppOutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = uiState.phoneNumber,
                placeholder = {
                    AppText(text = stringResource(R.string.placeholder_enter_phonenumber))
                },
                supportingText = {
                    val message = if (uiState.phoneNumberError > 0) {
                        stringResource(uiState.phoneNumberError)
                    } else {
                        ""
                    }
                    AppText(text = message, textColor = AppTheme.appColorScheme.error)
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone
                ),
                onValueChange = onPhoneNumberChange
            )
            AppButton(
                modifier = Modifier
                    .fillMaxWidth(),
                contentPadding = PaddingValues(vertical = dimensionResource(R.dimen.default_btn_padding)),
                enabled = uiState.isValidInput,
                onClick = onContinueClick
            ) {
                AppText(
                    text = stringResource(R.string.title_continue),
                    textColor = AppTheme.appColorScheme.white
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun LoginScreenPreview() {
    AppPreviewTheme {
        LoginScreen(
            modifier = Modifier
                .fillMaxSize(),
            uiState = LoginUiState(),
            onPhoneNumberChange = {},
            onContinueClick = {}
        )
    }
}
