package pl.farmaprom.trainings.contactsapp.contacts.presentation.list

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import pl.farmaprom.trainings.contactsapp.ui.theme.ContactsAppTheme

@Composable
fun HeaderItem(text: String) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
       Text(
           text = text,
           style = MaterialTheme.typography.headlineMedium
       )
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun HeaderItemPreview() {
    ContactsAppTheme {
        HeaderItem(text = "Text from Preview")
    }
}
