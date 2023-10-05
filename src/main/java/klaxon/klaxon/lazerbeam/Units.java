package klaxon.klaxon.lazerbeam;

public class Units {
    public static double lbToN(double lbs) {
        return lbs * 4.448222;
    }

    private static double NToLb(double N) {
        return N / 4.448222;
    }

    public static double inToMm(double in) {
        return in * 25.4;
    }

    private static double mmToIn(double mm) {
        return mm / 25.4;
    }

    public static double mmToM(double mm) {
        return mm / 1_000;
    }
}
