package com.droidcode.apps.contactsapp.ui.contacts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.droidcode.apps.contactsapp.R
import com.droidcode.apps.contactsapp.conatacts.data.Contact
import com.droidcode.apps.contactsapp.ui.theme.ContactsAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactEditScreen(
    contact: Contact,
    onCancelClick: () -> Unit = {},
    onDoneClick: (Contact) -> Unit = {}
) {
    var firstName by remember { mutableStateOf(contact.firstName) }
    var lastName by remember { mutableStateOf(contact.lastName) }
    var email by remember { mutableStateOf(contact.email) }
    var phone by remember { mutableStateOf(contact.phone) }
    var city by remember { mutableStateOf(contact.city ?: "") }
    var birthday by remember { mutableStateOf("") } // Joda DateTime handling simplified for UI

    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    TextButton(onClick = onCancelClick) {
                        Text(text = stringResource(R.string.cancel))
                    }
                },
                actions = {
                    TextButton(onClick = {
                        onDoneClick(
                            contact.copy(
                                firstName = firstName,
                                lastName = lastName,
                                email = email,
                                phone = phone,
                                city = city
                            )
                        )
                    }) {
                        Text(text = stringResource(R.string.done))
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f)
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.secondaryContainer),
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        model = contact.imageUrl ?: "https://i.pravatar.cc/150",
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp)
                            .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.5f), CircleShape)
                            .padding(4.dp),
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column(modifier = Modifier.weight(1.0f)) {
                    OutlinedTextField(
                        value = firstName,
                        onValueChange = { firstName = it },
                        label = { Text(stringResource(R.string.label_first_name)) },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = lastName,
                        onValueChange = { lastName = it },
                        label = { Text(stringResource(R.string.label_last_name)) },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(stringResource(R.string.label_adres_email)) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = phone,
                onValueChange = { phone = it },
                label = { Text(stringResource(R.string.label_phone_number)) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = city,
                onValueChange = { city = it },
                label = { Text(stringResource(R.string.label_city)) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = birthday,
                onValueChange = { birthday = it },
                label = { Text(stringResource(R.string.label_birthday)) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ContactEditScreenPreview() {
    ContactsAppTheme {
        ContactEditScreen(
            contact = Contact(
                firstName = "Olivia",
                lastName = "Eklund",
                email = "olivia.eklund@gmail.com",
                phone = "+48 500 600 700",
                city = "Kraków"
            )
        )
    }
}
