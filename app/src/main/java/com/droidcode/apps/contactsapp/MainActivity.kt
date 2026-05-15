package com.droidcode.apps.contactsapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.droidcode.apps.contactsapp.server.KtorServer
import com.droidcode.apps.contactsapp.ui.contacts.ContactEditScreen
import com.droidcode.apps.contactsapp.ui.contacts.ContactPreviewScreen
import com.droidcode.apps.contactsapp.ui.contacts.ContactsListScreen
import com.droidcode.apps.contactsapp.ui.theme.ContactsAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startService(Intent(this, KtorServer::class.java))

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
                            contactItems = contactState.contacts,
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
                                },
                                onCallContactClick = {
                                    Log.i("MainActivity", "Calling contact: $it")
                                },
                                onEditClick = {
                                    navController.navigate("contactEdit/${contact.id}")
                                }
                            )
                        }
                    }
                    composable(
                        route = "contactEdit/{contactId}",
                        arguments = listOf(navArgument("contactId") { type = NavType.StringType })
                    ) { backStackEntry ->
                        val contactId = backStackEntry.arguments?.getString("contactId")
                        Log.i("MainActivity", "Editing contact with ID: $contactId")

                        contactState.selected?.let { contact ->
                            ContactEditScreen(
                                contact = contact,
                                onCancelClick = {
                                    navController.popBackStack()
                                },
                                onDoneClick = {
                                    // Update logic would go here
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
