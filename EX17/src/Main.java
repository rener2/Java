/**
 * Created by Rene on 6.05.2016.
 */
public class Main {

    public static void main(String args) {
        Child marta = new Child("Marta");
        Child mati = new Child("Mati");
        Child kati = new Child("Kati");

        marta.playsWith(mati);
        mati.playsWith(kati);

        Child.getSandbox(marta); // --> laste nimed: ["Marta", "Mati", "Kati"]
    }
}
