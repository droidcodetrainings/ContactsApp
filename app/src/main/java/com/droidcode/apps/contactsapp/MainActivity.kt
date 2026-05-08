package com.droidcode.apps.contactsapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.droidcode.apps.contactsapp.ui.contacts.ContactPreviewScreen
import com.droidcode.apps.contactsapp.ui.contacts.ContactsListScreen
import com.droidcode.apps.contactsapp.ui.theme.ContactsAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: MainViewModel = viewModel()
            val contactState by viewModel.contacts.collectAsStateWithLifecycle()

            ContactsAppTheme {
                ContactsListScreen(
                    contactState.contacts,
                    onContactSelected = {
                        viewModel.selectContact(it)
                    })
//                ContactPreviewScreen(contactState.contacts[0])
            }
        }
    }
}
