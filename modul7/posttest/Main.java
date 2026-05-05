import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // buat ArrayList bertipe MetodePembayaran (Konsep Upcasting)
        ArrayList<MetodePembayaran> daftarPembayaran = new ArrayList<>();

        // masukkan minimal satu objek EWallet dan satu objek KartuKredit
        daftarPembayaran.add(new EWallet());
        daftarPembayaran.add(new KartuKredit());

        // gunakan perulangan (looping) untuk menelusuri array
        for (MetodePembayaran mp : daftarPembayaran) {
            
            // panggil metode bayar(100000) - demonstrasi Runtime Polymorphism
            mp.bayar(100000);

            // gunakan operator instanceof untuk pengecekan tipe objek
            if (mp instanceof EWallet) {
                // Downcasting ke EWallet untuk memanggil metode overloading
                EWallet ew = (EWallet) mp;
                ew.bayar(50000, "08123456789");
            } 
            else if (mp instanceof KartuKredit) {
                // downcasting ke KartuKredit untuk memanggil metode verifikasiPIN()
                KartuKredit kk = (KartuKredit) mp;
                kk.verifikasiPIN();
            }
            
            System.out.println("------------------------------------------");
        }
    }
}