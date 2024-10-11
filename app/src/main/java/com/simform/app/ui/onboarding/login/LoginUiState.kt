package com.simform.app.ui.onboarding.login

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.core.text.isDigitsOnly
import com.simform.app.R

/**
 * The UI state for [LoginScreen]
 *
 * @param phoneNumber The phone number of the user to login
 */
@Stable
@Immutable
data class LoginUiState(
    val phoneNumber: String = "",
) {
    @get:StringRes
    val phoneNumberError: Int
        get() = if (phoneNumber.isNotEmpty() && !isValidPhoneNumber()) {
            R.string.error_phone_number
        } else {
            0
        }

    val isValidInput: Boolean
        get() = isValidPhoneNumber()

    private fun isValidPhoneNumber(): Boolean = phoneNumber.isDigitsOnly() &&
        phoneNumber.count() in 10..13
}
