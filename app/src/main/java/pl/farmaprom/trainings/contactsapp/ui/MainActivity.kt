package pl.farmaprom.trainings.contactsapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pl.farmaprom.trainings.contactsapp.ContactsListViewState
import pl.farmaprom.trainings.contactsapp.MainViewViewModel
import pl.farmaprom.trainings.contactsapp.contacts.data.Contact
import pl.farmaprom.trainings.contactsapp.ui.theme.ContactsAppTheme

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewState = viewModel.viewState
            ContactsAppTheme {

                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "list") {
                    composable(route = "list") {
                        ContactsListScreen(
                            viewState = viewState,
                            viewModel = viewModel,
                            onContactClick = { contact ->
                                // viewModel.selectContact(contact) // TODO remove comment after remove call from inner fun
                                navController.navigate("preview")
                            }
                        )
                    }
                    composable(route = "preview") {
                        viewState.selectedContact?.let {
                            ContactPreviewScreen(
                                contact = it,
                                navigateUp = {
                                    viewModel.unSelectContact()
                                }
                            )
                        } ?: throw IllegalStateException("selected contact not filled")
                    }
                }

//                if (viewState.selectedContact != null) {
//                    ContactPreviewScreen(
//                        contact = viewState.selectedContact,
//                        navigateUp = {
//                            viewModel.unSelectContact()
//                        }
//                    )
//                } else {
//                    ContactsListScreen(viewState = viewState, viewModel = viewModel)
//                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactsListScreen(
    viewState: ContactsListViewState,
    viewModel: MainViewViewModel,
    onContactClick: (Contact) -> Unit = {}
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            MediumTopAppBar(
                title = { },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier.padding(it)
        ) {
            item {
                HeaderItem(text = "Test")
            }

            items(viewState.contacts) { contact ->
                ContactItem(
                    imageUrl = contact.profileImageUrl,
                    name = contact.name,
                    isFavourite = contact.isFavourite,
                    onClick = {
                        viewModel.selectContact(contact) // TODO move to outer fun
                        onContactClick(contact)
                    }
                )
            }
        }
    }
}

@Composable
fun ContactPreviewScreen(
    contact: Contact,
    navigateUp: () -> Unit
) {
    Text(
        modifier = Modifier.clickable {
            navigateUp()
        },
        text = contact.name
    )
}
