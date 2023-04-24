@file:OptIn(ExperimentalMaterial3Api::class)

package pl.farmaprom.trainings.contactsapp.contacts.presentation.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import pl.farmaprom.trainings.contactsapp.R
import pl.farmaprom.trainings.contactsapp.contacts.data.Contact
import pl.farmaprom.trainings.contactsapp.sampleData
import pl.farmaprom.trainings.contactsapp.ui.theme.ContactsAppTheme
import pl.farmaprom.trainings.contactsapp.ui.theme.Dimens

@Composable
fun DetailsScreen(
    contact: Contact
) {
    Scaffold(
        topBar = {
            ContactDetailsTopAppBar(
                isFavourite = contact.isFavourite,
                onNavigateBackClick = { /*TODO*/ },
                onFavouriteClick = {}
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(it)
                .padding(Dimens.medium)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                ContactInfoItem(
                    imageUrl = contact.profileImageUrl,
                    name = contact.name,
                    surname = contact.surname
                )
            }
            item {
                ContactData(contact = contact)
            }
        }
    }
}

@Composable
private fun ContactData(contact: Contact) {
    Column {
        KeyValueItem(key = stringResource(id = R.string.email), value = contact.email)
    }
}

@Preview
@Composable
fun DetailsScreenPreview() {
    ContactsAppTheme {
        DetailsScreen(
            contact = sampleData[0]
        )
    }
}
