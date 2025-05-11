package pl.farmaprom.trainings.contactsapp.presentation.list

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
import pl.farmaprom.trainings.contactsapp.ui.theme.ContactsAppTheme
import pl.farmaprom.trainings.contactsapp.utils.generateContactsList

@Composable
fun ContactsViewList(
    modifier: Modifier = Modifier,
    contactsViewState: ContactsViewState
) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        item {
            HeaderItem(headerText = stringResource(R.string.header_item_text))
        }

        items(contactsViewState.contacts) {
            ContactItem(
                profileUrl = it.profileImageUrl,
                isFavourite = it.isFavourite,
                name = "${it.name} ${it.surname}",
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ContactsListPreview() {
    ContactsAppTheme {
        val contactViewState by remember {
            mutableStateOf(
                ContactsViewState(
                    contacts = generateContactsList(
                        100
                    )
                )
            )
        }
        ContactsViewList(contactsViewState = contactViewState)
    }
}