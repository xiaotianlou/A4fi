package TerrainFeatures;

import transformation.builtinADT.MeshADT;
import transformation.builtinADT.PolygonADT;

public class Humidity {




    public void humidityInitialization(MeshADT meshADT){
        for (var p:meshADT.getPolygons()){
            if (p.isIsland()){
                humiditySum(p);
            }
        }
    }

    private void humiditySum(PolygonADT polygonADT){
        int sum=0;
        sum = polygonADT.getWaterContent();
        for (var v: polygonADT.getVertices()){
            if (v.isAroundWater()){
                sum += 115;
            }
            if (v.isRiver()){
                sum += 100;
            }
        }
        polygonADT.setHumidity(sum/2);
    }

}
