import java.util.ArrayList;

// 1. superclass LayananEkspedisi
class LayananEkspedisi {
    // menggunakan modifier protected agar bisa diturunkan ke subclass
    protected String nomorResi;
    protected double beratAktualKg;
    protected double panjang;
    protected double lebar;
    protected double tinggi;

    // Konstruktor untuk menginisialisasi semua atribut
    public LayananEkspedisi(String nomorResi, double beratAktualKg, double panjang, double lebar, double tinggi) {
        this.nomorResi = nomorResi;
        this.beratAktualKg = beratAktualKg;
        this.panjang = panjang;
        this.lebar = lebar;
        this.tinggi = tinggi;
    }

    // metode mengembalikan nilai tertinggi antara berat aktual dan volumetrik
    public double hitungBeratEfektif() {
        double beratVolumetrik = (panjang * lebar * tinggi) / 6000;
        return Math.max(beratAktualKg, beratVolumetrik);
    }

    // metode untuk mencetak nomor resi dan Berat Efektif
    public void cetakResi() {
        System.out.println("Nomor Resi: " + nomorResi + " | Berat Efektif: " + hitungBeratEfektif() + " Kg");
    }

    // metode polymorphic yang mengembalikan 0.0
    public double hitungOngkir() {
        return 0.0;
    }
}

// 2. subclass LayananReguler
class LayananReguler extends LayananEkspedisi {

    public LayananReguler(String nomorResi, double beratAktualKg, double panjang, double lebar, double tinggi) {
        super(nomorResi, beratAktualKg, panjang, lebar, tinggi);
    }

    // overriding: tarif dasar Rp 15.000 per Kg
    @Override
    public double hitungOngkir() {
        return hitungBeratEfektif() * 15000;
    }

    // overloading: tarif dengan diskon member dan surcharge jarak
    public double hitungOngkir(boolean isMember, int jarakKm) {
        double tarif = hitungOngkir(); // Ambil tarif dasar (panggil metode override)
        
        if (isMember) {
            tarif = tarif - (tarif * 0.10); // Diskon 10%
        }
        
        tarif += (500 * jarakKm); // Surcharge Rp 500 per jarakKm
        return tarif;
    }
}

// 3. subclass LayananExpress
class LayananExpress extends LayananEkspedisi {

    public LayananExpress(String nomorResi, double beratAktualKg, double panjang, double lebar, double tinggi) {
        super(nomorResi, beratAktualKg, panjang, lebar, tinggi);
    }

    // overriding: tarif dasar Rp 30.000 per Kg
    @Override
    public double hitungOngkir() {
        return hitungBeratEfektif() * 30000;
    }

    // metode spesifik klaimAsuransi
    public void klaimAsuransi(double nilaiBarang) {
        if (nilaiBarang > 1000000) {
            System.out.println("Klaim Asuransi VIP Rp" + nilaiBarang + " untuk resi " + nomorResi + " sedang diproses prioritas.");
        } else {
            System.out.println("Klaim Asuransi Standar diproses dalam 7 hari kerja.");
        }
    }
}

// 4. subclass LayananInternasional
class LayananInternasional extends LayananEkspedisi {
    // atribut tambahan
    private String negaraTujuan;
    private double nilaiBarangUSD;

    public LayananInternasional(String nomorResi, double beratAktualKg, double panjang, double lebar, double tinggi, String negaraTujuan, double nilaiBarangUSD) {
        super(nomorResi, beratAktualKg, panjang, lebar, tinggi);
        this.negaraTujuan = negaraTujuan;
        this.nilaiBarangUSD = nilaiBarangUSD;
    }

    // overriding: tarif dasar Rp 200.000 per Kg + Pajak
    @Override
    public double hitungOngkir() {
        double tarifDasar = hitungBeratEfektif() * 200000;
        
        // pajak bea cukai 20% jika nilai barang > 50 USD
        if (nilaiBarangUSD > 50) {
            tarifDasar += (tarifDasar * 0.20);
        }
        return tarifDasar;
    }

    // metode spesifik cetakManifest
    public void cetakManifest() {
        System.out.println("Manifest Internasional ke " + negaraTujuan + " - Deklarasi Nilai: $" + nilaiBarangUSD);
    }
}

