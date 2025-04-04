package pl.farmaprom.trainings.contactsapp.contacts.list.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.glide.GlideImage
import pl.farmaprom.trainings.contactsapp.R
import pl.farmaprom.trainings.contactsapp.ui.theme.ContactsAppTheme

@Composable
fun ContactItem(
    prfileUrl: String = "https://raw.githubusercontent.com/kamilruchalaf/trainingassets/main/assets/%20%20kamper.jpg",
    isFavourite: Boolean = false,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth(),
        verticalAlignment = CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        BaseContactItem(profileUrl = prfileUrl)
        if (isFavourite){
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = null
            )
        }
    }

}

@Composable
fun BaseContactItem(
    profileUrl: String,
    name: String = "Izabelle Doe"
) {
    Row(
        modifier = Modifier.padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        GlideImage(
            imageModel = { profileUrl },
            modifier = Modifier
                .padding(end = 8.dp)
                .size(36.dp)
                .clip(CircleShape),
            previewPlaceholder = R.drawable.face1,
        )
        Text(
            text = name,
            modifier = Modifier.padding(end = 8.dp),
            style = MaterialTheme.typography.bodyLarge

        )
    }
}


@Preview(showBackground = true)
@Composable
private fun ContactItemPreview() {
    ContactsAppTheme {
        ContactItem(isFavourite = true)
    }
}