public class Pesanan {
    private Menu menu;
    private int jumlah;
    private int jumlahGratis; // Jumlah gratisan dari promo BOGO

    public Pesanan(Menu menu, int jumlah) {
        this.menu = menu;
        this.jumlah = jumlah;
        this.jumlahGratis = 0; // Inisialisasi jumlah gratisan
    }

    public Menu getMenu() {
        return this.menu;
    }

    public int getJumlah() {
        return this.jumlah;
    }

    public int getJumlahGratis() {
        return this.jumlahGratis;
    }

    public boolean setJumlah(int jumlah) {
        if (jumlah <= 0)
            return false;
        this.jumlah = jumlah;
        return true;
    }

    public boolean setJumlahGratis(int jumlahGratis) {
        if (jumlahGratis <= 0)
            return false;
        this.jumlahGratis = jumlahGratis;
        return true;
    }

    public long hitungTotalHarga() {
        return (menu.getHargaMenu() * jumlah);
    }
}
