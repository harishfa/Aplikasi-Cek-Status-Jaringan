# Cek Koneksi Jaringan

Aplikasi Android sederhana untuk memantau status koneksi internet secara real-time. Aplikasi ini dapat mendeteksi apakah perangkat terhubung melalui WiFi, Data Seluler, atau tidak ada koneksi sama sekali.

## Fitur
- **Deteksi Real-time**: Menggunakan `BroadcastReceiver` untuk mendeteksi perubahan status jaringan secara langsung.
- **Notifikasi Toast**: Memberikan feedback instan kepada pengguna melalui pesan pop-up (Toast).
- **UI Dinamis**: Mengubah tampilan layar secara otomatis berdasarkan status koneksi (Halaman Terhubung/Halaman Error).

## Tampilan Aplikasi

Berikut adalah tampilan aplikasi dalam berbagai kondisi jaringan:

<table style="width: 100%; text-align: center;">
  <tr>
    <th>Jaringan WiFi Terhubung</th>
    <th>Jaringan Seluler Terhubung</th>
    <th>Jaringan Terputus</th>
  </tr>
  <tr>
    <td><img src="screenshot/Jaringan WiFi Terhubung.jpeg" width="200"></td>
    <td><img src="screenshot/Jaringan Seluler Terhubung.jpeg" width="200"></td>
    <td><img src="screenshot/Jaringan Terputus.jpeg" width="200"></td>
  </tr>
</table>

## Cara Kerja
Aplikasi menggunakan `NetworkChangeReceiver` yang didaftarkan pada `MainActivity`. Receiver ini mendengarkan intent `android.net.conn.CONNECTIVITY_CHANGE`. 
- Jika terhubung ke **WiFi**, aplikasi menampilkan pesan "WiFi terhubung".
- Jika terhubung ke **Data Seluler**, aplikasi menampilkan pesan "Data seluler terhubung".
- Jika **tidak ada koneksi**, aplikasi menampilkan pesan "Tidak ada koneksi jaringan" dan beralih ke tampilan error.

## Persyaratan
- Android Studio Bumblebee atau yang lebih baru.
- SDK Minimum: API 21 (Android 5.0 Lollipop).
- Bahasa Pemrograman: Kotlin.
