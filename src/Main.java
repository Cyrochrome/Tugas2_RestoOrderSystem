import java.util.Scanner;

// Nama                 : Marshalinas Yustiawan      
// NIM                  : 052817633
// Program studi        : Sistem Informasi
// Mata kuliah          : Pemrograman Berbasis Desktop

public class Main {
    public static void main(String[] args) {
        // Inisialisasi Scanner untuk membaca input user
        Scanner scanner = new Scanner(System.in);

        // Inisialisasi daftar menu
        Menu[] daftarMenu = new Menu[8];
        daftarMenu[0] = new Menu("Egg & Butter Toast", 20000, Kategori.MAKANAN);
        daftarMenu[1] = new Menu("Double Cheeseburger", 30000, Kategori.MAKANAN);
        daftarMenu[2] = new Menu("Margherita Pizza (1 slice)", 25000, Kategori.MAKANAN);
        daftarMenu[3] = new Menu("Miso Ramen", 27000, Kategori.MAKANAN);
        daftarMenu[4] = new Menu("Iced Tea", 12000, Kategori.MINUMAN);
        daftarMenu[5] = new Menu("Iced Latte", 18000, Kategori.MINUMAN);
        daftarMenu[6] = new Menu("Iced Black Coffee", 16000, Kategori.MINUMAN);
        daftarMenu[7] = new Menu("Iced Cappuccino", 17000, Kategori.MINUMAN);

        // Inisialisasi keranjang pesanan (menggunakan Class Pesanan)
        Pesanan[] keranjang = new Pesanan[4];

        final int PAJAK = 10; // 10% dari total harga
        final int DISKON1 = 10; // 10% dari total harga jika total harga > 100.000
        final long DISKON1_MIN = 100000; // minimal total harga untuk diskon 10%
        final long BOGO_MIN = 50000; // minimal total harga untuk dapat promo BOGO
        final long BIAYA_LAYANAN = 20000; // biaya layanan

        System.out.println("Selamat Datang di Cafe Atas Tebing!~~~");

        prosesInputMenu(scanner, daftarMenu, keranjang);

        prosesPerhitunganDanCetakStruk(scanner, keranjang, DISKON1, DISKON1_MIN, BIAYA_LAYANAN, PAJAK, BOGO_MIN);
    }

    /**
     * Method ini digunakan untuk memproses input menu dari user.
     * Sengaja dipisah agar method main tidak terlalu panjang.
     */
    private static void prosesInputMenu(Scanner scanner, Menu[] daftarMenu, Pesanan[] keranjang) {
        boolean lanjutInput = true;

        if (lanjutInput)
            lanjutInput = inputMenu(scanner, 0, keranjang, daftarMenu);
        if (lanjutInput)
            lanjutInput = inputMenu(scanner, 1, keranjang, daftarMenu);
        if (lanjutInput)
            lanjutInput = inputMenu(scanner, 2, keranjang, daftarMenu);
        if (lanjutInput)
            inputMenu(scanner, 3, keranjang, daftarMenu);
    }

    /**
     * Method ini digunakan untuk menampilkan daftar menu yang tersedia.
     */
    private static void tampilkanMenu(Menu[] daftarMenu) {
        System.out.println("Silahkan pilih menu yang tersedia:");
        System.out.println("!----------------------------------------------------!");
        System.out.println("Makanan:");
        System.out.println("1. " + daftarMenu[0].getNamaMenu() + "\t\t: Rp " + daftarMenu[0].getHargaMenu());
        System.out.println("2. " + daftarMenu[1].getNamaMenu() + "\t\t: Rp " + daftarMenu[1].getHargaMenu());
        System.out.println("3. " + daftarMenu[2].getNamaMenu() + "\t: Rp " + daftarMenu[2].getHargaMenu());
        System.out.println("4. " + daftarMenu[3].getNamaMenu() + "\t\t\t: Rp " + daftarMenu[3].getHargaMenu());
        System.out.println("Minuman:");
        System.out.println("5. " + daftarMenu[4].getNamaMenu() + "\t\t: Rp " + daftarMenu[4].getHargaMenu());
        System.out.println("6. " + daftarMenu[5].getNamaMenu() + "\t\t: Rp " + daftarMenu[5].getHargaMenu());
        System.out.println("7. " + daftarMenu[6].getNamaMenu() + "\t: Rp " + daftarMenu[6].getHargaMenu());
        System.out.println("8. " + daftarMenu[7].getNamaMenu() + "\t: Rp " + daftarMenu[7].getHargaMenu());
        System.out.println("!----------------------------------------------------!");
    }

