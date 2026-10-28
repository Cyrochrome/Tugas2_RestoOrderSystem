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

    public void setNamaMenu(String namaMenu) {
        this.namaMenu = namaMenu;
    }

    public void setHargaMenu(long hargaMenu) {
        this.hargaMenu = hargaMenu;
    }

    public void setKategoriMenu(Kategori kategoriMenu) {
        this.kategoriMenu = kategoriMenu;
    }
}
