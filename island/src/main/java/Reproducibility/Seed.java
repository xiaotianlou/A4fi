package Reproducibility;


import featureRenderer.Shape.Shape;

import java.util.HashMap;

public class Seed {

    public int getSeed() {
        return seed;
    }

    private int seed;



    public Seed(){
        seed = 77123123;
    }
    public Seed(int seed){
        this.seed = seed;
    }




}
