package pl.farmaprom.trainings.contactsapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pl.farmaprom.trainings.contactsapp.ui.theme.ContactsAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ContactsAppTheme {
                // A surface container using the 'background' color from the theme
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { padding ->
                    Greeting(modifier = Modifier.padding(padding), "Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(
    modifier: Modifier,
    name: String
) {
    Row(
        modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Top
    ) {
        Text(
            text = "Hello $name!",
            style = MaterialTheme.typography.headlineLarge
        )
        Text(
            modifier = modifier.clickable { },
            text = "Having fun?"
        )
    }
}

//@Preview(showBackground = true, device = Devices.NEXUS_5)
//@Composable
//fun DefaultPreview() {
//    ContactsAppTheme {
//        Greeting(modifier = Modifier, "Android")
//    }
//}

@Preview(showBackground = true, device = Devices.PIXEL_7_PRO)
@Composable
fun DefaultPreviewPixel7XL() {
    ContactsAppTheme {
        Greeting(modifier = Modifier, "Android")
    }
}
