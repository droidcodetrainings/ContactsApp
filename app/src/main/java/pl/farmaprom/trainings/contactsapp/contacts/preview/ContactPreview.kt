package pl.farmaprom.trainings.contactsapp.contacts.preview

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import pl.farmaprom.trainings.contactsapp.contacts.data.Contact

@Composable
fun ContactPreviewScreen(
    modifier: Modifier = Modifier,
    contact: Contact
) {
    MaterialTheme {
        Scaffold(
            // topBar = TODO: Add top bar implementation
        ) { padding ->
            ContactPreviewList(
                Modifier.padding(padding),
                contact = contact
            )
        }
    }
}

@Composable
fun ContactPreviewList(
    modifier: Modifier = Modifier,
    contact: Contact
) {
    LazyColumn {
        item {
            //ContactBaseInfo(modifier = modifier, contact = contact) TODO: Add contact base info implementation
        }
        item {
            //ContactDetailItem(label = "Email", value = contact.email) TODO: Add contact detail item reusable implementation
        }
        item {
            //ContactDetailItem(label = "Telefon", value = contact.phone)
        }
    }
}