    /**
     * Method ini digunakan untuk menampilkan isi keranjang pesanan.
     */
    public static void tampilkanKeranjang(Pesanan[] keranjang, boolean isStruk) {
        final long SUBTOTAL = hitungSubtotal(keranjang);

        System.out.println("\nKeranjang Pesanan:");
        System.out.println("!----------------------------------------------------!");

        if (keranjang[0] == null) {
            System.out.println("Keranjang Anda masih kosong.");
            System.out.println("!----------------------------------------------------!");
            return;
        }

        System.out.println("1. " + keranjang[0].menu.getNamaMenu() + " : " + keranjang[0].menu.getHargaMenu() + " x "
                + keranjang[0].jumlah + " = Rp " + keranjang[0].hitungTotalHarga());
        if (keranjang[0].jumlahGratis > 0) {
            System.out.println("\t (Gratis: " + keranjang[0].jumlahGratis + ")");
        }
        if (isStruk == false && keranjang[0] != null && keranjang[1] == null && keranjang[2] == null
                && keranjang[3] == null)
            System.out.println("Subtotal: Rp " + SUBTOTAL);
        if (keranjang[1] == null) {
            System.out.println("!----------------------------------------------------!");
            return;
        }

        System.out.println("2. " + keranjang[1].menu.getNamaMenu() + " : " + keranjang[1].menu.getHargaMenu() + " x "
                + keranjang[1].jumlah + " = Rp " + keranjang[1].hitungTotalHarga());
        if (keranjang[1].jumlahGratis > 0) {
            System.out.println("\t (Gratis: " + keranjang[1].jumlahGratis + ")");
        }
        if (isStruk == false && keranjang[0] != null && keranjang[1] != null && keranjang[2] == null
                && keranjang[3] == null)
            System.out.println("Subtotal: Rp " + SUBTOTAL);
        if (keranjang[2] == null) {
            System.out.println("!----------------------------------------------------!");
            return;
        }

        System.out.println("3. " + keranjang[2].menu.getNamaMenu() + " : " + keranjang[2].menu.getHargaMenu() + " x "
                + keranjang[2].jumlah + " = Rp " + keranjang[2].hitungTotalHarga());
        if (keranjang[2].jumlahGratis > 0) {
            System.out.println("\t (Gratis: " + keranjang[2].jumlahGratis + ")");
        }
        if (isStruk == false && keranjang[0] != null && keranjang[1] != null && keranjang[2] != null
                && keranjang[3] == null)
            System.out.println("Subtotal: Rp " + SUBTOTAL);
        if (keranjang[3] == null) {
            System.out.println("!----------------------------------------------------!");
            return;
        }

        System.out.println("4. " + keranjang[3].menu.getNamaMenu() + " : " + keranjang[3].menu.getHargaMenu() + " x "
                + keranjang[3].jumlah + " = Rp " + keranjang[3].hitungTotalHarga());
        if (keranjang[3].jumlahGratis > 0) {
            System.out.println("\t (Gratis: " + keranjang[3].jumlahGratis + ")");
        }
        if (isStruk == false)
            System.out.println("Subtotal: Rp " + SUBTOTAL);

        System.out.println("!----------------------------------------------------!");
    }

    private static void tampilkanKeranjang(Pesanan[] keranjang) {
        tampilkanKeranjang(keranjang, false);
    }

    /**
     * Method inputMenu ini dibuat untuk menghindari duplikasi kode input menu.
     */
    private static boolean inputMenu(Scanner scanner, int index, Pesanan[] keranjang, Menu[] daftarMenu) {
        int pilihan = 0;
        int qty = 0;
        boolean validInput = false;

        tampilkanMenu(daftarMenu);

        System.out.print("\nMasukkan nomor menu ke-" + (index + 1) + " (1-8): ");
        while (!validInput) {
            if (!scanner.hasNextInt()) {
                System.out.print("Input tidak valid. Masukkan nomor menu (1-8): ");
                scanner.next();
                continue;
            }

            pilihan = scanner.nextInt();
            if (pilihan < 1 || pilihan > 8) {
                System.out.print("Input tidak valid. Masukkan nomor menu (1-8): ");
                continue;
            }
            validInput = true;
        }
        validInput = false;

        System.out.print("Masukkan jumlah pesanan: ");
        while (!validInput) {
            if (!scanner.hasNextInt()) {
                System.out.print("Input harus berupa angka bulat. Masukkan jumlah pesanan: ");
                scanner.next();
                continue;
            }

            qty = scanner.nextInt();
            if (qty <= 0) {
                System.out.print("Jumlah pesanan harus lebih dari 0. Masukkan jumlah pesanan: ");
                continue;
            }
            validInput = true;
        }
        validInput = false;

        keranjang[index] = new Pesanan(daftarMenu[pilihan - 1], qty);

        tampilkanKeranjang(keranjang);

        if (index == 3) {
            System.out.println("Keranjang sudah penuh, tidak bisa menambah pesanan lagi.");
            return false;
        }

        System.out.print("Tambah pesanan lagi? (1 = Ya, 0 = Tidak): ");
        while (!validInput) {
            if (!scanner.hasNextInt()) {
                System.out.print("Input tidak valid. Tambah pesanan lagi? (1 = Ya, 0 = Tidak): ");
                scanner.next();
                continue;
            }
            int lanjut = scanner.nextInt();
            if (lanjut != 1 && lanjut != 0) {
                System.out.print("Input tidak valid. Tambah pesanan lagi? (1 = Ya, 0 = Tidak): ");
                continue;
            }
            if (lanjut == 0)
                return false;
            validInput = true;
        }

        return true;
    }

