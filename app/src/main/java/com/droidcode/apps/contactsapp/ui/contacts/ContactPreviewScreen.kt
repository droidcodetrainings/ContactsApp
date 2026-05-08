package com.droidcode.apps.contactsapp.ui.contacts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import coil.compose.AsyncImage
import com.droidcode.apps.contactsapp.R
import com.droidcode.apps.contactsapp.conatacts.data.Contact
import com.droidcode.apps.contactsapp.ui.theme.ContactsAppTheme

@Composable
fun ContactPreviewScreen(
    contact: Contact,
    onBackClick: () -> Unit = {},
    onCallContactClick: (String) -> Unit = {}
) {
    Scaffold(
        topBar = {
            ContactDetailAppBar(
                onBackClick = onBackClick,
                isFavorite = contact.isFavorite
            )
        }
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactDetailAppBar(
    onBackClick: () -> Unit,
    isFavorite: Boolean
) {
    TopAppBar(
        title = { },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null
                )
            }
        },
        actions = {
            IconButton(onClick = { /* TODO: Favorite action */ }) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = if (isFavorite) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.outline
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            navigationIconContentColor = MaterialTheme.colorScheme.primary,
            actionIconContentColor = MaterialTheme.colorScheme.primary
        )
    )
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
        PreviewIcon(contact.imageUrl)
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = "${contact.firstName} ${contact.lastName}",
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = contact.relation ?: "",
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.outline
        )
        Spacer(modifier = Modifier.size(8.dp))
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
    imageUrl: String?
) {
    AsyncImage(
        model = imageUrl ?: "https://i.pravatar.cc/150",
        contentDescription = null,
        modifier = Modifier
            .size(96.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.secondaryContainer),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun ContactDetail(label: String, value: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.outline
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ContactPreviewScreenPreview() {
    val contact by remember {
        mutableStateOf(
            Contact(
                firstName = "John",
                lastName = "Doe",
                email = "john.doe@example.com",
                phone = "123-456-789",
                city = "New York",
                relation = "Friend",
                imageUrl = "https://i.pravatar.cc/150?img=1",
                isFavorite = true
            )
        )
    }
    ContactsAppTheme {
        ContactPreviewScreen(contact = contact)
    }
}
