package pl.farmaprom.trainings.contactsapp.contacts.add.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.OutputTransformation
import androidx.compose.foundation.text.input.TextFieldBuffer
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.TextObfuscationMode
import androidx.compose.foundation.text.input.insert
import androidx.compose.foundation.text.input.maxLength
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.text.input.then
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SecureTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import pl.farmaprom.trainings.contactsapp.ui.theme.ContactsAppTheme

@Composable
fun AddContactScreen(modifier: Modifier = Modifier) {
    Scaffold(modifier = modifier) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            val nameState = remember {
                mutableStateOf("")
            }
            val surnameState = remember {
                mutableStateOf("")
            }
            TextField(
                value = nameState.value,
                onValueChange = { newTextValue ->
                    nameState.value = newTextValue
                },
                label = {
                    Row {
                        Icon(Icons.Default.Person, contentDescription = null)
                        Text("Imię")
                    }
                }
            )
            Spacer(modifier = Modifier.size(8.dp))
            OutlinedTextField(
                value = surnameState.value,
                onValueChange = { newTextValue ->
                    surnameState.value = newTextValue
                },
                label = {
                    Row {
                        Icon(Icons.Default.Face, contentDescription = null)
                        Text("Nazwisko")
                    }
                }
            )
            Spacer(modifier = Modifier.size(8.dp))
            BasicTextField(
                value = surnameState.value,
                onValueChange = { newTextValue ->
                    surnameState.value = newTextValue
                },
                decorationBox = { innerTextField ->
                    Box(modifier = Modifier.size(32.dp).background(Color.LightGray))
                }
            )
            Spacer(modifier = Modifier.size(8.dp))
            val phoneNumberState = rememberTextFieldState()
            //val phoneNumberStateInVM = TextFieldState()
            OutlinedTextField(
                state = phoneNumberState,
                label = {
                    Row {
                        Text("Numer telefonu")
                    }
                },
                leadingIcon = {
                    Icon(Icons.Default.Phone, contentDescription = null)
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Phone
                ),
                inputTransformation =
                    MyMaxLengthInputTransformation(maxLength = 9).then { // TextFieldBuffer scope
                        if (!asCharSequence().isDigitsOnly()) {
                            revertAllChanges()
                        }
                    },
                outputTransformation = NumberFormatOutputTransformation()
            )

            val textObfuscationMode = remember {
                mutableStateOf(TextObfuscationMode.RevealLastTyped)
            }

            Spacer(modifier = Modifier.size(8.dp))
            SecureTextField(
                state = rememberTextFieldState(),
                textObfuscationMode = textObfuscationMode.value,
                trailingIcon = {
                    AnimatedContent(
                        targetState = textObfuscationMode.value
                    ) {
                        val onClickAction = {
                            when (it) {
                                TextObfuscationMode.RevealLastTyped,
                                TextObfuscationMode.Hidden -> {
                                    textObfuscationMode.value = TextObfuscationMode.Visible
                                }
                                TextObfuscationMode.Visible -> {
                                    textObfuscationMode.value = TextObfuscationMode.RevealLastTyped
                                }
                            }
                        }
                        when (it) {
                            TextObfuscationMode.RevealLastTyped,
                            TextObfuscationMode.Hidden -> {
                                Icon(
                                    modifier = Modifier.clickable {
                                        onClickAction()
                                    },
                                    imageVector = Icons.Default.Visibility,
                                    contentDescription = null
                                )
                            }
                            TextObfuscationMode.Visible -> {
                                Icon(
                                    modifier = Modifier.clickable {
                                        onClickAction()
                                    },
                                    imageVector = Icons.Default.VisibilityOff,
                                    contentDescription = null
                                )
                            }
                        }
                    }
                    Icon(
                        modifier = Modifier.clickable {
                            when (textObfuscationMode.value) {
                                TextObfuscationMode.RevealLastTyped,
                                TextObfuscationMode.Hidden -> {
                                    textObfuscationMode.value = TextObfuscationMode.Visible
                                }
                                TextObfuscationMode.Visible -> {
                                    textObfuscationMode.value = TextObfuscationMode.RevealLastTyped
                                }
                            }
                        },
                        imageVector = Icons.Default.Visibility,
                        contentDescription = null
                    )
                }
            )
        }
    }
}

class MyMaxLengthInputTransformation(
    private val maxLength: Int
) : InputTransformation {

    override fun TextFieldBuffer.transformInput() {
        if (length > maxLength) {
            revertAllChanges()
        }
    }
}

class NumberFormatOutputTransformation : OutputTransformation {

    override fun TextFieldBuffer.transformOutput() {
        if (length == 9) {
            insert(3, " ")
            insert(7, " ")
        }
    }
}

@Preview
@Composable
private fun AddContactScreenPreview() {
    ContactsAppTheme {
        AddContactScreen()
    }
}
