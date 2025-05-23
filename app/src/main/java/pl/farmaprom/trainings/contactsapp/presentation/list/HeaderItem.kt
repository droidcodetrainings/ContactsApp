package pl.farmaprom.trainings.contactsapp.presentation.list

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pl.farmaprom.trainings.contactsapp.R
import pl.farmaprom.trainings.contactsapp.ui.theme.ContactsAppTheme

@Composable
fun HeaderItem(
    modifier: Modifier = Modifier,
    headerText: String
) {
    Row(modifier = modifier
        .padding(8.dp)
        .fillMaxWidth()) {
        Text(
            text = headerText,
            style = MaterialTheme.typography.headlineMedium
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun HeaderItemPreview() {
    ContactsAppTheme {
        HeaderItem(headerText = stringResource(R.string.header_item_text))
    }
}
