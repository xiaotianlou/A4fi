package featureRenderer;

import Reproducibility.Seed;
import TerrainFeatures.Tile;
import transformation.builtinADT.MeshADT;
import transformation.builtinADT.PolygonADT;

/**
 * @author loux8@mcmaster.ca
 * @date 2023/3/24 11:06
 */
public class LakesRenderer extends Tile implements Renderable {

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


    @Override
    public MeshADT Rendering(MeshADT m, Seed seed) {
        int i = seed.getSeedArray().get(seed.getSeedArray().size()/3)*2;

        for (;0<i;i--) {
            boolean flag=false;

            int count=0;
            for (var p : m.getPolygons()) {
                if (p.isIsland() && count > i && !flag) {
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
        return m;
    }
}
