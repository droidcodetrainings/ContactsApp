package pl.farmaprom.trainings.contactsapp.contacts.presentation.list

import androidx.compose.foundation.layout.Column
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
    LazyColumn(modifier = modifier.fillMaxSize()) {
        item {
            HeaderItem(stringResource(R.string.header_contacts_list_text))
        }
        items(contactsViewState.contacts){
            ContactItem(
                profileImageUrl = it.profileImageUrl,
                name = "${it.name} ${it.surname}",
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
private fun ContactsListViewPreview() {
    ContactsAppTheme {
        val contactViewState by remember { mutableStateOf(
            ContactsViewState(contacts = generateContacts(100))
        ) }
        ContactsListView(contactsViewState = contactViewState)
    }
}

