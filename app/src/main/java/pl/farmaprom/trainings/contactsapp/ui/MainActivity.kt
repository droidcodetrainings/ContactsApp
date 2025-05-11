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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import pl.farmaprom.trainings.contactsapp.presentation.list.ContactsViewList
import pl.farmaprom.trainings.contactsapp.presentation.list.ContactsViewState
import pl.farmaprom.trainings.contactsapp.presentation.list.MainViewModel
import pl.farmaprom.trainings.contactsapp.ui.theme.ContactsAppTheme
import pl.farmaprom.trainings.contactsapp.utils.generateContacts

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ContactsAppTheme {
                val viewModel = viewModel<MainViewModel>()
                val contactsViewState by viewModel.contactsViewState.collectAsStateWithLifecycle()
                // A surface container using the 'background' color from the theme
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { padding ->
                    ContactsView(modifier = Modifier.padding(padding), contactsViewState = contactsViewState)
                }
            }
        }
    }
}

@Composable
fun ContactsView(
    modifier: Modifier,
    contactsViewState: ContactsViewState
) {
    Column(
        modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val viewModel = viewModel<MainViewModel>()
        ContactsViewList(viewState = contactsViewState,
            onContactClick = { contact ->
                viewModel.onContactSelected(contact)
            })
    }
}

//@Preview(showBackground = true, device = Devices.NEXUS_5)
//@Composable
//fun DefaultPreview() {
//    ContactsAppTheme {
//        Greeting(modifier = Modifier, "Android")
//    }
//}

@Preview(showBackground = true, device = Devices.PIXEL_7_PRO)
@Composable
fun DefaultPreviewPixel7XL() {
    ContactsAppTheme {
        val viewState by remember { mutableStateOf(ContactsViewState(contacts = generateContacts(1000))) }
        ContactsView(modifier = Modifier, ContactsViewState())
    }
}
