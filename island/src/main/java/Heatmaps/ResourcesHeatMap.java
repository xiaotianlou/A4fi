package Heatmaps;

import transformation.builtinADT.MeshADT;
import transformation.builtinADT.PolygonADT;

import java.awt.*;

public class ResourcesHeatMap extends HeatMap{

    MeshADT meshADT;
    public ResourcesHeatMap(MeshADT meshADT) {
        super(meshADT);
        this.meshADT = meshADT;

    }


    @Override
    public void build() {
        for (var p:meshADT.getPolygons()) {
            if (350 < p.getHumidity() && p.getHumidity() < 500) {
                if (50 < p.getElevation() && p.getElevation() < 1000) {
                    Color color1 = new Color(13, 157, 33);
                    p.getInfoSet().setColor(color1);
                }
            }
            if (100 < p.getHumidity() && p.getHumidity() < 400) {
                if (100<p.getElevation()&&p.getElevation()<300){
                    Color color2 = new Color(194, 89, 6);
                    p.getInfoSet().setColor(color2);
                }
            }
        }
    }

    @Override
    public double getValue(PolygonADT p) {
        return  0;
    }
}
