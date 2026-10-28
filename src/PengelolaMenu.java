import java.util.Arrays;

public class PengelolaMenu {
    private Menu[] daftarMenu;

    public PengelolaMenu() {
        this.daftarMenu = new Menu[8];
        this.daftarMenu[0] = new Menu("Egg & Butter Toast", 20000, Kategori.MAKANAN);
        this.daftarMenu[1] = new Menu("Double Cheeseburger", 30000, Kategori.MAKANAN);
        this.daftarMenu[2] = new Menu("Margherita Pizza (1 slice)", 25000, Kategori.MAKANAN);
        this.daftarMenu[3] = new Menu("Miso Ramen", 27000, Kategori.MAKANAN);
        this.daftarMenu[4] = new Menu("Iced Tea", 12000, Kategori.MINUMAN);
        this.daftarMenu[5] = new Menu("Iced Latte", 18000, Kategori.MINUMAN);
        this.daftarMenu[6] = new Menu("Iced Black Coffee", 16000, Kategori.MINUMAN);
        this.daftarMenu[7] = new Menu("Iced Cappuccino", 17000, Kategori.MINUMAN);
    }

    
    public Menu[] getDaftarMenu() {
        return this.daftarMenu;
    }

    
    public Menu[] getDaftarMenuByKategori(Kategori kategori) {
        
        int count = 0;
        for (Menu menu : this.daftarMenu) {
            if (menu.getKategoriMenu() == kategori) {
                count++;
            }
        }
        
        Menu[] menuFiltered = new Menu[count];
        
        int index = 0;
        for (Menu menu : this.daftarMenu) {
            if (menu.getKategoriMenu() == kategori) {
                menuFiltered[index] = menu;
                index++;
            }
        }

        return menuFiltered;
    }

    public Menu getMenuByIndex(int index) {
        if (index < 0 || index >= this.daftarMenu.length) {
            return null;
        }
        return this.daftarMenu[index];
    }

    // Perbaikan append array (Ukuran array baru harus + 1)
    public void tambahMenu(String namaMenu, long hargaMenu, Kategori kategoriMenu) {
        Menu[] daftarMenuBaru = Arrays.copyOf(this.daftarMenu, this.daftarMenu.length + 1);
        daftarMenuBaru[this.daftarMenu.length] = new Menu(namaMenu, hargaMenu, kategoriMenu);
        this.daftarMenu = daftarMenuBaru;
    }

    // Pintu masuk untuk update menu secara opsional (Builder Pattern)
    public MenuUpdater updateMenu(int index) {
        if (index < 0 || index >= this.daftarMenu.length) {
            throw new IllegalArgumentException("Index menu tidak valid!");
        }
        return new MenuUpdater(index);
    }

    // Inner Class pembantu untuk menangani parameter opsional
    public class MenuUpdater {
        private final int index;
        private String nama = null;
        private Long harga = null; // Menggunakan Long (Object) agar bisa menampung null
        private Kategori kategori = null;

        public MenuUpdater(int index) {
            this.index = index;
        }

        public MenuUpdater setNamaMenu(String nama) {
            this.nama = nama;
            return this;
        }

        public MenuUpdater setHargaMenu(long harga) {
            this.harga = harga;
            return this;
        }

        public MenuUpdater setKategoriMenu(Kategori kategori) {
            this.kategori = kategori;
            return this;
        }

        // Method eksekusi akhir untuk menyimpan perubahan
        public void save() {
            Menu menuTarget = daftarMenu[index];

            if (this.nama != null) {
                menuTarget.setNamaMenu(this.nama);
            }
            if (this.harga != null) {
                menuTarget.setHargaMenu(this.harga);
            }
            if (this.kategori != null) {
                menuTarget.setKategoriMenu(this.kategori);
            }
        }
    }

    public void hapusMenu(int index) {
        if (index < 0 || index >= daftarMenu.length)
            return;

        Menu[] daftarMenuBaru = new Menu[daftarMenu.length - 1];
        int k = 0;
        for (int i = 0; i < daftarMenu.length; i++) {
            if (i == index)
                continue; // Lewati indeks yang dihapus
            daftarMenuBaru[k++] = daftarMenu[i];
        }
        this.daftarMenu = daftarMenuBaru;
    }
}
