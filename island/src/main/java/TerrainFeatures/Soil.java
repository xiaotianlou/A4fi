package TerrainFeatures;

import transformation.builtinADT.MeshADT;

/**
 * @author loux8@mcmaster.ca
 * @date 2023/3/24 11:06
 */
public class Soil{

    MeshADT meshADT;

    public Soil(MeshADT meshADT) {
        this.meshADT = meshADT;
    }

    public MeshADT soilInitialization(){
        for (var p:meshADT.getPolygons()){
            double n = ((double) p.getHumidity()/350)*(0.2);
            int [] temp = p.getColor();
            int [] temp_color = new int[]{(int)(temp[0]*n),(int)(temp[0]*n),(int)(temp[0]*n)};
            p.setColor(temp_color);
        }
        return this.meshADT;
    }
}
