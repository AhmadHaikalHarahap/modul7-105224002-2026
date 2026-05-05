class KartuKredit extends MetodePembayaran {
    // mengubah perilaku metode bayar untuk Kartu Kredit
    @Override
    public void bayar(double nominal) {
        System.out.println("Mencetak tagihan Kartu Kredit sebesar Rp" + nominal + "...");
    }

    // metode spesifik: hanya ada di kelas KartuKredit
    public void verifikasiPIN() {
        System.out.println("Memverifikasi PIN Kartu Kredit... BERHASIL!");
    }
}