package pl.farmaprom.trainings.contactsapp.presentation.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pl.farmaprom.trainings.contactsapp.R
import pl.farmaprom.trainings.contactsapp.ui.theme.ContactsAppTheme

@Composable
fun ContactItem(
    modifier: Modifier = Modifier,
    name: String,
    isFavourite: Boolean,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BaseContactView(name = name)
        Icon(
            modifier = modifier.padding(8.dp),
            imageVector = Icons.Filled.Star,
            contentDescription = null
        )
    }

}

@Composable
fun BaseContactView(
    modifier: Modifier = Modifier,
    name: String
) {
    Row(
        modifier = modifier.padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = modifier.size(64.dp),
            painter = painterResource(R.drawable.face),
            contentDescription = null
        )
        Text(
            modifier = modifier.padding(16.dp),
            text = name,
            style = MaterialTheme.typography.headlineMedium
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun ContactItemPreview() {
    ContactsAppTheme {
        ContactItem(name = "Joe Doe", isFavourite = true)
    }
}
