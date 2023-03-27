package TerrainFeatures;

import transformation.builtinADT.MeshADT;
import transformation.builtinADT.PolygonADT;

import java.util.Map;

/**
 * @author loux8@mcmaster.ca
 * @date 2023/3/24 11:06
 */
public class Lakes extends Tile{




    public MeshADT lakeBuilder(MeshADT meshADT, int n){
        int i = n;

        for (;0<i;i--) {
            boolean flag=false;

            int count=0;
            for (var p : meshADT.getPolygons()) {
                if (p.isIsland() && count > n && !flag) {
                    p.setIsland(false);
                    p.setColor("130,158,185");
                    p.setLake(true);
                    flag=true;
                    checkAround(p);
                }
                count +=1;
                System.out.println(count);
            }
        }
        return meshADT;
    }

    private void checkAround(PolygonADT polygonADT){
        for (var p:polygonADT.getPolygons()){
            if (p.getElevation()<polygonADT.getElevation()){
                p.setIsland(false);
                p.setColor("130,158,185");
                p.setLake(true);
                checkAround(p);
                System.out.println("-");
            }

        }
    }


}
