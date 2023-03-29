package Heatmaps;

import transformation.builtinADT.InfoSet;
import transformation.builtinADT.MeshADT;
import transformation.builtinADT.PolygonADT;

import java.awt.*;

public abstract class HeatMap implements Buildable{
    MeshADT meshADT;
    private void heatMapInitialization(){
        InfoSet temp;
        Color color1 = new Color(0,0,0);
        Color color2 = new Color(255,255,255);
        for (PolygonADT p : meshADT.getPolygons()){
            temp= p.getInfoSet();
            temp.setColor(color1);
            if(temp.isIsland()||temp.isLake()){
            temp.setColor(color2);
            }
        }
    }

    public HeatMap(MeshADT meshADT) {
        this.meshADT = meshADT;
        meshADT.calInfo();
        heatMapInitialization();
    }
    public void build() {
        double max=0;
        for (var p: meshADT.getPolygons()){
            if (getValue(p)>max){
                max = getValue(p);
            }
        }
        for (var p: meshADT.getPolygons()) {
            if (p.isLake() || p.isIsland()) {
                double n = 1-(getValue(p) / max);
                Color color = new Color((int) (220 * n), (int) (220 * n), 255);
                p.getInfoSet().setColor(color);
            }
        }
    }
    public abstract double getValue(PolygonADT p);
}
