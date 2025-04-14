package pl.farmaprom.trainings.contactsapp.presentation.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pl.farmaprom.trainings.contactsapp.R

@Composable
fun ContactItem(
    modifier: Modifier = Modifier,
    name: String,
    isFavourite: Boolean,
) {
    val icon = if (isFavourite) Icons.Filled.Star else Icons.Outlined.Favorite

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = modifier.size(64.dp),
            painter = painterResource(id = R.drawable.face),
            contentDescription = null
        )
        Icon(
            modifier = modifier,
            imageVector = icon,
            contentDescription = null
        )
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_7_PRO)
@Composable
private fun ContactItemPreview() {
    MaterialTheme {
        ContactItem(name = "Golden Kamper", isFavourite = true)
    }
}


