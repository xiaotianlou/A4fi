package featureRenderer;

import Reproducibility.Seed;
import TerrainFeatures.Tile;
import transformation.builtinADT.MeshADT;
import transformation.builtinADT.PolygonADT;

import java.awt.*;


public class LakeRenderer extends Tile implements Renderable {

    private void checkAround(PolygonADT polygonADT){
        Color c= new Color(83, 147, 222);
        for (var p:polygonADT.getPolygons()){
            if (p.getElevation()<polygonADT.getElevation()){
                p.setIsland(false);

                p.setColor(new int[]{c.getRed(),c.getGreen(),c.getBlue()});
                p.setLake(true);
                checkAround(p);
            }

        }
    }
    @Override
    public MeshADT Rendering(MeshADT m, Seed seed) {
        int i = seed.getSeedArray().get(seed.getSeedArray().size()/3)+3;
        Color c= new Color(59, 145, 238);
        for (;0<i;i--) {
            boolean flag=false;

            int count=0;
            for (var p : m.getPolygons()) {
                if (p.isIsland() && count > i && !flag) {
                    p.setIsland(false);
                    p.setColor(new int[]{c.getRed(),c.getGreen(),c.getBlue()});
                    p.setLake(true);
                    flag=true;
                    checkAround(p);
                }
                count +=1;
            }
        }
        return m;
    }
}
