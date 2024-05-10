package pl.farmaprom.trainings.contactsapp.list.presentation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pl.farmaprom.trainings.contactsapp.contacts.data.Contact
import pl.farmaprom.trainings.contactsapp.contacts.utils.generateFakeContactsList
import pl.farmaprom.trainings.contactsapp.ui.ContactItem
import pl.farmaprom.trainings.contactsapp.ui.HeaderItem
import pl.farmaprom.trainings.contactsapp.ui.theme.ContactsAppTheme

@Composable
fun ContactsList(
    viewState: ContactsListViewState,
    paddingValues: PaddingValues,
    onContactClick: (Contact) -> Unit = {}
) {
    LazyColumn(
        modifier = Modifier.padding(paddingValues)
    ) {
        item {
            HeaderItem(text = "Kontakty")
        }

        items(viewState.list) {
            ContactItem(
                imageUrl = it.imageUrl,
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
fun ContactsListPreview() {
    ContactsAppTheme {
        ContactsList(
            viewState = ContactsListViewState(
                list = generateFakeContactsList(20)
            ),
            paddingValues = PaddingValues(all = 0.dp)
        )
    }
}
