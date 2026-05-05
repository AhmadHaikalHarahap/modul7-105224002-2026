// subclass LampuPintar
// class turunan dari PerangkatPintar
public class LampuPintar extends PerangkatPintar {

    // override method dari parent
    @Override
    public void aktifkan() {
        System.out.println("Lampu dinyalakan dengan kecerahan standar.");
    }

    // overloading method pertama
    public void aturKecerahan(int level) {
        System.out.println("Kecerahan lampu diubah menjadi " + level + "%");
    }

    // overloading method kedua
    public void aturKecerahan(int level, String warna) {
        System.out.println("Kecerahan lampu diubah menjadi " + level + "% dengan warna " + warna + ".");
    }
}