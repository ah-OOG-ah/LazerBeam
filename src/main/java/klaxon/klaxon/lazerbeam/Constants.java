package klaxon.klaxon.lazerbeam;

import static klaxon.klaxon.lazerbeam.Units.*;

public class Constants {

    public static double nominalLoad = lbToN(25.3);
    public static double maxLoad = lbToN(30);
    public static double maxWidth = 50;
    public static double length = inToMm(18);
    public static double thicknessMm = inToMm(0.125);
    public static double thicknessM = mmToM(inToMm(0.125));
    public static Materials current = Materials.ACETAL;

    public static double load(BeamMath.Scenario s) {
        return switch (s) {

            case BEST, NOMINAL -> nominalLoad;
            case WORST -> maxLoad;
        };
    }
}
