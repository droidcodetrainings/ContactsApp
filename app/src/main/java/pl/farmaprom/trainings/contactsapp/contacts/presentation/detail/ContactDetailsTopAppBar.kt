package pl.farmaprom.trainings.contactsapp.contacts.presentation.detail

import androidx.compose.animation.animateColorAsState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import pl.farmaprom.trainings.contactsapp.ui.theme.ContactsAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactDetailsTopAppBar(
    isFavourite: Boolean,
    onNavigateBackClick: () -> Unit,
    onFavouriteClick: (Boolean) -> Unit
) {
    TopAppBar(
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
        title = {},
        navigationIcon = {
            IconButton(
                modifier = Modifier,
                onClick = {
                    onNavigateBackClick.invoke()
                }
            ) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            }
        },
        actions = {
            IconButton(
                modifier = Modifier,
                onClick = {
                    onFavouriteClick.invoke(!isFavourite)
                }
            ) {
                val color = animateColorAsState(targetValue = getFavouriteIconColor(isFavourite))
                Icon(imageVector = Icons.Filled.Star, tint = color.value, contentDescription = null)
            }
        }
    )
}

@Composable
private fun getFavouriteIconColor(isFavourite: Boolean) =
    if (isFavourite) MaterialTheme.colorScheme.onBackground else MaterialTheme.colorScheme.background

@Preview(showBackground = true)
@Composable
fun ContactDetailsTopAppBarPreview() {
    ContactsAppTheme {
        ContactDetailsTopAppBar(
            isFavourite = true,
            onNavigateBackClick = { },
            onFavouriteClick = {}
        )
    }
}
