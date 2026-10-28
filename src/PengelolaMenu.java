import java.util.Arrays;

public class PengelolaMenu {
    private Menu[] daftarMenu;

    public PengelolaMenu() {
        // Minimal 4 makanan dan 4 minuman sesuai instruksi soal
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

    // Menampilkan menu dikelompokkan berdasarkan kategori (Permintaan Soal)
    public void tampilkanMenuDikelompokkan() {
        System.out.println("\n===== DAFTAR MENU RESTORAN =====");
        System.out.println("[ MAKANAN ]");
        for (int i = 0; i < daftarMenu.length; i++) {
            if (daftarMenu[i].getKategoriMenu() == Kategori.MAKANAN) {
                System.out.printf("%d. %-30s | Rp %,d\n", (i + 1), daftarMenu[i].getNamaMenu(), daftarMenu[i].getHargaMenu());
            }
        }
        System.out.println("\n[ MINUMAN ]");
        for (int i = 0; i < daftarMenu.length; i++) {
            if (daftarMenu[i].getKategoriMenu() == Kategori.MINUMAN) {
                System.out.printf("%d. %-30s | Rp %,d\n", (i + 1), daftarMenu[i].getNamaMenu(), daftarMenu[i].getHargaMenu());
            }
        }
        System.out.println("================================");
    }

    public void tambahMenu(String namaMenu, long hargaMenu, Kategori kategoriMenu) {
        Menu[] daftarMenuBaru = Arrays.copyOf(this.daftarMenu, this.daftarMenu.length + 1);
        daftarMenuBaru[this.daftarMenu.length] = new Menu(namaMenu, hargaMenu, kategoriMenu);
        this.daftarMenu = daftarMenuBaru;
    }

    public void ubahHargaMenu(int index, long hargaBaru) {
        if (index >= 0 && index < daftarMenu.length) {
            daftarMenu[index].setHargaMenu(hargaBaru);
        }
    }

    public void hapusMenu(int index) {
        if (index < 0 || index >= daftarMenu.length) return;
        
        Menu[] daftarMenuBaru = new Menu[daftarMenu.length - 1];
        int k = 0;
        for (int i = 0; i < daftarMenu.length; i++) {
            if (i == index) continue; // Lewati indeks yang dihapus
            daftarMenuBaru[k++] = daftarMenu[i];
        }
        this.daftarMenu = daftarMenuBaru;
    }
}
