package pl.farmaprom.trainings.contactsapp.contacts.preview

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.widget.Toast
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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
import pl.farmaprom.trainings.contactsapp.sampleData
import pl.farmaprom.trainings.contactsapp.ui.theme.ContactsAppTheme

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ContactPreviewScreen(
    contact: Contact,
    onBackClick: () -> Unit = {}
) {
    val context = LocalContext.current
    
    // Permission state for call permission
    val callPermissionState = rememberPermissionState(permission = Manifest.permission.CALL_PHONE)
    
    // Track if we need to show the permission rationale dialog
    var showRationaleDialog by remember { mutableStateOf(false) }
    
    // Keep track of the phone number we want to call after permission is granted
    var phoneNumberToCall by remember { mutableStateOf<String?>(null) }
    
    // Permission rationale dialog
    if (showRationaleDialog) {
        AlertDialog(
            onDismissRequest = { showRationaleDialog = false },
            title = { Text("Permission Required") },
            text = { Text("Phone call permission is needed to make calls directly from the app.") },
            confirmButton = {
                TextButton(onClick = {
                    showRationaleDialog = false
                    callPermissionState.launchPermissionRequest()
                }) {
                    Text("Request Permission")
                }
            },
            dismissButton = {
                TextButton(onClick = { 
                    showRationaleDialog = false
                    phoneNumberToCall = null
                }) {
                    Text("Cancel")
                }
            }
        )
    }
    
    // Handle permission result for a pending call
    if (phoneNumberToCall != null && callPermissionState.status.isGranted) {
        try {
            val intent = Intent(Intent.ACTION_CALL).apply {
                data = Uri.parse("tel:$phoneNumberToCall")
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "Failed to make call: ${e.message}", Toast.LENGTH_SHORT).show()
        } finally {
            phoneNumberToCall = null
        }
    }

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
                ContactBaseInfo(
                    contact = contact, 
                    onCallContactClick = { phoneNumber ->
                        when {
                            callPermissionState.status.isGranted -> {
                                // Permission already granted, make the call
                                try {
                                    val intent = Intent(Intent.ACTION_CALL).apply {
                                        data = Uri.parse("tel:$phoneNumber")
                                    }
                                    context.startActivity(intent)
                                } catch (e: Exception) {
                                    Toast.makeText(context, "Failed to make call: ${e.message}", Toast.LENGTH_SHORT).show()
                                }
                            }
                            callPermissionState.status.shouldShowRationale -> {
                                // Show rationale dialog
                                phoneNumberToCall = phoneNumber
                                showRationaleDialog = true
                            }
                            else -> {
                                // First time asking for permission
                                phoneNumberToCall = phoneNumber
                                callPermissionState.launchPermissionRequest()
                            }
                        }
                    }
                )
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
