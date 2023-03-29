package Heatmaps;

import Reproducibility.Seed;
import featureRenderer.Renderable;
import transformation.builtinADT.InfoSet;
import transformation.builtinADT.MeshADT;
import transformation.builtinADT.PolygonADT;

import java.awt.*;

public abstract class HeatMap implements Buildable{
    MeshADT m;
    public void heatMapInitialization(){
        InfoSet temp;
        Color color1 = new Color(0,0,0);
        Color color2 = new Color(255,255,255);
        for (PolygonADT p : m.getPolygons()){
            temp= p.getInfoSet();
            temp.setColor(color1);
            if(temp.isIsland()||temp.isLake()){
            temp.setColor(color2);
            }
        }
    }


    public HeatMap(MeshADT meshADT) {
        this.m = meshADT;
    }
}
