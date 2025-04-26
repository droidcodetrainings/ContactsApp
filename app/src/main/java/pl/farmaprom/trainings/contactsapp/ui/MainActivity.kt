package pl.farmaprom.trainings.contactsapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import pl.farmaprom.trainings.contactsapp.contacts.list.presentation.ContactsListView
import pl.farmaprom.trainings.contactsapp.contacts.list.presentation.ContactsViewState
import pl.farmaprom.trainings.contactsapp.contacts.utils.generateContacts
import pl.farmaprom.trainings.contactsapp.ui.theme.ContactsAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ContactsAppTheme {
                val viewModel = viewModel<MainViewModel>()
                val contactsViewState by viewModel.contactsViewState.collectAsStateWithLifecycle()
                // A surface container using the 'background' color from the theme
                ContactsView(
                    contactsViewState = contactsViewState
                )
            }
        }
    }
}

@Composable
fun ContactsView(
    modifier: Modifier = Modifier,
    contactsViewState: ContactsViewState
) {
    val viewModel = viewModel<MainViewModel>()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {}
    ) { padding ->
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            ContactsListView(
                modifier = modifier.padding(padding),
                contactsViewState = contactsViewState,
                onContactClick = { contact ->
                    viewModel.onContactSelected(contact)
                }
            )
        }
    }
}

@Preview(
    showBackground = true,
    device = Devices.PIXEL_7_PRO
)
@Composable
fun DefaultPreview() {
    ContactsAppTheme {
        val contactsViewState by remember {
            mutableStateOf(
                ContactsViewState(
                    contacts = generateContacts(
                        100
                    )
                )
            )
        }
        ContactsView(modifier = Modifier, contactsViewState = contactsViewState)
    }
}
