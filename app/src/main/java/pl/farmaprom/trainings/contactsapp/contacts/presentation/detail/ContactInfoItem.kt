package pl.farmaprom.trainings.contactsapp.contacts.presentation.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import pl.farmaprom.trainings.contactsapp.R
import pl.farmaprom.trainings.contactsapp.ui.theme.ContactsAppTheme
import pl.farmaprom.trainings.contactsapp.ui.theme.Dimens

@Composable
fun ContactInfoItem(
    imageUrl: String?,
    name: String,
    surname: String
) {
    val profileImage = imageUrl ?: R.drawable.ic_launcher_background
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GlideImage(
            modifier = Modifier
                .clip(CircleShape)
                .size(Dimens.contactDetailsContactImageSize),
            imageModel = { profileImage },
            previewPlaceholder = R.drawable.ic_launcher_background,
            imageOptions = ImageOptions(
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center
            )
        )
        Text(
            modifier = Modifier.padding(Dimens.large),
            text = "$name $surname",
            style = MaterialTheme.typography.headlineLarge,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
@Preview(name = "ContactInfoItem", showBackground = true)
private fun ContactInfoItemPreview() {
    ContactsAppTheme {
        ContactInfoItem(
            imageUrl = null,
            name = "Name",
            surname = "Surname"
        )
    }
}
