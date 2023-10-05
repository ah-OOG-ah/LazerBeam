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

package klaxon.klaxon;

import klaxon.klaxon.lazerbeam.*;

import static klaxon.klaxon.lazerbeam.BeamMath.*;
import static klaxon.klaxon.lazerbeam.BeamMath.Scenario.*;
import static klaxon.klaxon.lazerbeam.Constants.thicknessMm;
import static klaxon.klaxon.lazerbeam.Materials.*;

public class Main {

    // mm
    public static double minBase = 20;

    private static Scenario scene = WORST;


    public static void main(String[] args) throws Exception {

        // GPLv3
        System.out.println(
                "LazerBeam  Copyright (C) 2023  ah-OOG-ah\n" +
                "This program comes with ABSOLUTELY NO WARRANTY.\n" +
                "This is free software, and you are welcome to redistribute it\n" +
                "under certain conditions.\n" +
                "For details see LICENSE.");

        for (Materials mat : Materials.values()) {
            double minCost = 1_000_000;
            double minb = -1;
            double minhw = -1;
            double maxDef = 10;
            for (double b = minBase; b <= 50; b += 0.5) {
                for (double hw = 6.5; hw <= 50; hw += 0.5) {
                    double vmax = calcSIVmax(mat, b, hw, scene);
                    if (vmax >= -2) {
                        double c = BeamMath.calcIBeamCost(b, hw, mat);
                        if (minCost >= c) {
                            minCost = c;
                            minb = b;
                            minhw = hw;
                            maxDef = vmax;
                        }
                    }
                }
            }

            String out = String.format("Minimum cost of %s SI-beam is $%.2f at %.3f mm deflection, %.1f mm flange, %.1f mm core", mat.name(), minCost, maxDef, minb, minhw);
            System.out.println(out);
        }
        System.out.println();

        for (Materials mat : Materials.values()) {
            double minCost = 1_000_000;
            double minbt = -1;
            double minbb = -1;
            double minhw = -1;
            double maxDef = 10;
            for (double bb = minBase; bb <= 50; bb += 0.5) {
                for (double bt = 6.5; bt <= 50 ; bt += 0.5) {
                    for (double hw = 6.5; hw <= 50; hw += 0.5) {
                        double vmax = calcAsIVmax(mat, bt, bb, hw, scene);
                        if (vmax >= -2) {
                            double c = BeamMath.calcIBeamCost(bt, bb, hw, mat);
                            if (minCost >= c) {
                                minCost = c;
                                minbt = bt;
                                minbb = bb;
                                minhw = hw;
                                maxDef = vmax;
                            }
                        }
                    }
                }
            }

            String out = String.format("Minimum cost of %s AsI-beam is $%.2f at %.3f mm deflection, %.1f mm top flange, %.1f bottom flange, %.1f mm core", mat.name(), minCost, maxDef, minbt, minbb, minhw);
            System.out.println(out);
        }
        System.out.println();

        for (Materials mat : Materials.values()) {
            double minCost = 1_000_000;
            double minb = -1;
            double minhw = -1;
            double maxDef = 10;
            for (double b = minBase; b <= 50; b += 0.5) {
                for (double hw = 6.5; hw <= 50; hw += 0.5) {
                    double vmax = calcTVmax(mat, b, hw, scene);
                    if (vmax >= -2) {
                        double c = BeamMath.calcTBeamCost(mat, b, hw);
                        if (minCost >= c) {
                            minCost = c;
                            minb = b;
                            minhw = hw;
                            maxDef = vmax;
                        }
                    }
                }
            }

            String out = String.format("Minimum cost of %s T-beam is $%.2f at %.3f mm deflection, %.1f mm flange, %.1f mm core", mat.name(), minCost, maxDef, minb, minhw);
            System.out.println(out);
        }
        System.out.println();

        double hw = 41.0;
        double bt = 7;
        double bb = 20;
        double maxvmax = calcAsIVmax(ACRYLIC, bt, bb, hw, WORST);
        double minvmax = calcAsIVmax(ACRYLIC, bt, bb, hw, NOMINAL);
        double I = calcIBeam2MoA(bt, bb, hw, thicknessMm);
        double cost = calcIBeamCost(bt, bb, hw, ACRYLIC);

        System.out.printf("Thickness: %.3fmm\n", thicknessMm);
        System.out.printf("Shape: AsI; %.3fmm top & %.3fmm bottom by %.3fmm high\n", bt, bb, hw + thicknessMm * 2);
        System.out.printf("Deflection: %.4fmm to %.4fmm\n", minvmax, maxvmax);
        System.out.printf("I: %.3f\n", I);
        System.out.printf("Cost: %.3f\n", cost);

        //Shape s = VmaxGeom.surface((b, h) -> Math.max(-2, calcTVmax(ACRYLIC, b, h, scene)));
        //Graph g = new Graph(s);
        //g.graph();
    }
}