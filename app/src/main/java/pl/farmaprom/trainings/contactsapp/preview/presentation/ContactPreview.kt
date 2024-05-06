package pl.farmaprom.trainings.contactsapp.preview.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import pl.farmaprom.trainings.contactsapp.R
import pl.farmaprom.trainings.contactsapp.contacts.data.Contact
import pl.farmaprom.trainings.contactsapp.contacts.utils.generateFakeContact
import pl.farmaprom.trainings.contactsapp.ui.theme.ContactsAppTheme

@Composable
fun ContactBaseInfoView(
    contact: Contact
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ProfileIcon(profileImageUrl = contact.imageUrl)
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = contact.name,
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = contact.additionalInfo,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.outline
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ContactBaseInfoViewPreview() {
    ContactsAppTheme {
        ContactBaseInfoView(
            contact = generateFakeContact(id = 99)
        )
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
            .size(96.dp),
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

@Composable
fun ContactDetailItem(
    key: String,
    value: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            text = key,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.outline
        )
        Spacer(modifier = Modifier.size(2.dp))
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ContactDetailItemPreview() {
    ContactsAppTheme {
        ContactDetailItem(
            key = "key",
            value = "value"
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactPreviewScreen(contact: Contact) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "")
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                navigationIcon = {
                    IconButton(onClick = {  }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = ""
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {  }) {
                        Icon(
                            imageVector = Icons.Default.StarOutline,
                            contentDescription = ""
                        )
                    }
                }
            )
        }
    ) {
        ContactPreviewList(
            modifier = Modifier.padding(it),
            contact = contact
        )
    }
}

@Composable
fun ContactPreviewList(
    modifier: Modifier = Modifier,
    contact: Contact
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 16.dp)
    ) {
        item {
            ContactBaseInfoView(contact = contact)
        }
        item {
            ContactDetailItem(
                key = "email",
                value = contact.email
            )
        }

        item {
            ContactDetailItem(
                key = stringResource(R.string.phone_number),
                value = "+48 245512442"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ContactPreviewScreenPreview() {
    ContactsAppTheme {
        ContactPreviewScreen(
            contact = generateFakeContact(id = 1)
        )
    }
}
