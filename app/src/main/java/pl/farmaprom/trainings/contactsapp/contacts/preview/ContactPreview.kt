package pl.farmaprom.trainings.contactsapp.contacts.preview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import pl.farmaprom.trainings.contactsapp.R
import pl.farmaprom.trainings.contactsapp.contacts.data.Contact
import pl.farmaprom.trainings.contactsapp.sampleData
import pl.farmaprom.trainings.contactsapp.ui.theme.ContactsAppTheme

@Composable
fun ContactPreviewScreen(
    contact: Contact,
    onBackClick: () -> Unit = {},
    onCallContactClick: (String) -> Unit = {}
) {
    Scaffold(
        topBar = { ContactDetailAppBar(onBackClick = onBackClick) }
    ) { innerPadding ->
        LazyColumn(
            contentPadding = innerPadding,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(16.dp))
                ContactBaseInfo(contact = contact, onCallContactClick = onCallContactClick)
                Spacer(modifier = Modifier.height(24.dp))
            }
            item {
                ContactDetail(
                    label = stringResource(R.string.label_adres_email),
                    value = contact.email
                )
            }
            item {
                ContactDetail(
                    label = stringResource(R.string.label_phone_number),
                    value = contact.phone
                )
            }
            item {
                ContactDetail(
                    label = stringResource(R.string.label_city),
                    value = contact.city ?: stringResource(R.string.text_city_no_provided)
                )
            }
        }
    }
}

@Composable
fun ContactDetailAppBar(
    onBackClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF2196F3))
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = onBackClick) {
            Icon(Icons.Filled.ArrowBack, contentDescription = null, tint = Color.White)
        }
        IconButton(onClick = { /* akcja ulubione */ }) {
            Icon(Icons.Default.Star, contentDescription = null, tint = Color.White)
        }
    }
}

@Composable
fun ContactBaseInfo(
    modifier: Modifier = Modifier,
    contact: Contact,
    onCallContactClick: (String) -> Unit = { }
) {

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PreviewIcon(modifier, contact.profileImageUrl)
        Spacer(modifier.size(8.dp))
        Text(
            text = contact.name,
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier.size(4.dp))
        Text(
            text = contact.relation ?: "",
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.outline
        )
        Spacer(modifier.size(8.dp))
        Button(
            onClick = {
                onCallContactClick(contact.phone)
            },
            shape = RectangleShape
        ) {
            Icon(
                imageVector = Icons.Outlined.Call,
                contentDescription = null,
                tint = Color.White
            )
        }
    }
}

@Composable
fun PreviewIcon(
    modifier: Modifier,
    profileImageUrl: String?
) {
    GlideImage(
        imageModel = { profileImageUrl ?: R.drawable.face1 },
        previewPlaceholder = R.drawable.face1,
        modifier = modifier
            .size(96.dp)
            .clip(CircleShape),
        imageOptions = ImageOptions(
            alignment = Alignment.Center,
            contentScale = ContentScale.Crop
        )
    )
}

@Composable
fun ContactDetail(label: String, value: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Text(text = label, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
        Text(text = value, style = MaterialTheme.typography.bodyLarge)
    }
}

@Preview(showBackground = true)
@Composable
private fun ContactPreviewScreenPreview() {
    val contact by remember { mutableStateOf(sampleData[1]) }
    ContactsAppTheme {
        ContactPreviewScreen(contact = contact)
    }
}
