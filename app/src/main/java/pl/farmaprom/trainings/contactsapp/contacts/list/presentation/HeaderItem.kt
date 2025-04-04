package pl.farmaprom.trainings.contactsapp.contacts.list.presentation

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pl.farmaprom.trainings.contactsapp.R
import pl.farmaprom.trainings.contactsapp.ui.theme.ContactsAppTheme

@Composable
fun HeaderItem(
    text: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Text(
            modifier = modifier.padding(8.dp),
            text = text,
            style = MaterialTheme.typography.headlineMedium
        )
    }
}


@Preview(
    showBackground = true,
    device = Devices.PIXEL_4_XL
)
@Composable
private fun HeaderItemPreview() {
    ContactsAppTheme {
        HeaderItem(text = stringResource(R.string.header_contacts_text))
    }
}