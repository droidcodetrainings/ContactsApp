package pl.farmaprom.trainings.contactsapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pl.farmaprom.trainings.contactsapp.R
import pl.farmaprom.trainings.contactsapp.ui.theme.ContactsAppTheme

@Composable
fun ThemeComposables(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Switch(
            checked = true,
            onCheckedChange = {},
            colors = SwitchDefaults.colors(
                checkedThumbColor = MaterialTheme.colorScheme.primaryContainer,
            )
        )

        Image(
            painter = painterResource(id = R.drawable.logo),
            modifier = Modifier.fillMaxWidth(),
            contentDescription = null,
        )

        Spacer(Modifier.size(24.dp))

        Checkbox(checked = true, onCheckedChange = {})

        Spacer(Modifier.size(24.dp))

        Slider(value = .6F, onValueChange = {})

        Spacer(Modifier.size(24.dp))

        FloatingActionButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = null
            )
        }

        Spacer(Modifier.size(24.dp))

        CircularProgressIndicator(
            modifier = Modifier.width(64.dp),
            color = MaterialTheme.colorScheme.tertiary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
        )

        Spacer(Modifier.size(24.dp))

        CustomButton(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            onClick = { /*TODO*/ }
        ) {
            Text(
                text = "Button",
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}
@Composable
fun CustomButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        shape = CutCornerShape(70),
        onClick = onClick,
        modifier = modifier,
        content = content
    )
}

@Preview(
    showBackground = true,
    device = Devices.PIXEL_4_XL,
    name = "Pixel 4 XL"
)
@Composable
fun AppThemeComposablePixelPreview() {
    ContactsAppTheme {
        ThemeComposables()
    }
}