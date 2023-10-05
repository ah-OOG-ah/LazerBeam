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
