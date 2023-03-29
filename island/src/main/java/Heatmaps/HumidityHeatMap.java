package Heatmaps;

import transformation.builtinADT.MeshADT;

import java.awt.*;

public class HumidityHeatMap extends HeatMap{
    public HumidityHeatMap(MeshADT meshADT) {
        super(meshADT);
        heatMapInitialization();
        build(this.meshADT);
    }

    @Override
    public void build(MeshADT meshADT) {
        int max=0;
        for (var p: meshADT.getPolygons()){
            if (p.getHumidity()>max){
                max = p.getHumidity();
            }
        }
        for (var p: meshADT.getPolygons()) {
            if (p.isLake() || p.isLake()) {
                double n = p.getHumidity() / max;
                Color color = new Color((int) (50 * n), (int) (50 * n), 255);
                p.getInfoSet().setColor(color);
            }
        }
    }
}
