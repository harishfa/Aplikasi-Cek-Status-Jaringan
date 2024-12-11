package com.example.cekkoneksijaringan

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkInfo
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var networkChangeReceiver: NetworkChangeReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Menginisialisasi elemen UI
        val networkConnect: View = findViewById(R.id.networkConnect)   // Halaman Connected
        val networkError: View = findViewById(R.id.networkError)       // Halaman Disconnected

        // Membuat objek NetworkChangeReceiver untuk memantau perubahan jaringan
        networkChangeReceiver = NetworkChangeReceiver(networkConnect, networkError)

        // Mendaftarkan receiver untuk menerima perubahan status jaringan
        val intentFilter = android.content.IntentFilter()
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE")
        registerReceiver(networkChangeReceiver, intentFilter)
    }

    override fun onDestroy() {
        super.onDestroy()
        // Membatalkan pendaftaran receiver saat activity dihancurkan
        unregisterReceiver(networkChangeReceiver)
    }
}

class NetworkChangeReceiver(
    private val networkConnect: View,
    private val networkError: View
) : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo

        if (activeNetwork != null && activeNetwork.isConnected) {
            // Jaringan terhubung
            if (activeNetwork.type == ConnectivityManager.TYPE_WIFI) {
                showNetworkStatus(context, "WiFi terhubung")
            } else if (activeNetwork.type == ConnectivityManager.TYPE_MOBILE) {
                showNetworkStatus(context, "Data seluler terhubung")
            }
            // Menyembunyikan halaman error dan menampilkan halaman connected
            networkConnect.visibility = View.VISIBLE
            networkError.visibility = View.GONE
        } else {
            // Jaringan terputus
            showNetworkStatus(context, "Tidak ada koneksi jaringan")
            // Menyembunyikan halaman connected dan menampilkan halaman error
            networkConnect.visibility = View.GONE
            networkError.visibility = View.VISIBLE
        }
    }

    private fun showNetworkStatus(context: Context, message: String) {
        // Menampilkan Toast sebagai pop-up
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}
