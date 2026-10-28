import java.util.Arrays;

public class Keranjang {
    private Pesanan[] daftarPesanan;

    public Keranjang() {
        this.daftarPesanan = new Pesanan[0];
    }

    public Pesanan[] lihatDaftarPesanan() {
        return this.daftarPesanan;
    }

    public Pesanan lihatPesananByIndex(int index) {
        if (index < 0 || index >= this.daftarPesanan.length) {
            return null;
        }
        return this.daftarPesanan[index];
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

    public void kosongkanKeranjang() {
        this.daftarPesanan = new Pesanan[0];
    }
}
