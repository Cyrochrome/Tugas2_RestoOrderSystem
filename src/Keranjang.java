import java.util.Arrays;

public class Keranjang {
    private Pesanan[] daftarPesanan;

    public Keranjang() {
        this.daftarPesanan = new Pesanan[0];
    }

    public Pesanan[] getDaftarPesanan() {
        return this.daftarPesanan;
    }

    public Pesanan getPesananByIndex(int index) {
        if (index < 0 || index >= this.daftarPesanan.length) {
            return null;
        }
        return this.daftarPesanan[index];
    }

    public Pesanan[] getDaftarPesananByKategori(Kategori kategori) {

        int count = 0;
        for (Pesanan pesanan : this.daftarPesanan) {
            if (pesanan.getMenu().getKategoriMenu() == kategori) {
                count++;
            }
        }

        Pesanan[] daftarPesananFiltered = new Pesanan[count];

        int index = 0;
        for (Pesanan pesanan : this.daftarPesanan) {
            if (pesanan.getMenu().getKategoriMenu() == kategori) {
                daftarPesananFiltered[index] = pesanan;
                index++;
            }
        }

        return daftarPesananFiltered;
    }

    public void tambahPesanan(Menu menu, int jumlah) {
        Pesanan[] daftarPesananBaru = Arrays.copyOf(this.daftarPesanan, this.daftarPesanan.length + 1);
        daftarPesananBaru[this.daftarPesanan.length] = new Pesanan(menu, jumlah);
        this.daftarPesanan = daftarPesananBaru;
    }

    public long hitungSubtotal() {
        long subtotal = 0;
        for (Pesanan pesanan : this.daftarPesanan) {
            subtotal += pesanan.hitungTotalHarga();
        }
        return subtotal;
    }

    public void renderKeranjang(boolean isStruk) {
        System.out.println("\n=== KERANJANG PESANAN ===");
        System.out.println("!----------------------------------------------------!");

        if (this.daftarPesanan.length == 0) {
            System.out.println("Keranjang Anda masih kosong.");
            System.out.println("!----------------------------------------------------!");
            return;
        }

        int nomor = 1;
        for (Pesanan pesanan : this.daftarPesanan) {

            System.out.println(nomor + ". " + pesanan.getMenu().getNamaMenu());

            System.out.println("\tHarga Total: Rp " + pesanan.getMenu().getHargaMenu()
                    + " * " + pesanan.getJumlah() + " : Rp " + pesanan.hitungTotalHarga());

            if (pesanan.getJumlahGratis() > 0) {
                System.out.println("\t(Gratis BOGO: " + pesanan.getJumlahGratis() + " item)");
            }
            nomor++;
        }

        System.out.println("!----------------------------------------------------!");

        if (!isStruk) {
            System.out.println("Subtotal Sementara: Rp " + hitungSubtotal());
            System.out.println("!----------------------------------------------------!");
        }
    }

    public void kosongkanKeranjang() {
        this.daftarPesanan = new Pesanan[0];
    }
}
