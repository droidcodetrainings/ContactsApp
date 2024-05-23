package pl.farmaprom.trainings.contactsapp.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pl.farmaprom.trainings.contactsapp.list.presentation.ContactsList
import pl.farmaprom.trainings.contactsapp.list.presentation.ContactsListViewModel
import pl.farmaprom.trainings.contactsapp.list.presentation.ContactsListViewState
import pl.farmaprom.trainings.contactsapp.preview.presentation.ContactPreviewScreen
import pl.farmaprom.trainings.contactsapp.ui.theme.ContactsAppTheme

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {

    private val viewModel: ContactsListViewModel by viewModels()

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            toastMessage("permission granted after request") //navigate to phone call
        } else {
            toastMessage("permission denied")
        }
    }

    private fun callPhoneWithPermissionCheck() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CALL_PHONE
            ) == PackageManager.PERMISSION_GRANTED -> {
                toastMessage("permission granted before") //navigate to phone call
            }
            ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.CALL_PHONE
            ) -> {
                toastMessage("show rationale")
            }
            else -> {
                requestPermissionLauncher.launch(Manifest.permission.CALL_PHONE)
            }
        }
    }

    private fun toastMessage(message: String) {
        Toast.makeText(
            this, message, Toast.LENGTH_SHORT
        ).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewState = viewModel.viewState
            ContactsAppTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "list") {
                    composable(route = "list") {
                        ContactsList(
                            viewModel = viewModel,
                            viewState = viewState,
                            navController = navController
                        )
                    }
                    composable(route = "preview") {
                        viewState.selectedContact?.let {
                            ContactPreviewScreen(
                                contact = viewState.selectedContact,
                                onNavigateUp = {
                                    navController.navigateUp()
                                },
                                onContactClick = {
                                    //callPhoneWithPermissionCheck()
                                }
                            )
                        } ?: throw IllegalStateException("selected contact not filled")
                    }
                }
            }
        }
    }
}

@Composable
fun KotlinNavigation(
    viewModel: ContactsListViewModel,
    viewState: ContactsListViewState
) {
    if (viewState.selectedContact != null) {
        ContactPreviewScreen(
            contact = viewState.selectedContact,
            onNavigateUp = {
                viewModel.unselectContact()
            }
        )
    } else {
//        ContactsList(
//            viewModel = viewModel,
//            viewState = viewState
//        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactsList(
    viewModel: ContactsListViewModel,
    viewState: ContactsListViewState,
    navController: NavController
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "")
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) {
        ContactsList(
            viewState = viewState,
            paddingValues = it,
            onContactClick = { contact ->
                viewModel.selectContact(contact)
                navController.navigate(route = "preview")
            }
        )
    }
}
