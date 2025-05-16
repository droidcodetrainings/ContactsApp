package pl.farmaprom.trainings.contactsapp.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import pl.farmaprom.trainings.contactsapp.R
import pl.farmaprom.trainings.contactsapp.contacts.data.Contact
import pl.farmaprom.trainings.contactsapp.presentation.list.ContactItem
import pl.farmaprom.trainings.contactsapp.presentation.list.ContactsViewState
import pl.farmaprom.trainings.contactsapp.presentation.list.HeaderItem
import pl.farmaprom.trainings.contactsapp.presentation.list.MainViewModel
import pl.farmaprom.trainings.contactsapp.presentation.preview.ContactPreviewScreen
import pl.farmaprom.trainings.contactsapp.ui.theme.ContactsAppTheme
import pl.farmaprom.trainings.contactsapp.utils.generateContacts

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
                }
            }
        }
    }
}

@Serializable
data object ContactsList

@Serializable
data class ContactPreview(
    val id: Long
)

@Composable
fun ContactsView(
    modifier: Modifier = Modifier,
    contactsViewState: ContactsViewState,
    onContactClick: (Contact) -> Unit = { }
) {
    Scaffold(
        topBar = { },
        modifier = Modifier.fillMaxSize(),
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

@Composable
fun ContactsListView(
    modifier: Modifier = Modifier,
    contactsViewState: ContactsViewState,
    onContactClick: (Contact) -> Unit = {}
) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        item {
            HeaderItem(text = stringResource(R.string.header_contacts_list_text))
        }
        items(contactsViewState.contacts){
            ContactItem(
                profileImageUrl = it.profileImageUrl,
                name = it.name,
                isFavourite = it.isFavourite,
                onClick = {
                    onContactClick(it)
                }
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
