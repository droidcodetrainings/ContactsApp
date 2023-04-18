package pl.farmaprom.trainings.contactsapp.contacts.presentation.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import pl.farmaprom.trainings.contactsapp.R
import pl.farmaprom.trainings.contactsapp.ui.theme.ContactsAppTheme

@Composable
fun ContactsItem(
    profileImageUrl: String?,
    name: String,
    isFavourite: Boolean = false
) {
    val resolvedUrl = profileImageUrl ?: R.drawable.ic_launcher_background
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            GlideImage(
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape),
                imageModel = { resolvedUrl },
                previewPlaceholder = R.drawable.ic_launcher_background,
                imageOptions = ImageOptions(
                    alignment = Alignment.Center,
                    contentScale = ContentScale.Crop
                )
            )
            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = name,
                style = MaterialTheme.typography.headlineSmall
            )
        }
        if (isFavourite) {
            Icon(
                modifier = Modifier.size(24.dp),
                imageVector = Icons.Default.Star,
                contentDescription = ""
            )
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Preview(showBackground = true, device = Devices.NEXUS_10)
@Composable
fun ContactItemFavouritePreview() {
    ContactsAppTheme {
        ContactsItem(
            profileImageUrl = null,
            name = "Preview Contact",
            isFavourite = true
        )
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun ContactItemPreview() {
    ContactsAppTheme {
        ContactsItem(
            profileImageUrl = null,
            name = "Preview Contact",
            isFavourite = false
        )
    }
}
