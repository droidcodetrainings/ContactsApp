package pl.farmaprom.trainings.contactsapp.ui.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.glide.GlideImage
import pl.farmaprom.trainings.contactsapp.R
import pl.farmaprom.trainings.contactsapp.contacts.data.Contact
import pl.farmaprom.trainings.contactsapp.ui.theme.ContactsAppTheme
import pl.farmaprom.trainings.contactsapp.ui.theme.Dimens


@Composable
fun AvatarAndName(
    profileImageUrl: String?,
    name: String,
    surname: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(Dimens.large)
    ) {
        GlideImage(
            imageModel = { profileImageUrl },
            modifier = Modifier
                .size(Dimens.contactDetailsContactImageSize)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.height(Dimens.medium))

        Text(
            text = "$name $surname",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
    }
}


@Composable
fun KeyValueItem(
    key: String,
    value: String,
    isClickable: Boolean = false,
    onClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = Dimens.medium, vertical = Dimens.small)
            .then(
                if (isClickable && onClick != null) {
                    Modifier.clickable { onClick() }
                } else Modifier
            ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(Dimens.medium)
        ) {
            Text(
                text = key,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium,
                color = if (isClickable) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.onSurface
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactDetailScreen(
    contact: Contact,
    onBackClick: () -> Unit = {},
    onEditClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Favorite",
                            tint = if (contact.isFavourite) MaterialTheme.colorScheme.primary
                            else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onEditClick,
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Text(
                    text = "Edytuj",
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                AvatarAndName(
                    profileImageUrl = contact.profileImageUrl,
                    name = contact.name,
                    surname = contact.surname
                )
            }


            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(Dimens.large),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {

                    if (contact.phone.isNotEmpty()) {
                        Card(
                            onClick = {
                                val intent = Intent(Intent.ACTION_DIAL).apply {
                                    data = Uri.parse("tel:${contact.phone}")
                                }
                                context.startActivity(intent)
                            },
                            modifier = Modifier.size(64.dp)
                        ) {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier.fillMaxSize()
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Phone,
                                    contentDescription = "Call",
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                    }


                    if (contact.email.isNotEmpty()) {
                        Card(
                            onClick = {
                                val intent = Intent(Intent.ACTION_SENDTO).apply {
                                    data = Uri.parse("mailto:${contact.email}")
                                }
                                context.startActivity(intent)
                            },
                            modifier = Modifier.size(64.dp)
                        ) {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier.fillMaxSize()
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Email,
                                    contentDescription = "Email",
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                    }
                }
            }


            if (contact.email.isNotEmpty()) {
                item {
                    KeyValueItem(
                        key = "Adres email",
                        value = contact.email
                    )
                }
            }

            if (contact.phone.isNotEmpty()) {
                item {
                    KeyValueItem(
                        key = "Telefon",
                        value = contact.phone,
                        isClickable = true,
                        onClick = {
                            val intent = Intent(Intent.ACTION_DIAL).apply {
                                data = Uri.parse("tel:${contact.phone}")
                            }
                            context.startActivity(intent)
                        }
                    )
                }
            }

            if (!contact.city.isNullOrEmpty()) {
                item {
                    KeyValueItem(
                        key = "Miasto",
                        value = contact.city
                    )
                }
            }

            if (contact.birthday != null) {
                item {
                    KeyValueItem(
                        key = "Urodziny",
                        value = "${contact.birthday.dayOfMonth} ${contact.birthday.monthOfYear().asText}"
                    )
                }
            }

            if (!contact.relation.isNullOrEmpty()) {
                item {
                    KeyValueItem(
                        key = "Relacja",
                        value = contact.relation
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ContactDetailScreenPreview() {
    ContactsAppTheme {
        val sampleContact = Contact(
            id = 1,
            name = "Olivia",
            surname = "Eklund",
            email = "olivia.eklund@gmail.com",
            phone = "+48 500 600 700",
            city = "Kraków",
            profileImageUrl = "https://raw.githubusercontent.com/kamilruchalaf/trainingassets/main/assets/face1.png",
            isFavourite = true,
            relation = "Siostra"
        )

        ContactDetailScreen(
            contact = sampleContact
        )
    }
}