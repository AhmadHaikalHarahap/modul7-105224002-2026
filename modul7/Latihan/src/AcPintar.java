// class extends PerangkatPintar
public class AcPintar extends PerangkatPintar {

    // override method dari parent
    @Override
    public void aktifkan() {
        System.out.println("AC dinyalakan dan mulai mendinginkan ruangan.");
    }

    // method tambahan khusus untuk AC
    public void aturSuhu(int suhu) {
        System.out.println("Sekarang suhu diatur menjadi " + suhu + " derajat.");
    }
}