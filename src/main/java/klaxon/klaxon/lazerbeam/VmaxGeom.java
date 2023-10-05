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

import org.jzy3d.colors.Color;
import org.jzy3d.colors.ColorMapper;
import org.jzy3d.colors.colormaps.ColorMapRainbow;
import org.jzy3d.maths.Range;
import org.jzy3d.plot3d.builder.Mapper;
import org.jzy3d.plot3d.builder.SurfaceBuilder;
import org.jzy3d.plot3d.builder.concrete.OrthonormalGrid;
import org.jzy3d.plot3d.primitives.Shape;

import java.util.function.BiFunction;

import static klaxon.klaxon.Main.minBase;
import static klaxon.klaxon.lazerbeam.Constants.*;

public class VmaxGeom {

    public static Shape surface(BiFunction<Double, Double, Double> mapper) {
        Range range = new Range(minBase, maxWidth);

        return surface(range, range, mapper);
    }

    public static Shape surface(Range xRange, Range yRange, BiFunction<Double, Double, Double> mapper) {
        return surface(xRange, yRange, 0.5f, mapper);
    }

    public static Shape surface(Range xRange, Range yRange, float alpha, BiFunction<Double, Double, Double> func) {

        Mapper mapper = new Mapper() {
            @Override
            public double f(double x, double y) {
                return func.apply(x, y);
            }
        };

        int steps = 50;

        Shape surface =
                new SurfaceBuilder().orthonormal(new OrthonormalGrid(xRange, steps, yRange, steps), mapper);

        ColorMapper colorMapper = new ColorMapper(new ColorMapRainbow(), surface, new Color(1, 1, 1, alpha));

        surface.setColorMapper(colorMapper);
        surface.setFaceDisplayed(true);
        surface.setWireframeDisplayed(true);
        surface.setWireframeColor(Color.BLACK);
        surface.setWireframeWidth(1);

        return surface;
    }
}
