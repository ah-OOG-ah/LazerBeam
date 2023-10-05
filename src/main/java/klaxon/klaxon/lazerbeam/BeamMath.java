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

import static klaxon.klaxon.lazerbeam.Constants.*;

public class BeamMath {

    public enum Scenario {
        BEST,
        NOMINAL,
        WORST;
    }

    /* ***** */
    /* Vmax */
    /* ***** */

    public static double calcTVmax(Materials mat, double b, double hw, Scenario s) {
        return calcAsIVmax(mat, 0, b, hw, s);
    }

    public static double calcSIVmax(Materials mat, double b, double hw, Scenario s) {
        return calcAsIVmax(mat, b, b, hw, s);
    }

    public static double calcAsIVmax(Materials mat, double bt, double bb, double hw, Scenario s) {

        return calcAsIVmax(load(s), length, mat, bt, bb, hw, s);
    }

    /**
     * Calculate the maximum deflection (Vmax) of an asymmetrical I-beam, given bt, bb, and hw
     * @param P Load, in newtons
     * @param L Length of beam in mm
     * @param bt Width of top flange in mm
     * @param bb Width of bottom flange in mm
     * @param hw Height of core piece in mm
     * @return Vmax in mm
     */
    public static double calcAsIVmax(double P, double L, Materials mat, double bt, double bb, double hw, Scenario s) {

        double I = calcIBeam2MoA(bt, bb, hw, thicknessMm);
        return calcVmax(P, L, mat.MoE(s), I);
    }

    /**
     * Calculate the maximum deflection (Vmax) of a beam, given basic constants.
     * @param P Load, in newtons
     * @param L Length of beam in mm
     * @param E Modulus of elasticity of the material in N/mm^2
     * @param I Area moment of inertia of the cross-section in mm^4
     * @return Vmax in mm
     */
    public static double calcVmax(double P, double L, double E, double I) {
        return -1 * P * Math.pow(L, 3) / 48 / E / I;
    }

    /* ***** */
    /*  MoA  */
    /* ***** */

    public static double calcSIBeam2MoA(double b, double hw) {
        return calcSIBeam2MoA(b, hw, thicknessMm);
    }

    /**
     * Calculate the moment of inertia of a symmetrical I-beam's cross-section.
     * @param b Width of the top and bottom pieces in mm
     * @param hw Height of the core piece in mm
     * @param thickness Thickness of the material in mm
     * @return Moment of inertia in mm^4
     */
    public static double calcSIBeam2MoA(double b, double hw, double thickness) {

        return calcIBeam2MoA(b, b, hw, thickness);
    }

    /**
     * Calculate the moment of inertia of a vertically asymmetrical I-beam's cross-section.
     * @param bt Width of the top piece in mm
     * @param bb Width of the bottom piece in mm
     * @param hw Height of the core piece in mm
     * @param tw Thickness of the material in mm
     * @return Moment of inertia in mm^4
     */
    public static double calcIBeam2MoA(double bt, double bb, double hw, double tw) {

        // Find centroid height from 0. X calculation is not needed, the beam is horizontally symmetrical
        // Areas
        double TA = bt * tw;
        double CA = hw * tw;
        double BA = bb * tw;
        double A = TA + CA + BA;

        // Distances
        double Td = hw + tw * 1.5;
        double Cd = hw / 2 + tw;
        double Bd = tw / 2;

        // Weight distances with area
        double cenT = TA * Td / A;
        double cenC = CA * Cd / A;
        double cenB = BA * Bd / A;

        // Centroid height
        double cenH = cenT + cenC + cenB;


        // Now calculate the actual moment, around the calculated centroid axis

        double itop;
        double icore;
        double ibottom;

        // Rectangle: bh^3/12

        itop = bt * Math.pow(tw, 3) / 12;
        icore = tw * Math.pow(hw, 3) / 12;
        ibottom = bb * Math.pow(tw, 3) / 12;

        // Shift vertically for top and bottom pieces
        // End axis is center of isegment; end axis is centroid above
        // Shift is area*distance^2

        itop += TA * Math.pow(Td - cenH, 2);
        icore += CA * Math.pow(Cd - cenH, 2); // already axial!
        ibottom += BA * Math.pow(Bd - cenH, 2);

        // Ix = (1 / 3) (B yb^3 - B1 hb^3 + b yt^3 - b1 ht^3)
        //double momI = (bb * Math.pow(hw / 2 + tw, 3) - (bb - tw) * Math.pow(hw / 2, 3) + bt * Math.pow(hw / 2 + tw, 3) - (bt - tw) * Math.pow(hw / 2, 3)) / 3;
        double momI = icore +  ibottom + itop;
        return momI;
    }

    /* ***** */
    /* MONEY */
    /* ***** */

    /**
     * Find the cost of a T-beam
     *
     * @param mat Material, for cost data
     * @param b  Bottom flange width, mm
     * @param hw  Core height, mm
     * @return Cost in USD
     */
    public static double calcTBeamCost(Materials mat, double b, double hw) {

        return calcIBeamCost(0, b, hw, mat);
    }

    /**
     * Find the cost of a symmetrical I-beam
     * @param b Flange width, mm
     * @param hw Core height, mm
     * @param mat Material, for cost data
     * @return Cost in USD
     */
    public static double calcIBeamCost(double b, double hw, Materials mat) {

        return calcIBeamCost(b, b, hw, mat);
    }

    /**
     * Find the cost of an asymmetrical I-beam
     * @param bt Top flange width, mm
     * @param bb Bottom flange width, mm
     * @param hw Core height, mm
     * @param mat Material, for cost data
     * @return Cost in USD
     */
    public static double calcIBeamCost(double bt, double bb, double hw, Materials mat) {

        double l = 480;
        double fl = l * bt + l * bb;
        double mid = hw * l;
        double area = fl + mid;
        return area * mat.costMm;
    }
}