    /**
     * Perhitungan SUBTOTAL dibikin dengan paradigma functional programming.
     */
    private static long hitungSubtotal(Pesanan[] keranjang) {
        return (keranjang[0] != null ? keranjang[0].hitungTotalHarga() : 0)
                + (keranjang[1] != null ? keranjang[1].hitungTotalHarga() : 0)
                + (keranjang[2] != null ? keranjang[2].hitungTotalHarga() : 0)
                + (keranjang[3] != null ? keranjang[3].hitungTotalHarga() : 0);
    }

    /**
     * Perhitungan promo BOGO dibikin dengan paradigma functional programming.
     */
    private static void promoBogo(Scanner scanner, long BOGO_MIN, long SUBTOTAL, Pesanan[] keranjang) {
        if (SUBTOTAL <= BOGO_MIN)
            return;
        Pesanan[] minumanDalamKeranjang = new Pesanan[4];

        if (keranjang[0] != null && keranjang[0].menu.getKategoriMenu() == Kategori.MINUMAN) {
            minumanDalamKeranjang[0] = keranjang[0];
        }
        if (keranjang[1] != null && keranjang[1].menu.getKategoriMenu() == Kategori.MINUMAN) {
            if (minumanDalamKeranjang[0] == null) {
                minumanDalamKeranjang[0] = keranjang[1];
            } else {
                minumanDalamKeranjang[1] = keranjang[1];
            }
        }
        if (keranjang[2] != null && keranjang[2].menu.getKategoriMenu() == Kategori.MINUMAN) {
            if (minumanDalamKeranjang[0] == null) {
                minumanDalamKeranjang[0] = keranjang[2];
            } else if (minumanDalamKeranjang[1] == null) {
                minumanDalamKeranjang[1] = keranjang[2];
            } else {
                minumanDalamKeranjang[2] = keranjang[2];
            }
        }
        if (keranjang[3] != null && keranjang[3].menu.getKategoriMenu() == Kategori.MINUMAN) {
            if (minumanDalamKeranjang[0] == null) {
                minumanDalamKeranjang[0] = keranjang[3];
            } else if (minumanDalamKeranjang[1] == null) {
                minumanDalamKeranjang[1] = keranjang[3];
            } else if (minumanDalamKeranjang[2] == null) {
                minumanDalamKeranjang[2] = keranjang[3];
            } else {
                minumanDalamKeranjang[3] = keranjang[3];
            }
        }

        if (minumanDalamKeranjang[0] == null && minumanDalamKeranjang[1] == null && minumanDalamKeranjang[2] == null
                && minumanDalamKeranjang[3] == null)
            return;

        tampilkanMenuBogo(minumanDalamKeranjang);
        int pilihanGratis = pilihGratisan(scanner, minumanDalamKeranjang);
        minumanDalamKeranjang[pilihanGratis].jumlahGratis++;
    }

    /**
     * Method ini digunakan untuk meminta input user untuk memilih menu gratisan.
     */
    private static int pilihGratisan(Scanner scanner, Pesanan[] minumanDalamKeranjang) {
        System.out.print("Masukkan nomor menu yang ingin Anda dapatkan gratis (1-4): ");
        int pilihanGratis = 0;
        boolean validInput = false;

        while (!validInput) {
            if (!scanner.hasNextInt()) {
                System.out.print("Input tidak valid. Masukkan nomor menu (1-4): ");
                scanner.next();
                continue;
            }

            pilihanGratis = scanner.nextInt();
            if (pilihanGratis < 1 || pilihanGratis > 4) {
                System.out.print("Input tidak valid. Masukkan nomor menu (1-4): ");
                continue;
            }
            if (minumanDalamKeranjang[pilihanGratis - 1] == null) {
                System.out.print("Slot menu tersebut kosong. Masukkan nomor menu (1-4): ");
                continue;
            }
            if (minumanDalamKeranjang[pilihanGratis - 1].menu.getKategoriMenu() != Kategori.MINUMAN) {
                System.out.print("Menu tersebut bukan minuman. Masukkan nomor menu (1-4): ");
                continue;
            }
            validInput = true;
        }
        return pilihanGratis - 1;
    }

