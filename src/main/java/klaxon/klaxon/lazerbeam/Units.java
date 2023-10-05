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

public class Units {
    public static double lbToN(double lbs) {
        return lbs * 4.448222;
    }

    private static double NToLb(double N) {
        return N / 4.448222;
    }

    public static double inToMm(double in) {
        return in * 25.4;
    }

    private static double mmToIn(double mm) {
        return mm / 25.4;
    }

    public static double mmToM(double mm) {
        return mm / 1_000;
    }
}
