# Sistem Pesanan Restoran CLI (Tugas 1 - Pemrograman Berbasis Desktop)

Aplikasi berbasis terminal (CLI) sederhana yang ditulis dalam Java untuk mensimulasikan sistem pemesanan kasir di "Cafe Atas Tebing". Proyek ini dibuat untuk memenuhi Tugas Praktik 1 mata kuliah **Pemrograman Berbasis Desktop (MSIM4208)** di Universitas Terbuka.

## 👨‍💻 Informasi Mahasiswa

- **Nama:** Marshalinas Yustiawan
- **NIM:** 052817633
- **Program Studi:** Sistem Informasi
- **Mata Kuliah:** Pemrograman Berbasis Desktop

## ✨ Fitur Utama

- **Katalog Menu:** Menampilkan daftar menu makanan dan minuman secara terstruktur menggunakan tipe data `Enum` untuk menjaga integritas kategori.
- **Sistem Keranjang:** Memungkinkan pelanggan memesan hingga maksimal 4 item berbeda dalam satu transaksi.
- **Validasi Input Ketat:** Mencegah program _crash_ (seperti `InputMismatchException`) saat pengguna memasukkan tipe data yang salah atau di luar batas yang ditentukan.
- **Perhitungan Otomatis:** Menghitung nilai murni transaksi, penambahan Pajak (10%), dan Biaya Layanan tetap (Rp 20.000).
- **Sistem Diskon Bersyarat:**
  - Potongan 10% otomatis jika total pesanan melebihi Rp 100.000.
- **Promo Buy One Get One (BOGO):**
  - Deteksi otomatis untuk memberikan 1 item minuman gratis tambahan jika subtotal pesanan melebihi Rp 50.000 dan terdapat item minuman di dalam keranjang.
- **Cetak Struk:** Menampilkan rincian akhir pesanan secara rapi di terminal.

## 🛠️ Struktur Kode & Constraint Tugas

Proyek ini dibangun dengan batasan khusus dari spesifikasi tugas, yaitu **meminimalisir penggunaan struktur pengulangan (_looping_ seperti `for` atau `while`)**.

Sebagai solusinya, kode dirancang menggunakan:

1. **Unrolled Array Processing:** Pengecekan isi keranjang dan kalkulasi subtotal dilakukan secara manual berurutan menggunakan paradigma _functional-style_ dan _cascading if_ (index 0 hingga 3).
2. **Modular Method:** Eksekusi dipecah ke dalam berbagai _method_ khusus (`hitungPajak`, `promoBogo`, `tampilkanKeranjang`) untuk menjaga `Main.java` tetap bersih dari _spaghetti code_.
3. **Isolated While-Loop:** Pengulangan _hanya_ diizinkan dan dilokalisasi pada bagian validasi `Scanner` untuk memastikan keamanan input data.

## 🚀 Cara Menjalankan Program

Pastikan Anda sudah menginstal **Java Development Kit (JDK)** di sistem operasi Anda.

1. Buka terminal atau _command prompt_.
2. Arahkan direktori ke folder tempat file _source code_ disimpan.
3. _Compile_ seluruh file Java:

```bash
   javac Main.java Menu.java Pesanan.java Kategori.java
```

5. Jalankan program utama:

```bash
java Main
```

## 📺 Link Video Presentasi

Demonstrasi program dan penjelasan _source code_ dapat dilihat pada tautan YouTube berikut:
[Tugas 1 Pemrograman Berbasis Desktop - Resto Order System](https://youtu.be/jIMNfBv9w8U)

`
