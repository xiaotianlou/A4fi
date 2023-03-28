package Heatmaps;

import Reproducibility.Seed;
import featureRenderer.Renderable;
import transformation.builtinADT.InfoSet;
import transformation.builtinADT.MeshADT;
import transformation.builtinADT.PolygonADT;

public abstract class HeatMap implements Buildable{
    MeshADT m;
    public void eliminationOfWaters(){
        InfoSet temp;
        for (PolygonADT p : m.getPolygons()){
            temp= p.getInfoSet();
            if(!temp.isIsland()){


            }

        }


    }


    public HeatMap(MeshADT m) {
        this.m = m;
    }
}
