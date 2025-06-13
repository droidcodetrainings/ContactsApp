package pl.farmaprom.trainings.contactsapp.contacts.add.presentation.compose

import android.text.TextUtils
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.OutputTransformation
import androidx.compose.foundation.text.input.TextFieldBuffer
import androidx.compose.foundation.text.input.TextObfuscationMode
import androidx.compose.foundation.text.input.insert
import androidx.compose.foundation.text.input.maxLength
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.text.input.then
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SecureTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pl.farmaprom.trainings.contactsapp.ui.theme.ContactsAppTheme
import androidx.core.text.isDigitsOnly

@Composable
fun AddContactScreen(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            // TODO
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //better to store in ViewModel
            val fieldValue1 = remember {
                mutableStateOf("")
            }
            val fieldValue2 = remember {
                mutableStateOf("")
            }
            TextField(
                value = fieldValue1.value,
                onValueChange = { newTextValue ->
                    fieldValue1.value = newTextValue
                },
                label = {
                    Row {
                        Icon(Icons.Default.Person, contentDescription = null)
                        Text("Imię")
                    }
                }
            )
            Spacer(Modifier.size(8.dp))
            OutlinedTextField(
                value = fieldValue2.value,
                onValueChange = { newTextValue ->
                    fieldValue2.value = newTextValue
                },
                label = {
                    Row {
                        Icon(Icons.Default.Person, contentDescription = null)
                        Text("Nazwisko")
                    }
                }
            )
            Spacer(Modifier.size(8.dp))


            // in ViewModel
            // val phoneNumberState = TextFieldState()

            val phoneNumberState = rememberTextFieldState()
            OutlinedTextField(
                state = phoneNumberState,
                leadingIcon = {
                    Icon(Icons.Default.Call, contentDescription = null)
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Phone
                ),
                label = {
                    Row {
                        Text("Numer telefonu")
                    }
                },
                inputTransformation = InputTransformation.maxLength(9).then(DigitOnlyInputTransformation()).then(
                    MyInputTransformation()
                ),
                outputTransformation = PhoneNumberFormatTransformation()
            )
            Spacer(Modifier.size(8.dp))
            SecureTextField(
                state = rememberTextFieldState(),
                textObfuscationMode = TextObfuscationMode.Hidden,
            )
            Spacer(Modifier.size(8.dp))
            BasicTextField(
                value = fieldValue1.value,
                onValueChange = { newTextValue ->
                    fieldValue1.value = newTextValue
                },
                decorationBox = {
                    Column(Modifier.size(60.dp).background(Color.Gray)) {
                        it.invoke()
                    }
                }
            )
        }
    }
}

class PhoneNumberFormatTransformation : OutputTransformation {

    override fun TextFieldBuffer.transformOutput() {
        if (length == 9) {
            insert(3, " ")
            insert(7, " ")
        }
    }
}

class MyInputTransformation : InputTransformation {

    override fun TextFieldBuffer.transformInput() {
        // do some transformation/validation
    }
}

class DigitOnlyInputTransformation : InputTransformation {
    override fun TextFieldBuffer.transformInput() {
        if (!asCharSequence().isDigitsOnly()) {
            revertAllChanges()
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
