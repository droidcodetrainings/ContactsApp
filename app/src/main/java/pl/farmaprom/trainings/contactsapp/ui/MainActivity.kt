package pl.farmaprom.trainings.contactsapp.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import pl.farmaprom.trainings.contactsapp.contacts.SearchBoxTopAppBar
import pl.farmaprom.trainings.contactsapp.contacts.add.presentation.AddContactScreen
import pl.farmaprom.trainings.contactsapp.contacts.data.Contact
import pl.farmaprom.trainings.contactsapp.contacts.presentation.list.MainViewModel
import pl.farmaprom.trainings.contactsapp.contacts.presentation.list.ContactsListView
import pl.farmaprom.trainings.contactsapp.contacts.presentation.list.ContactsViewState
import pl.farmaprom.trainings.contactsapp.contacts.preview.ContactPreviewScreen
import pl.farmaprom.trainings.contactsapp.contacts.utils.generateContacts
import pl.farmaprom.trainings.contactsapp.ui.theme.ContactsAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ContactsAppTheme {
                val viewModel: MainViewModel = viewModel<MainViewModel>()
                val contactsViewState by viewModel.contactsViewState.collectAsStateWithLifecycle()
                // A surface container using the 'background' color from the theme
                val selectedContact = contactsViewState.selectedContact
//                BackHandler(enabled = selectedContact != null) {
//                    viewModel.onContactSelected(null)
//                }
//                if (selectedContact != null) {
//                    ContactPreviewScreen(
//                        contact = selectedContact,
//                        onBackClick = {
//                            viewModel.onContactSelected(null)
//                        }
//                    )
//                } else {
//                    ContactsView(contactsViewState = contactsViewState)
//                }

                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = ContactsList
                ) {
                    composable<ContactsList> {
                        ContactsView(
                            contactsViewState = contactsViewState,
                            onContactClick = { contact ->
                                viewModel.onContactSelected(contact)
                                navController.navigate(ContactPreview(id = contact.id))
                            },
                            onAddContactClick = {
                                navController.navigate(AddContact)
                            }
                        )
                    }
                    composable<ContactPreview> { navEntry ->
                        val args = navEntry.toRoute<ContactPreview>()
                        Log.d("KRTEST", "args: $args")
                        ContactPreviewScreen(
                            contact = selectedContact!!, // TODO should be based on arg from list
                            onBackClick = {
                                navController.navigateUp()
                            }
                        )
                    }
                    composable<AddContact> {
                        AddContactScreen()
                    }
                }
            }
        }
    }
}

@Serializable
data object ContactsList

@Serializable
data object AddContact

@Serializable
data class ContactPreview(
    val id: Long
)

@Composable
fun ContactsView(
    modifier: Modifier = Modifier,
    contactsViewState: ContactsViewState,
    onContactClick: (Contact) -> Unit = { },
    onAddContactClick: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            SearchBoxTopAppBar()
        },
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            Button(
                onClick = onAddContactClick
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.Add, contentDescription = null)
                    Text("Dodaj")
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ContactsListView(
                modifier = modifier.padding(padding),
                contactsViewState = contactsViewState,
                onContactClick = onContactClick
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ContactsAppTheme {
        val contactViewState by remember {
            mutableStateOf(
                ContactsViewState(contacts = generateContacts(20))
            )
        }
        ContactsView(contactsViewState = contactViewState)
    }
}
