package com.multiplatform.clubdistances.ClubDetails.composables

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign

@Composable
fun OutlinedTextFieldForInputs(inputType: String, hint: String, suffixText: String, ) {
    var outlinedText by remember {
        mutableStateOf("")
    }
    OutlinedTextField(
        value = outlinedText,
        onValueChange = { outlinedText = it},
        // copy the local textStyle to keep the  other attributes
        textStyle = LocalTextStyle.current.copy(
            textAlign = TextAlign.Right
        ),
        // standard material label that minimises
        label = {
            Text(text = inputType)
        },
        //place holder and label can be used together
        placeholder = {
            Text(text = hint)
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Create,
                contentDescription = "face"
            )
        },
        suffix = {
            Text(text = " $suffixText")
        },
        // can be used for errors
        supportingText = {
            Text(text = "Please enter a realistic distance")
        },
        isError = false,
//                            visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Decimal,
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(
            onNext = {
                println("Hello World")
            }
        ),
        singleLine = true
    )
}