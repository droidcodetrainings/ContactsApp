package pl.farmaprom.trainings.contactsapp.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.skydoves.landscapist.glide.GlideImage
import pl.farmaprom.trainings.contactsapp.R
import pl.farmaprom.trainings.contactsapp.ui.theme.ContactsAppTheme
import pl.farmaprom.trainings.contactsapp.ui.theme.Dimens

@Composable
fun ContactItem(
    profileImageUrl: String,
    name: String,
    isFavourite: Boolean = false,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = Dimens.small, horizontal = Dimens.medium),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Profile Image
        GlideImage(
            imageModel = { profileImageUrl },
            modifier = Modifier
                .size(Dimens.contactListItemProfileImageSize)
                .clip(CircleShape)
        )

        // Name
        Text(
            text = name,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = Dimens.medium)
        )

        if (isFavourite) {
            Icon(
                painter = painterResource(id = android.R.drawable.btn_star_big_on),
                contentDescription = "Favourite",
                modifier = Modifier.size(Dimens.contactListItemFavouriteIconSize)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ContactItemPreview() {
    ContactsAppTheme {
        ContactItem(
            profileImageUrl = "https://github.com/tnosekk/ContactsApp/tree/main/app/src/main/res/face.png",
            name = "John Doe",
            isFavourite = true
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ContactItemPreviewNoFavorite() {
    ContactsAppTheme {
        ContactItem(
            profileImageUrl = "https://github.com/tnosekk/ContactsApp/tree/main/app/src/main/res/face.png",
            name = "Jane Smith",
            isFavourite = false
        )
    }
}