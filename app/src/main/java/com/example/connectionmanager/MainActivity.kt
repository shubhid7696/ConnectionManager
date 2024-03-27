package com.example.connectionmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.connectionmanager.connectivityObserver.ConnectivityObserver
import com.example.connectionmanager.connectivityObserver.NetworkConnectivityObserver
import com.example.connectionmanager.ui.theme.ConnectionManagerTheme

class MainActivity : ComponentActivity() {

    lateinit var connectionObserver : NetworkConnectivityObserver
    override fun onCreate(savedInstanceState: Bundle?) {
        connectionObserver = NetworkConnectivityObserver(applicationContext)
        super.onCreate(savedInstanceState)
        setContent {
            ConnectionManagerTheme {
                val status by connectionObserver.observeNetwork().collectAsState(initial = ConnectivityObserver.Status.Unavailiable)
Box(
    modifier = Modifier.fillMaxSize(),
    contentAlignment = Alignment.Center
){
    Text(text = "Network status: $status")
}
            }
        }
    }
}

