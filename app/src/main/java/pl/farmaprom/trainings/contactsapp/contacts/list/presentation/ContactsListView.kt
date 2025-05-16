package pl.farmaprom.trainings.contactsapp.contacts.list.presentation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import pl.farmaprom.trainings.contactsapp.R
import pl.farmaprom.trainings.contactsapp.contacts.data.Contact
import pl.farmaprom.trainings.contactsapp.contacts.utils.generateContacts
import pl.farmaprom.trainings.contactsapp.ui.theme.ContactsAppTheme

@Composable
fun ContactsListView(
    modifier: Modifier = Modifier,
    contactsViewState: ContactsViewState,
    onContactClick: (Contact) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 8.dp, horizontal = 8.dp)
    ) {
        item {
            HeaderItem(stringResource(R.string.header_contacts_text))
        }
        items(contactsViewState.contacts) { contact ->
            ContactItem(
                prfileUrl = contact.profileImageUrl,
                isFavourite = contact.isFavourite,
                name = "${contact.name} ${contact.surname}",
                onClick = {
                    onContactClick(contact)
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ContactsListViewPreview() {
    val contactsViewState by remember { mutableStateOf(ContactsViewState(contacts = generateContacts(100))) }
    ContactsAppTheme {
        ContactsListView(
            contactsViewState = contactsViewState
        )
    }
}

