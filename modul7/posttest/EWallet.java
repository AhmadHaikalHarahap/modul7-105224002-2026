class EWallet extends MetodePembayaran {
    // mengubah perilaku metode bayar dari superclass
    @Override
    public void bayar(double nominal) {
        System.out.println("Memotong saldo E-Wallet sebesar Rp" + nominal + "...");
    }

    // menambahkan metode bayar dengan parameter yang berbeda (nomorHp)
    public void bayar(double nominal, String nomorHp) {
        System.out.println("Memotong saldo E-Wallet sebesar Rp" + nominal + " dari nomor " + nomorHp + "...");
    }
}