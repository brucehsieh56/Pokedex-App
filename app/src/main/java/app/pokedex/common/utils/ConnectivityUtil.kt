package app.pokedex.common.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

/**
 * Util to check network connection.
 *
 * https://medium.com/scalereal/observing-live-connectivity-status-in-jetpack-compose-way-f849ce8431c7
 * */

sealed class ConnectionState {
    object Available : ConnectionState()
    object Unavailable : ConnectionState()
}

val Context.currentConnectivityState: ConnectionState
    get() {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.getCurrentConnectivityState()
    }

private fun ConnectivityManager.getCurrentConnectivityState(): ConnectionState {
    val connected = allNetworks.any { network ->
        getNetworkCapabilities(network)
            ?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            ?: false
    }

    return if (connected) ConnectionState.Available else ConnectionState.Unavailable
}
