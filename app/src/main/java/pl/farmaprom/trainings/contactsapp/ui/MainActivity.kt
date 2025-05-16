package pl.farmaprom.trainings.contactsapp.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import pl.farmaprom.trainings.contactsapp.contacts.data.Contact
import pl.farmaprom.trainings.contactsapp.contacts.list.presentation.ContactsListView
import pl.farmaprom.trainings.contactsapp.contacts.list.presentation.ContactsViewState
import pl.farmaprom.trainings.contactsapp.contacts.preview.ContactPreviewScreen
import pl.farmaprom.trainings.contactsapp.contacts.utils.generateContacts
import pl.farmaprom.trainings.contactsapp.ui.theme.ContactsAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ContactsAppTheme {
                val viewModel = viewModel<MainViewModel>()
                val contactsViewState by viewModel.contactsViewState.collectAsStateWithLifecycle()
                val selectedContact = contactsViewState.selectedContact
//                BackHandler(enabled = selectedContact != null) {
//                    viewModel.onContactSelected(null)
//                }
//                if (selectedContact != null) { // contactsViewState.selectedContact?.let { } ?: { }
//                    ContactPreviewScreen(
//                        contact = selectedContact,
//                        onBackClick = {
//                            viewModel.onContactSelected(null)
//                        }
//                    )
//                } else {
//                    ContactsView(
//                        contactsViewState = contactsViewState
//                    )
//                }

                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = List
                ) {
                    composable<List> {
                        ContactsView(
                            contactsViewState = contactsViewState,
                            onContactClick = { contact ->
                                viewModel.onContactSelected(contact)
                                navController.navigate(ContactPreview(id = contact.id))
                            }
                        )
                    }
                    composable<ContactPreview> {
                        val resolved = it.toRoute<ContactPreview>()
                        Log.d("KRTEST", "resolved args: $resolved")
                        ContactPreviewScreen(
                            contact = selectedContact!!, // TODO pass id of contact
                            onBackClick = {
                                viewModel.onContactSelected(null)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Serializable
data object List

@Serializable
data class ContactPreview(val id: Long)

@Composable
fun ContactsView(
    modifier: Modifier = Modifier,
    contactsViewState: ContactsViewState,
    onContactClick: (Contact) -> Unit = {}
) {
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
                onContactClick = onContactClick
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
