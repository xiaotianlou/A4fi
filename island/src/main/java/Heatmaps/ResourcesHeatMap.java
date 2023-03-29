package Heatmaps;

import transformation.builtinADT.MeshADT;
import transformation.builtinADT.PolygonADT;

public class ResourcesHeatMap extends HeatMap{
    public ResourcesHeatMap(MeshADT m) {
        super(m);
    }


    @Override
    public void build() {

    }

    @Override
    public double getValue(PolygonADT p) {
        return 0;
    }
}
