/** LazerBeam - a program to calculate beam deflection and possibly a bit more
 * Copyright (C) 2023  ah-OOG-ah
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

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
