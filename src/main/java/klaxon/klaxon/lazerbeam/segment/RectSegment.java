package klaxon.klaxon.lazerbeam.segment;

import klaxon.klaxon.lazerbeam.Materials;

public class RectSegment extends BeamSegment {

    // Width/height in meters
    protected double b;
    protected double h;

    RectSegment(Materials m) {
        super(m);
    }

    @Override
    public double centroidY() {
        return h / 2;
    }

    @Override
    public double area() {
        return b * h;
    }

    /**
     * Returns the second moment of area for this segment.
     * Rectangle formula: bh^3/12
     */
    @Override
    public double x2MoA() {
        return b * Math.pow(h, 3) / 12;
    }

    @Override
    public double cost() {
        return 0;
    }
}
