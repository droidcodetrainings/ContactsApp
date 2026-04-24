package com.droidcode.apps.contactsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices.PIXEL_9_PRO_XL
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.droidcode.apps.contactsapp.conatacts.data.Contact
import com.droidcode.apps.contactsapp.ui.theme.ContactsAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: MainViewModel = viewModel()
            val contactState by viewModel.contacts.collectAsStateWithLifecycle()

            ContactsAppTheme {
                LazyContactsList(contactState.contacts)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LazyContactsList(
    contactItems: List<Contact>
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.contacts_title)) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            items(contactItems) { item ->
                ContactListItem(item = item)
            }
        }
    }
}

@Composable
fun ContactsList() {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            ContactsHeader()
            ContactListItem(
                item = Contact(
                    firstName = "John",
                    lastName = "Doe",
                    isFavorite = true,
                    imageUrl = "https://i.pravatar.cc/150?img=12"
                )
            )
            ContactListItem(
                item = Contact(
                    firstName = "John",
                    lastName = "Doe",
                    isFavorite = false,
                    imageUrl = "https://i.pravatar.cc/150?img=25"
                )
            )
        }
    }
}

@Composable
fun ContactsHeader(modifier: Modifier = Modifier) {
    Text(
        text = stringResource(R.string.contacts_title),
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(8.dp),
        color = MaterialTheme.colorScheme.primary,
        style = MaterialTheme.typography.bodyLarge
    )
}

@Composable
fun ContactListItem(item: Contact) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = item.imageUrl ?: "https://i.pravatar.cc/150",
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .padding(1.dp)
            )
            Column {
                Text(
                    text = item.firstName,
                    style = MaterialTheme.typography.labelSmall
                )
                Text(
                    text = item.lastName,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
        AnimatedVisibility(
            visible = item.isFavorite
        ) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.tertiary,
            )
        }
    }
}

@Preview
@Composable
private fun ContactsListScreenPreview() {
    ContactsAppTheme {
        ContactsList()
    }
}

@Preview(showBackground = true, device = PIXEL_9_PRO_XL)
@Composable
fun HeaderPreview() {
    ContactsAppTheme {
        ContactsHeader()
    }
}

@Preview(showBackground = true, device = PIXEL_9_PRO_XL)
@Composable
private fun ContactListItemFavoritePreview() {
    ContactsAppTheme {
        ContactListItem(
            item = Contact(
                firstName = "John",
                lastName = "Doe",
                isFavorite = true,
                imageUrl = null
            )
        )
    }
}

@Preview(showBackground = true, device = PIXEL_9_PRO_XL)
@Composable
private fun ContactListItemPreview() {
    ContactsAppTheme {
        ContactListItem(
            item = Contact(
                firstName = "John",
                lastName = "Doe",
                isFavorite = false,
                imageUrl = null
            )
        )
    }
}

