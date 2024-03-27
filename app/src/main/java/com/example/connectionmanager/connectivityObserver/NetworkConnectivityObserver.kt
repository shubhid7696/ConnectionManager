package com.example.connectionmanager.connectivityObserver

import android.content.Context
import android.net.ConnectivityManager
import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch

class NetworkConnectivityObserver (
    private val context: Context
) : ConnectivityObserver {
    private val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    override fun observeNetwork(): Flow<ConnectivityObserver.Status> {
       return callbackFlow {
           val callback = object : ConnectivityManager.NetworkCallback() {
               override fun onAvailable(network: Network) {
                   super.onAvailable(network)
                   launch { send(ConnectivityObserver.Status.Availiable) }
               }

               override fun onLosing(network: Network, maxMsToLive: Int) {
                   super.onLosing(network, maxMsToLive)
                   launch { send(ConnectivityObserver.Status.Looseing) }
               }

               override fun onLost(network: Network) {
                   super.onLost(network)
                   launch { send(ConnectivityObserver.Status.Lost) }
               }

               override fun onUnavailable() {
                   super.onUnavailable()
                   launch { send(ConnectivityObserver.Status.Unavailiable) }
               }

               override fun onCapabilitiesChanged(
                   network: Network,
                   networkCapabilities: NetworkCapabilities
               ) {
                   super.onCapabilitiesChanged(network, networkCapabilities)
               }

               override fun onLinkPropertiesChanged(
                   network: Network,
                   linkProperties: LinkProperties
               ) {
                   super.onLinkPropertiesChanged(network, linkProperties)
               }

               override fun onBlockedStatusChanged(network: Network, blocked: Boolean) {
                   super.onBlockedStatusChanged(network, blocked)
               }

           }
           connectivityManager.registerDefaultNetworkCallback(callback);

           awaitClose {
               connectivityManager.unregisterNetworkCallback(callback);
           }
       }
    }
}