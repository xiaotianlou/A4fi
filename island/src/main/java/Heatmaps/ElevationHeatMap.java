package Heatmaps;

import transformation.builtinADT.MeshADT;

import java.awt.*;

public class ElevationHeatMap extends HeatMap{

    public ElevationHeatMap(MeshADT meshADT) {
        super(meshADT);
    }
    @Override
    public void build() {
        double max=0;
        for (var p: meshADT.getPolygons()){
            if (p.getElevation()>max){
                max = p.getElevation();
            }
        }
        for (var p: meshADT.getPolygons()) {
            if (p.isLake() || p.isIsland()) {
                double n = 1-(p.getElevation() / max);

                Color color = new Color((int) (220 * n), (int) (220 * n), 255);
                p.getInfoSet().setColor(color);
            }
        }
    }


}
