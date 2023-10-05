package klaxon.klaxon.lazerbeam.segment;

import klaxon.klaxon.lazerbeam.Materials;

/**
 * Defines a prismatic segment of a beam. The Y axis is up, X is across, Z is along the beam (thus ignored).
 * Most/all calculations are thus around the X axis; deflection of the beam will bend around it.
 */
public abstract class BeamSegment {

    protected Materials material;

    BeamSegment(final Materials m) {
        material = m;
    }

    /**
     * Returns the height of the centroid from y = 0, for the segment by itself
     */
    public abstract double centroidY();

    /**
     * Returns the area.
     */
    public abstract double area();

    /**
     * Returns the second moment of area with respect to the X axis.
     */
    public abstract double x2MoA();

    /**
     * Return the price of this segment in USD
     */
    public abstract double cost();
}
