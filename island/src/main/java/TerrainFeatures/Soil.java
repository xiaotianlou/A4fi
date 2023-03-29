package TerrainFeatures;

import transformation.builtinADT.MeshADT;
import transformation.builtinADT.PolygonADT;

public class Soil{


    public void soilInitialization(MeshADT meshADT){
        for(var p:meshADT.getPolygons()){
            if (!p.isIsland()){
                waterAbsorbing(p);
            }
        }
        for (var p:meshADT.getPolygons()){
            humidityInfluence(p);
        }
    }
    private void waterAbsorbing(PolygonADT polygonADT){
        for (var p:polygonADT.getPolygons()){
            p.setSoilAbsorption(p.getSoilAbsorption()+5);
            for (var np:p.getPolygons()){
                if (np!=p){
                    p.setSoilAbsorption(p.getSoilAbsorption()+1);
                }
            }
        }
    }

    private void humidityInfluence(PolygonADT polygonADT){
        int tempA = polygonADT.getSoilAbsorption();
        int tempB = polygonADT.getHumidity();
        if (tempB-tempA>0){
            polygonADT.setHumidity(tempB-tempA);
        }
    }

}