// 5. Main
public class Main {
    public static void main(String[] args) {
        // upcasting: ArrayList tipe Superclass
        ArrayList<LayananEkspedisi> daftarLayanan = new ArrayList<>();

        // menambahkan data objek Subclass ke dalam ArrayList
        daftarLayanan.add(new LayananReguler("REG-11", 2, 50, 50, 50));
        daftarLayanan.add(new LayananExpress("EXP-22", 5, 10, 10, 10));
        daftarLayanan.add(new LayananInternasional("INT-33", 3, 20, 20, 20, "Korea", 100));

        // variabel penampung total pendapatan
        double totalPendapatanPerusahaan = 0.0;

        System.out.println("=== Sistem Ekspedisi ===");

        // perulangan menelusuri isi koleksi
        for (LayananEkspedisi layanan : daftarLayanan) {
            // 1. Panggil cetakResi()
            layanan.cetakResi();
            
            // 2. Tambahkan hasil hitungOngkir() tanpa parameter
            double ongkirDasar = layanan.hitungOngkir();
            totalPendapatanPerusahaan += ongkirDasar;
            
            // 3. Gunakan instanceof dan Downcasting untuk metode khusus
            if (layanan instanceof LayananReguler) {
                LayananReguler reg = (LayananReguler) layanan;
                System.out.println("Ongkir Reguler (Member, Jarak 25km): Rp " + reg.hitungOngkir(true, 25));
            } else if (layanan instanceof LayananExpress) {
                LayananExpress exp = (LayananExpress) layanan;
                exp.klaimAsuransi(1500000);
            } else if (layanan instanceof LayananInternasional) {
                LayananInternasional inter = (LayananInternasional) layanan;
                inter.cetakManifest();
            }
            System.out.println("----------------------------------------");
        }

        // 4. cetak total pendapatan keseluruhan (ongkir dasar)
        System.out.println("Total Pendapatan Keseluruhan Perusahaan: Rp " + totalPendapatanPerusahaan);
    }
}

// Penjelasan //
// LayananEkspedisi (Superclass): Ini adalah kelas induk (Superclass) yang menjadi cetak biru dasar untuk semua jenis layanan ekspedisi. Kelas ini menyimpan atribut umum yang pasti dimiliki setiap paket, seperti nomor resi, berat aktual, dan dimensi (panjang, lebar, tinggi). Di sini juga terdapat logika inti untuk membandingkan mana yang lebih besar antara berat asli dan berat volumetrik , serta menyediakan metode dasar untuk mencetak resi dan menghitung ongkir yang nantinya akan dimodifikasi oleh kelas anaknya //
// LayananReguler (Subclass): Merupakan kelas turunan dari LayananEkspedisi. Kelas ini memodifikasi (Override) perhitungan ongkir dengan tarif paling dasar, yaitu Rp 15.000 per Kg. Keunikan kelas ini adalah memiliki dua versi metode hitung ongkir (Overloading), di mana versi kedua dapat menerima info keanggotaan untuk memberikan diskon 10% dan menghitung biaya tambahan jarak tempuh sebesar Rp 500 per kilometer //
// LayananExpress (Subclass): Merupakan kelas turunan dari LayananEkspedisi untuk layanan yang lebih cepat. Kelas ini menetapkan tarif dasar yang lebih mahal, yakni Rp 30.000 per Kg. Fitur khususnya adalah metode klaimAsuransi(), yang akan otomatis memproses klaim prioritas (VIP) jika nilai barang melebihi Rp 1.000.000, atau asuransi standar jika di bawah itu //
// LayananInternasional (Subclass): Kelas turunan khusus untuk pengiriman ke luar negeri. Karena itu, kelas ini memiliki tambahan atribut berupa negara tujuan dan nilai barang dalam mata uang USD. Tarif dasarnya ditetapkan sebesar Rp 200.000 per Kg , dan di dalamnya terdapat logika untuk memungut pajak bea cukai sebesar 20% apabila nilai barang yang dikirim bernilai lebih dari 50 USD. Kelas ini juga punya metode khusus cetakManifest() //
// Main tempat di mana program dieksekusi. Kelas ini menyatukan semua layanan ke dalam satu daftar belanja (ArrayList) menggunakan konsep Upcasting. Di sini, program menelusuri setiap paket menggunakan perulangan untuk menghitung total pendapatan dasar perusahaan. Selain itu, kelas ini mendeteksi jenis spesifik setiap paket (menggunakan instanceof) dan melakukan Downcasting agar bisa menjalankan fitur-fitur unik seperti diskon jarak, klaim asuransi, atau cetak manifest dari masing-masing paket //