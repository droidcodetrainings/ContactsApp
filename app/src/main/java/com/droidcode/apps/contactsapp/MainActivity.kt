package com.droidcode.apps.contactsapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
            val navController = rememberNavController()

            ContactsAppTheme {
                NavHost(
                    navController = navController,
                    startDestination = "contactsList"
                ) {
                    composable("contactsList") {
                        ContactsListScreen(
                            contactState.contacts,
                            onContactSelected = {
                                viewModel.selectContact(it)
                                navController.navigate("contactPreview")
                            }
                        )
                    }
                    composable("contactPreview") {
                        contactState.selected?.let { contact ->
                            ContactPreviewScreen(
                                contact = contact,
                                onBackClick = {
                                    viewModel.unselectContact()
                                    navController.popBackStack()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
