package TerrainFeatures;

import transformation.builtinADT.InfoSet;
import transformation.builtinADT.MeshADT;
import transformation.builtinADT.PolygonADT;
import transformation.builtinADT.VertexADT;

public class Humidity {




    public void humidityInitialization(MeshADT meshADT){
        for (var p:meshADT.getPolygons()){
            if (p.isIsland()){
                Humidity.humiditySum(p);
            }
        }
    }

    public static void humiditySum(PolygonADT polygonADT){
        int sum=0;
        InfoSet i =polygonADT.getInfoSet();
        sum = i.getWaterContent();
        for (VertexADT v: polygonADT.getVertices()){
            if (v.isAroundWater()){
                sum += 115;
            }
            if (v.isRiver()){
                sum += 100;
            }
        }
        i.setHumidity(sum);
    }

}
