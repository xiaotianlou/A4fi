package Heatmaps;

import transformation.builtinADT.MeshADT;
import transformation.builtinADT.PolygonADT;

import java.awt.*;

public class HumidityHeatMap extends HeatMap{
    public HumidityHeatMap(MeshADT meshADT) {
        super(meshADT);
    }

    public double getValue(PolygonADT p) {
        return p.getHumidity();
    }
}
