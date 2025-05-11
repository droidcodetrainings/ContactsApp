package pl.farmaprom.trainings.contactsapp.presentation.list

import androidx.compose.foundation.layout.Arrangement
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
import pl.farmaprom.trainings.contactsapp.R
import pl.farmaprom.trainings.contactsapp.contacts.data.Contact
import pl.farmaprom.trainings.contactsapp.utils.generateContacts
import pl.farmaprom.trainings.contactsapp.ui.theme.ContactsAppTheme


@Composable
fun ContactsViewList(
    modifier: Modifier = Modifier,
    viewState: ContactsViewState,
    onContactClick: (Contact) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 8.dp, horizontal = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            HeaderItem(text = stringResource(R.string.header_item_text))
        }
        items(viewState.contacts) {
            ContactItem(
                profileImageUrl = it.profileImageUrl,
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
private fun ContactsListPreview() {
    val viewState by remember { mutableStateOf(ContactsViewState(contacts = generateContacts(100))) }
    ContactsAppTheme {
        ContactsViewList(viewState = viewState)
    }
}