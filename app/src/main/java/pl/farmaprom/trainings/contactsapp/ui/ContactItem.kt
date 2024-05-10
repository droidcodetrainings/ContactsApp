package pl.farmaprom.trainings.contactsapp.ui

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import pl.farmaprom.trainings.contactsapp.R
import pl.farmaprom.trainings.contactsapp.ui.theme.ContactsAppTheme
import pl.farmaprom.trainings.contactsapp.ui.theme.Dimens

@Composable
fun ContactItem(
    imageUrl: String?,
    name: String,
    isFavourite: Boolean,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            }
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            ProfileIcon(
                profileImageUrl = imageUrl
            )
            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = name
            )
        }
        if (isFavourite) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = ""
            )
        }
    }
}

@Composable
private fun ProfileIcon(
    modifier: Modifier = Modifier,
    profileImageUrl: String?
) {
    val imageUrl = profileImageUrl ?: R.drawable.ic_launcher_background
    GlideImage(
        modifier = modifier
            .clip(CircleShape)
            .size(Dimens.contactListItemProfileImageSize),
        imageModel = {
            imageUrl
        },
        imageOptions = ImageOptions(
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center
        ),
        previewPlaceholder = R.drawable.ic_launcher_background,
    )
}

@Preview(showBackground = true)
@Composable
fun ContactItemPreview() {
    ContactsAppTheme {
        ContactItem(
            imageUrl = "",
            name = "Sample User",
            isFavourite = true
        )
    }
}
