import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean isAplikasiJalan = true;

        Scanner scanner = new Scanner(System.in);

        PengelolaMenu pengelolaMenu = new PengelolaMenu();

        System.out.println("Selamat Datang di Cafe Atas Tebing!~~~");

        while (isAplikasiJalan) {
            System.out.println("\n=== MENU UTAMA ===");
            System.out.println("!----------------------------------------------------!");
            System.out.println("1. Masuk sebagai Pelanggan (Pesan Menu)");
            System.out.println("2. Masuk sebagai Pemilik Restoran (Panel Admin)");
            System.out.println("3. Keluar Aplikasi");
            System.out.println("!----------------------------------------------------!");
            System.out.print("Pilih menu (1/2/3): ");

            String pilihanMenu = scanner.nextLine();

            switch (pilihanMenu) {
                case "1":
                    prosesPelanggan(scanner, pengelolaMenu);
                    break;
                case "2":

                    break;
                case "3":
                    System.out.println("Mematikan sistem... Terima kasih!");
                    isAplikasiJalan = false; // Memutus loop utama
                    break;
                default:
                    System.out.println("Input tidak valid! Silakan pilih 1, 2, atau 3.");
                    break;
            }
        }
        scanner.close();
    }

    public static void prosesPelanggan(Scanner scanner, PengelolaMenu pengelolaMenu) {
        Keranjang keranjang = new Keranjang();

        final int PAJAK = 10; // 10% dari total harga
        final int DISKON1 = 10; // 10% dari total harga jika total harga > 100.000
        final long DISKON1_MIN = 100000; // minimal total harga untuk diskon 10%
        final long BOGO_MIN = 50000; // minimal total harga untuk dapat promo BOGO
        final long BIAYA_LAYANAN = 20000; // biaya layanan

        System.out.println("Selamat Datang Pembeli Tercinta di Cafe Atas Tebing!~~~");

        prosesInputMenu(scanner, pengelolaMenu, keranjang);

        final long SUBTOTAL = keranjang.hitungSubtotal();
        long nilaiMurniTransaksi = SUBTOTAL;

        long nominalDiskon = SUBTOTAL > DISKON1_MIN ? SUBTOTAL * DISKON1 / 100 : 0;
        nilaiMurniTransaksi -= nominalDiskon;

        long dasarPengenaanPajak = nilaiMurniTransaksi + BIAYA_LAYANAN; 
        long nominalPajak = SUBTOTAL * PAJAK / 100;
        long totalAkhir = dasarPengenaanPajak + nominalPajak;

        

    }

    private static void prosesInputMenu(Scanner scanner, PengelolaMenu pengelolaMenu, Keranjang keranjang) {
        boolean isLanjutInput = true;

        Menu[] daftarMenuMakanan = pengelolaMenu.getDaftarMenuByKategori(Kategori.MAKANAN);
        Menu[] daftarMenuMinuman = pengelolaMenu.getDaftarMenuByKategori(Kategori.MINUMAN);

        while (isLanjutInput) {
            System.out.println("Silahkan pilih kategori Daftar Menu (1 atau 2):");
            System.out.println("!----------------------------------------------------!");
            System.out.println("1. MAKANAN");
            System.out.println("2. MINUMAN");
            System.out.println("!----------------------------------------------------!");

            String pilihanKategori = scanner.nextLine();

            Menu[] targetArray;
            String labelKategori;

            switch (pilihanKategori) {
                case "1":
                    targetArray = daftarMenuMakanan;
                    labelKategori = "MAKANAN";
                    break;
                case "2":
                    targetArray = daftarMenuMinuman;
                    labelKategori = "MINUMAN";
                    break;
                default:
                    System.out.println("Input tidak valid! Silakan pilih 1 atau 2.");
                    continue;
            }

            renderDaftarMenu(targetArray, labelKategori);
            keranjang.renderKeranjang(false);

            int pilihan = 0;
            boolean isMenuValid = false;

            while (!isMenuValid) {
                System.out.print("Silahkan pilih Menu (1-" + targetArray.length + "): ");

                if (!scanner.hasNextInt()) {
                    System.out.println("Error: Masukkan angka!");
                    scanner.next(); // Buang input ampas
                    continue;
                }

                pilihan = scanner.nextInt();

                // Validasi batas rentang dinamis
                if (pilihan < 1 || pilihan > targetArray.length) {
                    System.out.println("Error: Pilihan di luar rentang!");
                    continue;
                }
                isMenuValid = true;
            }
            scanner.nextLine(); // Sapu sisa "Enter" dari nextInt()

            Menu menuYangDipilih = targetArray[pilihan - 1];

            int qty = 0;
            boolean isQtyValid = false;
            while (!isQtyValid) {
                System.out.print("Masukkan jumlah pesanan untuk " + menuYangDipilih.getNamaMenu() + ": ");

                if (!scanner.hasNextInt()) {
                    System.out.println("Error: Masukkan angka bulat!");
                    scanner.next();
                    continue;
                }

                qty = scanner.nextInt();
                if (qty <= 0) {
                    System.out.println("Error: Jumlah minimal 1!");
                    continue;
                }
                isQtyValid = true;
            }
            scanner.nextLine(); // Sapu sisa "Enter" lagi

            keranjang.tambahPesanan(menuYangDipilih, qty);

            System.out
                    .println(">> Berhasil menambahkan " + qty + " " + menuYangDipilih.getNamaMenu() + " ke keranjang!");

            keranjang.renderKeranjang(false);

            int lanjut = -1;
            boolean isLanjutValid = false;

            while (!isLanjutValid) {
                System.out.print("\nTambah pesanan lagi? (1 = Ya, 0 = Tidak): ");

                if (!scanner.hasNextInt()) {
                    System.out.println("Error: Masukkan angka 1 atau 0!");
                    scanner.next(); // Buang input ampas
                    continue;
                }

                lanjut = scanner.nextInt();

                if (lanjut != 1 && lanjut != 0) {
                    System.out.println("Error: Pilihan hanya 1 atau 0!");
                    continue;
                }
                isLanjutValid = true;
            }
            scanner.nextLine();

            if (lanjut == 0) {
                isLanjutInput = false; // Menghentikan loop pemesanan utama
            }
        }
    }

    private static void renderDaftarMenu(Menu[] targetArray, String namaKategori) {
        System.out.println("\n=== DAFTAR MENU " + namaKategori + " ===");
        System.out.println("!----------------------------------------------------!");

        int nomor = 1;
        for (Menu menu : targetArray) {
            System.out.printf("%d. %-28s : Rp %,d\n", nomor, menu.getNamaMenu(), menu.getHargaMenu());
            nomor++;
        }

        System.out.println("!----------------------------------------------------!");
    }
}
