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
