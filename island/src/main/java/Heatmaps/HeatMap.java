package Heatmaps;

import Reproducibility.Seed;
import featureRenderer.Renderable;
import transformation.builtinADT.InfoSet;
import transformation.builtinADT.MeshADT;
import transformation.builtinADT.PolygonADT;

import java.awt.*;

public abstract class HeatMap implements Buildable{
    MeshADT m;
    public void eliminationOfWaters(){
        InfoSet temp;
        Color c = new Color(0,0,0);
        for (PolygonADT p : m.getPolygons()){
            temp= p.getInfoSet();
            if(!temp.isIsland()){
            temp.setColor(c);
            }
        }
    }


    public HeatMap(MeshADT m) {
        this.m = m;
    }
}
