package featureRenderer;

import Reproducibility.Seed;
import transformation.builtinADT.MeshADT;
import transformation.builtinADT.PolygonADT;

import java.util.Random;

public class WhittakerDiagramsRenderer implements Renderable {


    public PolygonADT BoundryColor(PolygonADT p,Seed seed){
        Random random = new Random();

        int ran = seed.getSeedArray().get(p.getId()%seed.getSeedArray().size())%3;



        if(ran > 0.75 ){
            p.setColor("238,118,33");
        }else{
            p.setColor("255,215,0");
        }
        return p;
    }

    @Override
    public MeshADT Rendering(MeshADT m, Seed seed) {
        for (PolygonADT p: m.getPolygons()){
            for(PolygonADT neighbour: p.getPolygons())
                if (neighbour.isIsland() == false){

                }
        }
        return m;
    }
}
