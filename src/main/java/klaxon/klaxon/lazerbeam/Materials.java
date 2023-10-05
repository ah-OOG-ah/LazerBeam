package klaxon.klaxon.lazerbeam;

import static klaxon.klaxon.lazerbeam.Constants.thicknessM;

public enum Materials {
    ACETAL(2810.1376, 3027.1410, 3165.9412, 375.00),
    ACRYLIC(2344.3545, 2415.3314, 2519.6402, 312.50),
    MAPLE(6595.8966, 7369.5008, 7858.6919, 979.17),
    WALNUT(5294.0749, 5929.3335, 6249.7868, 750);
    /**
     * 2810.1376, 3027.1410, 3165.9412
     * 2344.3545, 2415.3314, 2519.6402
     * 6595.8966, 7369.5008, 7858.6919
     * 5294.0749, 5929.3335, 6249.7868
     */

    // N/mm^2
    public final double maxE;
    public final double E;
    public final double minE;
    // $/m^2
    public final double greenlyCost;
    // $/mm^2
    public final double costMm;
    // $/m^3
    public final double cost;

    Materials(double minE, double E, double maxE, double greenlyCost) {
        this.minE = minE;
        this.E = E;
        this.maxE = maxE;
        this.greenlyCost = greenlyCost;
        this.costMm = greenlyCost / 1_000_000;
        cost = greenlyCostToVolCost(greenlyCost);
    }

    public double MoE(BeamMath.Scenario s) {
        return switch (s) {
            case BEST -> maxE;
            case NOMINAL -> E;
            case WORST -> minE;
        };
    }

    /**
     * Converts the cost given by Dr. Greenly ($/(m^2 * 1/8")) to a cost per volume ($/m^3)
     */
    public static double greenlyCostToVolCost(double jg) {

        return jg / thicknessM;
    }
}
