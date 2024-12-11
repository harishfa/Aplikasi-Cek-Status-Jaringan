package com.example.cekkoneksijaringan

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cekkoneksijaringan.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Menginisialisasi elemen UI
        val networkConnect: View = findViewById(R.id.networkConnect)   // Halaman Connected
        val networkError: View = findViewById(R.id.networkError)       // Halaman Disconnected

        // Membuat objek NetworkConnection untuk memantau status jaringan
        val networkConnection = NetworkConnection(applicationContext)

        // Mengamati perubahan status koneksi jaringan
        networkConnection.observe(this) { isConnected ->
            if (isConnected) {
                // Jaringan terhubung
                Toast.makeText(this, "Jaringan Terhubung", Toast.LENGTH_SHORT).show()
                // Menyembunyikan halaman error dan menampilkan halaman connected
                networkConnect.visibility = View.VISIBLE
                networkError.visibility = View.GONE
            } else {
                // Jaringan terputus
                Toast.makeText(this, "Jaringan Terputus", Toast.LENGTH_SHORT).show()
                // Menyembunyikan halaman connected dan menampilkan halaman error
                networkConnect.visibility = View.GONE
                networkError.visibility = View.VISIBLE
            }
        }
    }
}
