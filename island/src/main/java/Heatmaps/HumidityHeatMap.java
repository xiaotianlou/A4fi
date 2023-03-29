package Heatmaps;

import transformation.builtinADT.MeshADT;

public class HumidityHeatMap extends HeatMap{
    public HumidityHeatMap(MeshADT meshADT) {
        super(meshADT);
        build(meshADT);
    }

    @Override
    public void build(MeshADT meshADT) {
        int max=0;
        for (var p: meshADT.getPolygons()){
            if (p.getHumidity()>max){
                max = p.getHumidity();
            }
        }
        for (var p: meshADT.getPolygons()){
           double n = p.getHumidity()/max;

        }

    }
}
