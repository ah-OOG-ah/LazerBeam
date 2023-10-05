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

import org.jzy3d.analysis.AbstractAnalysis;
import org.jzy3d.analysis.AnalysisLauncher;
import org.jzy3d.chart.factories.SwingChartFactory;
import org.jzy3d.plot3d.primitives.SampleGeom;
import org.jzy3d.plot3d.primitives.Shape;
import org.jzy3d.plot3d.primitives.Surface;
import org.jzy3d.plot3d.rendering.canvas.Quality;

import java.util.ArrayList;

public class Graph extends AbstractAnalysis {

    private final Shape[] shapes;

    public Graph(Shape... ss) {
        super(new SwingChartFactory());
        shapes = ss;
    }

    public void graph() throws Exception {
        AnalysisLauncher.open(this);
    }

    @Override
    public void init() throws Exception {
        chart = new SwingChartFactory().newChart(Quality.Advanced());
        chart.getAxisLayout().setXAxisLabel("Flange");
        chart.getAxisLayout().setYAxisLabel("Core");

        for (Shape s : shapes) {
            chart.add(s);
        }
    }
}
