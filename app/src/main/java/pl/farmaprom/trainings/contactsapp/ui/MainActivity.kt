package pl.farmaprom.trainings.contactsapp.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import pl.farmaprom.trainings.contactsapp.contacts.data.Contact
import pl.farmaprom.trainings.contactsapp.presentation.list.ContactsListView
import pl.farmaprom.trainings.contactsapp.presentation.list.ContactsViewState
import pl.farmaprom.trainings.contactsapp.presentation.preview.ContactPreviewScreen
import pl.farmaprom.trainings.contactsapp.ui.theme.ContactsAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ContactsAppTheme {
                val viewModel = viewModel<MainViewModel>()
                val contactsViewState by viewModel.contactViewState.collectAsStateWithLifecycle()

                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = ContactList
                ) {
                    composable<ContactList> {
                        Scaffold(
                            modifier = Modifier.fillMaxSize(),
                        ) { padding ->
                            ContactsView(
                                modifier = Modifier.padding(padding),
                                viewState = contactsViewState,
                                onContactClick = { contact ->
                                    viewModel.onContactSelected(contact)
                                    navController.navigate(
                                        ContactPreview(
                                            id = contact.id
                                        )
                                    )
                                }
                            )
                        }
                    }
                    composable<ContactPreview> { navEntry ->
                        val arg = navEntry.toRoute<ContactPreview>()
                        Log.d("KRTEST", arg.toString())
                        ContactPreviewScreen(
                            contact = contactsViewState.selectedContact!!,
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
data object ContactList

@Serializable
data class ContactPreview(val id: Long)

@Composable
fun ContactsView(
    modifier: Modifier,
    viewState: ContactsViewState = ContactsViewState(),
    onContactClick: (Contact) -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ContactsListView(
            contactsViewState = viewState,
            onContactClick = onContactClick
        )
    }
}

@Preview(
    showBackground = true,
    device = Devices.PIXEL_7_PRO
)
@Composable
fun DefaultPreview() {
    ContactsAppTheme {
        ContactsView(
            modifier = Modifier,
        )
    }
}

//
//@Preview(
//    showBackground = true,
//    device = Devices.NEXUS_5
//)
//@Composable
//fun DefaultPreviewNexus() {
//    ContactsAppTheme {
//        Greeting(
//            modifier = Modifier,
//            name = "Android"
//        )
//    }
//}
//
