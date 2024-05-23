package pl.farmaprom.trainings.contactsapp.preview.presentation

import android.Manifest
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.StarOutline
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import pl.farmaprom.trainings.contactsapp.R
import pl.farmaprom.trainings.contactsapp.contacts.data.Contact
import pl.farmaprom.trainings.contactsapp.contacts.utils.generateFakeContact
import pl.farmaprom.trainings.contactsapp.ui.theme.ContactsAppTheme

@Composable
fun ContactBaseInfoView(
    contact: Contact,
    onContactClick: (String) -> Unit = {}
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
        Button(onClick = {
            onContactClick(contact.email) //temporary, should be contact.phoneNumber
        }) {
            Icon(
                imageVector = Icons.Default.Call,
                contentDescription = null,
                tint = Color.White
            )
        }
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

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun ContactPreviewScreen(
    contact: Contact,
    onNavigateUp: () -> Unit = {},
    onContactClick: (String) -> Unit = {}
) {
    val permissionState = rememberPermissionState(
        permission = Manifest.permission.CALL_PHONE
    )

    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            Log.d("TAG", "Permission granted after request")
        } else {
            Log.d("TAG", "permission denied")
        }
    }

    LaunchedEffect(permissionState) {
        if (permissionState.status.isGranted.not()
            && permissionState.status.shouldShowRationale) {
            Log.d("TAG", "show rationale")
        } else if (permissionState.status.isGranted.not()) {
            requestPermissionLauncher.launch(Manifest.permission.CALL_PHONE)
        } else {
            Log.d("TAG", "permission granted before")
        }
    }

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
                    IconButton(onClick = {
                        onNavigateUp()
                    }) {
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
            contact = contact,
            onContactClick = onContactClick
        )
    }
}

@Composable
fun ContactPreviewList(
    modifier: Modifier = Modifier,
    contact: Contact,
    onContactClick: (String) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 16.dp)
    ) {
        item {
            ContactBaseInfoView(
                contact = contact,
                onContactClick = onContactClick
            )
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
