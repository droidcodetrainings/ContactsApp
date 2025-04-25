package pl.farmaprom.trainings.contactsapp.contacts.preview

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pl.farmaprom.trainings.contactsapp.R
import pl.farmaprom.trainings.contactsapp.contacts.data.Contact
import pl.farmaprom.trainings.contactsapp.sampleData
import pl.farmaprom.trainings.contactsapp.ui.theme.ContactsAppTheme


@Composable
fun ContactPreviewList(
    modifier: Modifier = Modifier,
    contact: Contact
) {
    LazyColumn {
        item {
            // ContactBaseInfoView() TODO: Implement ContactBaseInfoView
        }
        item{
            ContactDetailItem(
                key = stringResource(R.string.text_key_e_mail),
                value = contact.email
            )
        }
        item{
            ContactDetailItem(
                key = stringResource(R.string.text_phone_number),
                value = contact.phone
            )
        }
    }

}

@Composable
fun ContactDetailItem(
    modifier: Modifier = Modifier,
    key: String,
    value: String
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Text(
            text = key,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.outline
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ContactDetailItemPreview() {
    ContactsAppTheme {
        val contact = sampleData[0]
        ContactDetailItem(
            key = stringResource(R.string.text_key_e_mail),
            value = contact.email
        )
    }
}
