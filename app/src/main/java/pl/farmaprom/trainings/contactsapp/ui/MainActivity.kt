package pl.farmaprom.trainings.contactsapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import pl.farmaprom.trainings.contactsapp.contacts.presentation.list.ContactsScreen

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ContactsScreen(viewState = viewModel.viewState.value)
        }
    }
}
