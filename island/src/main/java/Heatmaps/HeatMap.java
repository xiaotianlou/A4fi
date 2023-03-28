package Heatmaps;

import Reproducibility.Seed;
import featureRenderer.Renderable;
import transformation.builtinADT.MeshADT;

public abstract class HeatMap implements Buildable{
    MeshADT m;

    public HeatMap(MeshADT m) {
        this.m = m;
    }
}
