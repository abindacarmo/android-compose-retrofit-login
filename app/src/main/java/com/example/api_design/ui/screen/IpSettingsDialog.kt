package com.example.api_design.ui.screen

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun IpSettingsDialog(onDismiss: () -> Unit, onSave: (String) -> Unit, context: Context) {
    val prefs = context.getSharedPreferences("api_prefs", Context.MODE_PRIVATE)
    val currentIp = prefs.getString("server_ip", "10.67.116.88") ?: "10.67.116.88"
    var ipText by remember { mutableStateOf(currentIp) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Configura Server IP") },
        text = {
            Column {
                Text("Prena IP husi laptop (Ethernet/Wi-Fi):")
                OutlinedTextField(
                    value = ipText,
                    onValueChange = { ipText = it },
                    placeholder = { Text("Ex: 10.67.116.XX") },
                    modifier = Modifier.padding(top = 8.dp)
                )
                Text(
                    "Porta default mak 80",
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        },
        confirmButton = {
            TextButton(onClick = { onSave(ipText) }) {
                Text("Save")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Kansela")
            }
        }
    )
}
