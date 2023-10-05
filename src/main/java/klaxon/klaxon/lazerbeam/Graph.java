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
