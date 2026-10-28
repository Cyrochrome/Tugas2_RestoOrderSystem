public class Menu {
    private String namaMenu;
    private long hargaMenu;
    private Kategori kategoriMenu;

    public Menu(String nama, long harga, Kategori kategori) {
        this.namaMenu = nama;
        this.hargaMenu = harga;
        this.kategoriMenu = kategori;
    }

    public String getNamaMenu() {
        return namaMenu;
    }

    public long getHargaMenu() {
        return hargaMenu;
    }

    public Kategori getKategoriMenu() {
        return kategoriMenu;
    }

    public boolean setNamaMenu(String namaMenu) {
        if (namaMenu == "" || namaMenu == null)
            return false;
        this.namaMenu = namaMenu;
        return true;
    }

    public boolean setHargaMenu(long hargaMenu) {
        if (hargaMenu <= 0)
            return false;
        this.hargaMenu = hargaMenu;
        return true;
    }

    public boolean setKategoriMenu(Kategori kategoriMenu) {
        if (kategoriMenu == null)
            return false;
        this.kategoriMenu = kategoriMenu;
        return true;
    }
}