    /**
     * Method ini digunakan untuk menampilkan menu yang bisa dipilih untuk claim
     * promo BOGO.
     */
    private static void tampilkanMenuBogo(Pesanan[] minumanDalamKeranjang) {
        System.out.println(
                "Selamat! Anda mendapatkan promo BOGO untuk minuman. Silahkan pilih minuman yang ada di keranjang yang ingin Anda dapatkan gratis:");
        System.out.println("!----------------------------------------------------!");

        if (minumanDalamKeranjang[0] != null && minumanDalamKeranjang[0].menu.getKategoriMenu() == Kategori.MINUMAN)
            System.out.println("1. " + minumanDalamKeranjang[0].menu.getNamaMenu());
        if (minumanDalamKeranjang[1] != null && minumanDalamKeranjang[1].menu.getKategoriMenu() == Kategori.MINUMAN)
            System.out.println("2. " + minumanDalamKeranjang[1].menu.getNamaMenu());
        if (minumanDalamKeranjang[2] != null && minumanDalamKeranjang[2].menu.getKategoriMenu() == Kategori.MINUMAN)
            System.out.println("3. " + minumanDalamKeranjang[2].menu.getNamaMenu());
        if (minumanDalamKeranjang[3] != null && minumanDalamKeranjang[3].menu.getKategoriMenu() == Kategori.MINUMAN)
            System.out.println("4. " + minumanDalamKeranjang[3].menu.getNamaMenu());

        System.out.println("!----------------------------------------------------!");
    }

    /**
     * Perhitungan diskon dibikin dengan paradigma functional programming.
     */
    private static long hitungDiskon(int DISKON1, long DISKON1_MIN, long SUBTOTAL) {
        if (SUBTOTAL <= DISKON1_MIN)
            return 0;
        return SUBTOTAL * DISKON1 / 100;

    }

    /**
     * Perhitungan pajak dibikin dengan paradigma functional programming.
     */
    private static long hitungPajak(int PAJAK, long SUBTOTAL) {
        return SUBTOTAL * PAJAK / 100;
    }

    private static void prosesPerhitunganDanCetakStruk(Scanner scanner, Pesanan[] keranjang, int DISKON1,
            long DISKON1_MIN, long BIAYA_LAYANAN, int PAJAK, long BOGO_MIN) {
            
        final long SUBTOTAL = hitungSubtotal(keranjang);
        long nilaiMurniTransaksi = SUBTOTAL;
        
        long nominalDiskon = hitungDiskon(DISKON1, DISKON1_MIN, SUBTOTAL);
        if (nominalDiskon > 0) {
            nilaiMurniTransaksi -= nominalDiskon; 
        }
            
        
        long dasarPengenaanPajak = nilaiMurniTransaksi + BIAYA_LAYANAN; 
        long nominalPajak = hitungPajak(PAJAK, dasarPengenaanPajak);
        long totalAkhir = dasarPengenaanPajak + nominalPajak;

        promoBogo(scanner, BOGO_MIN, SUBTOTAL, keranjang);

        tampilkanStruk(keranjang, SUBTOTAL, nominalDiskon, nominalPajak, BIAYA_LAYANAN, 
                       nilaiMurniTransaksi, dasarPengenaanPajak, totalAkhir);
    }

    private static void tampilkanStruk(Pesanan[] keranjang, long SUBTOTAL, long nominalDiskon, long nominalPajak,
            long BIAYA_LAYANAN, long nilaiMurniTransaksi, long dasarPengenaanPajak, long totalAkhir) {
            
        System.out.println("\nStruk Pesanan:");
        System.out.println("!----------------------------------------------------!");
        
        // Lempar parameter TRUE agar "Subtotal" per menu tidak nge-spam di dalam struk
        tampilkanKeranjang(keranjang, true);
        
        // Alignment \t diratakan pada ketukan ke-32 (biar lurus sempurna kayak print kasir)
        System.out.println("Subtotal\t\t\t: Rp " + SUBTOTAL);
        System.out.println("Diskon\t\t\t\t: - Rp " + nominalDiskon);
        System.out.println("Nilai Transaksi Murni\t\t: Rp " + nilaiMurniTransaksi);
        System.out.println("Biaya Layanan\t\t\t: + Rp " + BIAYA_LAYANAN);
        System.out.println("Dasar Pengenaan Pajak\t\t: Rp " + dasarPengenaanPajak);
        System.out.println("Pajak\t\t\t\t: + Rp " + nominalPajak);
        System.out.println("!----------------------------------------------------!");
        System.out.println("Total Akhir\t\t\t: Rp " + totalAkhir);
        System.out.println("!----------------------------------------------------!");
    }
}