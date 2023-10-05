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

package klaxon.klaxon.lazerbeam;

import klaxon.klaxon.lazerbeam.segment.BeamSegment;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Defines a prismatic beam. The Y axis is up, X is across, Z is along the beam (thus ignored).
 * Most/all calculations are thus around the X axis; deflection of the beam will bend around it.
 */
public class Beam {

    private final Map<BeamSegment, Double> pieces = new HashMap<>();

    /**
     * Generate beam with these segments. The list is not returned or modified, no risk of heap pollution.
     */
    @SafeVarargs
    public Beam(final Pair<BeamSegment, Double>... segments) {

        Stream.of(segments).collect(Collectors.groupingBy(Pair::getKey));
    }

    public boolean addSegment(BeamSegment segment) {

        // No collision checking... yet
        //pieces.add(segment);
        return true;
    }

    /**
     * Calculates the area
     /
    public double calcArea() {

        return pieces.stream().mapToDouble(BeamSegment::area).sum();
    }*/

    /**
     * Calculates the centroid
     *
    public double calcCentroidY() {

        double area = calcArea();
        return pieces.stream().mapToDouble(s -> s.area() * s.centroidY() / area).sum();
    }*/

    /**
     * Calculates the second moment of area, relative to the centroid
     *
    public double calc2MoA() {

        return pieces.stream().mapToDouble(s -> s.x2MoA() + s.area() * Math.pow());
    }*/

    /**
     * Return the beam's total cost in USD
     *
    public double totalCost() {
        return pieces.stream().mapToDouble(BeamSegment::cost).sum();
    }*/
}
