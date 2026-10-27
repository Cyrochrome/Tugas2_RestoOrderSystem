public class Pesanan {
    Menu menu;
    int jumlah;
    int jumlahGratis; // Jumlah gratisan dari promo BOGO

    public Pesanan(Menu menu, int jumlah) {
        this.menu = menu;
        this.jumlah = jumlah;
        this.jumlahGratis = 0; // Inisialisasi jumlah gratisan
    }

    public long hitungTotalHarga() {
        return (menu.hargaMenu * jumlah);
    }
}
