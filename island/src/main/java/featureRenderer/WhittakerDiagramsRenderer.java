package featureRenderer;

import Reproducibility.Seed;
import transformation.builtinADT.MeshADT;
import transformation.builtinADT.PolygonADT;

import java.util.Random;

public class WhittakerDiagramsRenderer {


    public PolygonADT BoundryColor(PolygonADT p,Seed seed){
        Random random = new Random();

        int ran = seed.getSeedArray().get(p.getId()%seed.getSeedArray().size())%4;



        if(ran > 0.75 ){
            p.setColor("238,118,33");
        }else{
            p.setColor("255,215,0");
        }
        return p;
    }


    public MeshADT Rendering(MeshADT m, Seed seed, String biome) {
        for (PolygonADT p: m.getPolygons()){
            if(p.isIsland()){
//                p.setColor();
            }
            for(PolygonADT neighbour: p.getPolygons())
                if (!neighbour.isIsland()){
                    BoundryColor(p,seed);
                    for(PolygonADT nei: p.getPolygons()){
                        if (neighbour.isIsland() ){
                            BoundryColor(nei,seed);
                        }
                    }
                }
        }

        return m;
    }
}
