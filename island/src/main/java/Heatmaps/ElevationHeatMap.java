package Heatmaps;

import transformation.builtinADT.MeshADT;
import transformation.builtinADT.PolygonADT;

import java.awt.*;

public class ElevationHeatMap extends HeatMap{

    public ElevationHeatMap(MeshADT meshADT) {
        super(meshADT);
    }

    @Override
    public double getValue(PolygonADT p) {
        return p.getElevation();
    }





}
