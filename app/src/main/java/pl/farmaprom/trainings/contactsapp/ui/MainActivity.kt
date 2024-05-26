package pl.farmaprom.trainings.contactsapp.ui

import android.Manifest
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import pl.farmaprom.trainings.contactsapp.ContactListViewState
import pl.farmaprom.trainings.contactsapp.MainViewModel
import pl.farmaprom.trainings.contactsapp.R
import pl.farmaprom.trainings.contactsapp.contacts.data.Contact
import pl.farmaprom.trainings.contactsapp.ui.theme.ContactsAppTheme
import java.lang.IllegalStateException

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewState = viewModel.viewState
            ContactsAppTheme {

                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "list"
                ) {
                    composable(route = "list") {
                        ContactsListScreen(
                            viewState = viewState,
                            onContactClick = {
                                viewModel.selectContact(it)
                                navController.navigate(
                                    route = "preview"
                                )
                            }
                        )
                    }

                    composable(route = "preview") {
                        viewState.selectedContact?.let {
                            ContactPreviewScreen(
                                contact = viewState.selectedContact,
                                onNavigateUp = {
                                    navController.navigateUp()
                                },
                                onNextClick = {
                                    navController.navigate(route = "next")
                                }
                            )
                        } ?: throw IllegalStateException("no selected contact filled")
                    }

                    composable(route = "next") {
                        NextScreen(
                            onNavigateUp = {
                                navController.navigateUp()
                            }
                        )
                    }
                }

//                if (viewState.selectedContact != null) {
//                    ContactPreviewScreen(
//                        contact = viewState.selectedContact,
//                        onNavigateUp = {
//                            viewModel.unselectContact()
//                        }
//                    )
//                } else {
//                    ContactsListScreen(
//                        viewState = viewState,
//                        onContactClick = {
//                            viewModel.selectContact(it)
//                        }
//                    )
//                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactsListScreen(
    viewState: ContactListViewState,
    onContactClick: (Contact) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            MediumTopAppBar(
                title = {},
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
                HeaderItem(text = "Kontakty")
            }

            items(viewState.contactsList) {
                ContactItem(
                    imageUrl = it.profileImageUrl,
                    name = "${it.name} ${it.surname}",
                    isFavourite = it.isFavourite,
                    onClick = {
                        onContactClick(it)
                    }
                )
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ContactsAppTheme {
        Greeting("Android sssshshshsh")
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ContactPreviewScreen(
    contact: Contact,
    onNavigateUp: () -> Unit,
    onNextClick: () -> Unit
) {
    val permissionState = rememberPermissionState(
        permission = Manifest.permission.CALL_PHONE
    )

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            if (isGranted) {
                Log.i("TAG", "just granted")
            } else {
                Log.i("TAG", "denied clicked")
            }
        }
    )

    LaunchedEffect(permissionState) {
        when {
            permissionState.status.isGranted.not() && permissionState.status.shouldShowRationale -> {
                Log.i("TAG", "show rationale")
            }
            permissionState.status.isGranted -> Log.i("TAG", "granted before")
            else -> permissionLauncher.launch(Manifest.permission.CALL_PHONE)
        }
    }

    Column {
        Text(
            modifier = Modifier.clickable {
                onNavigateUp()
            },
            text = contact.name
        )
        Button(onClick = { onNextClick() }) {
            Text(text = "next")
        }
    }
}

@Composable
fun NextScreen(
    onNavigateUp: () -> Unit
) {
    IconButton(onClick = { onNavigateUp() }) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = null
        )
    }
}
