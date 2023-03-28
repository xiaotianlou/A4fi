package TerrainFeatures;

import transformation.builtinADT.MeshADT;
import transformation.builtinADT.PolygonADT;

public class Humidity {
    MeshADT meshADT;


    public Humidity(MeshADT meshADT) {
        this.meshADT = meshADT;
    }

    public MeshADT humidityInitialization(){
        for (var p:this.meshADT.getPolygons()){
            if (p.isIsland()){
                humiditySum(p);
            }
        }
        return this.meshADT;
    }

    private void humiditySum(PolygonADT polygonADT){
        int sum=0;
        sum = polygonADT.getWaterContent();
        for (var v: polygonADT.getVertices()){
            if (v.isAroundWater()){
                sum += 50;
            }
            if (v.isRiver()){
                sum += 100;
            }
        }
        polygonADT.setHumidity(sum/2);
    }

}
