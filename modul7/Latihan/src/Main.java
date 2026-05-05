import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        // buat list untuk menyimpan perangkat
        ArrayList<PerangkatPintar> daftarPerangkat = new ArrayList<>();

        // upcasting (pakai tipe parent)
        PerangkatPintar lampu = new LampuPintar();
        PerangkatPintar ac = new AcPintar();

        // tambah objek ke dalam list
        daftarPerangkat.add(lampu);
        daftarPerangkat.add(ac);

        System.out.println("=== Menyalakan semua perangkat ===");

        // perulangan untuk menyalakan semua perangkat
        for (PerangkatPintar p : daftarPerangkat) {
            p.aktifkan();
        }

        System.out.println();
        System.out.println("=== Mengatur suhu khusus AC ===");

        // cek apakah objek adalah AC, lalu lakukan casting
        for (PerangkatPintar p : daftarPerangkat) {
            if (p instanceof AcPintar) {
                AcPintar acPintar = (AcPintar) p;
                acPintar.aturSuhu(20);
            }
        }

        System.out.println();
        System.out.println("=== Contoh pengaturan lampu ===");

        // contoh penggunaan overloading
        LampuPintar lp = new LampuPintar();
        lp.aturKecerahan(75);
        lp.aturKecerahan(80, "Putih");
    }
}

// soal 5: analisisi //
// errornya pada bagian kode, PerangkatPintar alat1 = new LampuPintar();
// alat1.aturKecerahan(75, "Putih"); //
// error terjadi karena compiler hanya melihat tipe variabel, bukan objek aslinya //
// variabel alat1 bertipe PerangkatPintar, sedangkan di class tersebut tidak ada method aturKecerahan(), Method itu hanya ada di class LampuPintar //
// makanya muncul error cannot find symbol //
// cara memperbaiki, kodenya di bawah ini //
// LampuPintar alat1 = new LampuPintar(); //
// alat1.aturKecerahan(75, "Putih"); //