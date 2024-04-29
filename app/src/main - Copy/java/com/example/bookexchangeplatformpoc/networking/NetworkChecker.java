//code adapted from: https://www.youtube.com/watch?v=2g3lWHd1lQ8

package com.example.bookplace.networking;

import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;

public class NetworkChecker {
    private ConnectivityManager connectivityManager;

    public NetworkChecker(ConnectivityManager connectivityManager) {
        this.connectivityManager = connectivityManager;
    }

    public void performAction(Runnable action) {
        if (hasValidInternetConnection()) {
            action.run();
        }
    }

    private boolean hasValidInternetConnection() {
        if (connectivityManager == null) {
            return false;
        }

        android.net.Network network = connectivityManager.getActiveNetwork();
        android.net.NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(network);

        if (capabilities == null) {
            return false;
        }

        return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN);

    }
}
