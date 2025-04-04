package pl.farmaprom.trainings.contactsapp.contacts

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pl.farmaprom.trainings.contactsapp.R
import pl.farmaprom.trainings.contactsapp.ui.theme.ContactsAppTheme


@Composable
fun SearchBoxTopAppBar(
    modifier: Modifier = Modifier,
    initialText: String = "Ania"
) {
    var searchText by remember { mutableStateOf(initialText) }

    Row(
        modifier = modifier.background(MaterialTheme.colorScheme.primary)
    ) {

        val context = LocalContext.current
        TextField(
            shape = RoundedCornerShape(30),
            modifier = modifier
                .padding(8.dp)
                .fillMaxWidth()
                .height(48.dp),
            value = searchText,
            textStyle = TextStyle.Default.copy(fontSize = 16.sp),
            onValueChange = { searchText = it },
            leadingIcon = {
                Icon(
                    modifier = modifier.clickable {
                        Log.i("SearchBoxTopAppBar", "Search clicked")
                        Toast.makeText(
                            context,
                            context.getString(R.string.search_clicked_with_text, searchText),
                            Toast.LENGTH_LONG
                        ).show()
                    },
                    imageVector = Icons.Filled.Search,
                    contentDescription = null
                )
            }
        )
    }

}


@Preview(showBackground = true)
@Composable
private fun SearchBoxTopAppBarPreview() {
    ContactsAppTheme {
        Column {
            SearchBoxTopAppBar()
            SearchBoxTopAppBar()
        }
    }
}
