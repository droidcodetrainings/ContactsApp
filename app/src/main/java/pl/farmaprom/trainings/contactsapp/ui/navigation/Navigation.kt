package pl.farmaprom.trainings.contactsapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pl.farmaprom.trainings.contactsapp.contacts.data.Contact
import pl.farmaprom.trainings.contactsapp.sampleData
import pl.farmaprom.trainings.contactsapp.ui.components.ContactDetailScreen
import pl.farmaprom.trainings.contactsapp.ui.components.ContactListScreen

sealed class Screen(val route: String) {
    object ContactList : Screen("contact_list")
    object ContactDetail : Screen("contact_detail/{contactId}") {
        fun createRoute(contactId: Long) = "contact_detail/$contactId"
    }
}

@Composable
fun ContactsNavigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.ContactList.route
    ) {
        composable(Screen.ContactList.route) {
            ContactListScreen(
                contacts = sampleData,
                onContactClick = { contactId ->
                    navController.navigate(Screen.ContactDetail.createRoute(contactId))
                }
            )
        }

        composable(Screen.ContactDetail.route) { backStackEntry ->
            val contactId = backStackEntry.arguments?.getString("contactId")?.toLongOrNull() ?: -1
            val contact = sampleData.find { it.id == contactId } ?: Contact()

            ContactDetailScreen(
                contact = contact,
                onBackClick = {
                    navController.popBackStack()
                },
                onEditClick = {
                }
            )
        }
    }
}