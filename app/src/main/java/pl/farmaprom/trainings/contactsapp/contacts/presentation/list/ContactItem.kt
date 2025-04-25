package pl.farmaprom.trainings.contactsapp.contacts.presentation.list

import androidx.compose.foundation.clickable
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
fun ContactItem(
    modifier: Modifier = Modifier,
    profileImageUrl: String? = "https://raw.githubusercontent.com/kamilruchalaf/trainingassets/main/assets/face1.png",
    name: String = "Jannet Doe",
    isFavourite: Boolean = false,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ContactBaseItem(profileImageUrl, name, modifier)
        if (isFavourite) FavouriteIcon(modifier)
    }
}

@Composable
fun FavouriteIcon(modifier: Modifier) {
    Icon(
        modifier = modifier
            .padding(8.dp)
            .size(36.dp),
        imageVector = Icons.Filled.Star,
        contentDescription = null
    )
}

@Composable
fun ContactBaseItem(profileImageUrl: String?, name: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        GlideImage(
            imageModel = { profileImageUrl ?: R.drawable.face1 },
            previewPlaceholder = R.drawable.face1,
            modifier = modifier
                .size(64.dp)
                .clip(CircleShape),
            imageOptions = ImageOptions(
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop
            )
        )
        Text(
            modifier = modifier.padding(8.dp),
            text = name,
            style = MaterialTheme.typography.headlineMedium
        )
    }
}

@Preview(
    showBackground = true,
    device = Devices.PIXEL_4,
)
@Composable
private fun ContactItemPreview() {
    ContactsAppTheme {
        ContactItem(isFavourite = true)
    }
}
