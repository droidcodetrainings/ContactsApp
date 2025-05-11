package pl.farmaprom.trainings.contactsapp.presentation.list

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
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.glide.GlideImage
import pl.farmaprom.trainings.contactsapp.R

@Composable
fun ContactItem(
    profileUrl: String? = "https://raw.githubusercontent.com/kamilruchalaf/trainingassets/main/assets/%20%20kamper.jpg",
    isFavourite: Boolean = false,
    modifier: Modifier = Modifier,
    name: String = "Izabelle Doe",
    onClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable {
                onClick()
            },
        verticalAlignment = CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        BaseContactItem(profileUrl = profileUrl?:"", name = name)
        if (isFavourite) {
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
    name: String
) {
    Row(
        modifier = Modifier.padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        GlideImage(
            imageModel = { profileUrl },
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape),
            previewPlaceholder = R.drawable.face
        )
        Text(
            text = name,
            modifier = Modifier.padding(8.dp),
            style = MaterialTheme.typography.headlineMedium
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


