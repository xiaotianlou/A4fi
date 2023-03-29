package featureRenderer.Elevation;

import Reproducibility.Seed;
import featureRenderer.Generable;
import transformation.builtinADT.InfoSet;
import transformation.builtinADT.MeshADT;
import transformation.builtinADT.PolygonADT;

import java.util.ArrayList;
import java.util.List;

public class SnowMountainGenerator implements Generable {
    @Override
    public MeshADT Genering(MeshADT m, Seed seed) {

        List<PolygonADT> land = new ArrayList();


        for (PolygonADT p : m.getPolygons()) {
            if (p.getInfoSet().isIsland()) {
                land.add(p);
            }
        }
        PolygonADT top = land.get(seed.getSeed()%land.size());
        InfoSet i = top.getInfoSet();
        i.setElevation(100);
        SnowMountainDescentAlgorithm(top);


        return m;
    }


    public void SnowMountainDescentAlgorithm(PolygonADT top){
        List<PolygonADT> neighbor =top.getPolygons();
        InfoSet t;
        for (PolygonADT p : neighbor){
            if(p.isIsland()){
                t=p.getInfoSet();
            if(t.getElevation()==-1&&top.getInfoSet().getElevation()>=10){
                t.setElevation(top.getElevation()-5);
                SnowMountainDescentAlgorithm(p);
            }
        }

        }
    }


}
